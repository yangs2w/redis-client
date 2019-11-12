package com.yalogs.redisclient.controller.conn;


import com.yalogs.redisclient.commons.ConnResult;
import com.yalogs.redisclient.factory.jedis.JedisFactory;
import com.yalogs.redisclient.key.RsaUtils;
import com.yalogs.redisclient.service.conn.RedisConnService;
import com.yalogs.redisclient.service.read.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RedisConnController {

    @Autowired
    private StringRedisTemplate template;

    @Autowired
    private RedisConnService redisConnService;

    @Autowired
    private ContentService contentService;

    @RequestMapping(value = "/link")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("link");
        String publicKey = RsaUtils.getPublicKey();
        modelAndView.addObject("publicKey", publicKey);
        return modelAndView;
    }

    @RequestMapping(value = "/redis/conn")
    @ResponseBody
    public ConnResult startCInnRedis(String host, @RequestParam(defaultValue = "6379") String port, String password, @RequestParam(defaultValue = "2000") String timeout, HttpServletRequest request) {
        int port1 = Integer.valueOf(port);
        int timeout1 = Integer.valueOf(timeout);
        String enPass = null;
        try {
            enPass = RsaUtils.decrypt(password, RsaUtils.getPrivateKey());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 从JedisFactory中获取一个Jedis对象
        Jedis jedis = redisConnService.getJedis(host, port1, enPass, timeout1);
        if (null == jedis)
            return new ConnResult(400, "请检查你提交的信息，或查看redis是否可连接", null);
        // 测试jedis对象的可用性
        Boolean canConnected = redisConnService.isCanConnected(jedis);
        if (!canConnected) {
            // 从map中删除jesis
            JedisFactory.removeJedis4Map(jedis.getClient().getHost());
            return new ConnResult(400, "请检查你提交的信息，或查看redis是否可连接", null);
        }
        JedisFactory.setTermTime(host);
        // 获取到了Jedis对象后，将host作为参数返回
        // 每次访问/**下面的所有网页都要提供host参数值
        // host参数值可以在header中，也可以在RequestParam中
        // 前端通过js将host存入到localStorage中
        return new ConnResult(200, jedis.getClient().getHost());
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public String test() {
        template.opsForValue().set("aini", "shide");
        return "success";
    }
}
