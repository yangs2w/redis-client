package com.yalogs.redisclient.controller.curd;

import com.yalogs.redisclient.factory.jedis.JedisFactory;
import com.yalogs.redisclient.service.conn.RedisConnService;
import com.yalogs.redisclient.service.read.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

@Controller
public class PageController {

    @Autowired
    private ContentService contentService;

    @Autowired
    private RedisConnService redisConnService;

    @RequestMapping(value = "/")
    public ModelAndView index() {
        return new ModelAndView("redirect:/link");
    }


    @RequestMapping(value = "/crud")
    public ModelAndView crudPage(String host) {
        ModelAndView mav = new ModelAndView();
        // 通过host获取Map中的jedis对象
        Jedis jedis4host = JedisFactory.getJedis4host(host);
        // 无法正常与redis进行连接
        if (null == jedis4host || !redisConnService.isCanConnected(jedis4host)) {
            mav.addObject("msg", "您的会话已过期，请重新申请连接");
            mav.setViewName("redirect:/link");
            return mav;
        }
        mav.setViewName("crud");
        mav.addObject("dbNum", contentService.dbNum(jedis4host));
        mav.addObject("keySize", contentService.keysNum(jedis4host));
        return mav;
    }
}
