package com.yalogs.redisclient.controller.curd;

import com.yalogs.redisclient.commons.BaseResult;
import com.yalogs.redisclient.commons.Keys;
import com.yalogs.redisclient.commons.KeysResult;
import com.yalogs.redisclient.factory.jedis.JedisFactory;
import com.yalogs.redisclient.service.read.ContentService;
import com.yalogs.redisclient.service.write.WriteService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.*;

@Controller
public class ReadController {

    private static final Logger logger = LoggerFactory.getLogger(ReadController.class);

    private static final List<Keys> list = new ArrayList<>();

    @Autowired
    private StringRedisTemplate template;

    @Autowired
    private ContentService contentService;

    @Autowired
    private WriteService writeService;


    @RequestMapping(value = "/search/key")
    @ResponseBody
    public KeysResult searchKeys(String host, String key) {
        if (StringUtils.isBlank(host))
            return new KeysResult(400, "连接异常，请重启浏览器", 0, null);
        if (StringUtils.isBlank(key))
            return new KeysResult(400, "请输入正确的搜索词", 0, null);
        Jedis jedis4host = JedisFactory.getJedis4host(host);
        if (null == jedis4host)
            return new KeysResult(500, "您的会话已过期，请重新申请连接", 0, null);
        return null;
    }


    @RequestMapping(value = "/db/select")
    @ResponseBody
    public BaseResult dbSelect(String host, Integer dbNumber) {
        if (StringUtils.isBlank(host))
            return new BaseResult(500, "您的会话已过期，请重新申请连接", null);
        Jedis jedis = JedisFactory.getJedis4host(host);
        if (null == jedis)
            return new BaseResult(500, "您的会话已过期，请重新申请连接", null);
        jedis.select(dbNumber);
        // 获取key的个数
        Long aLong = jedis.dbSize();
        List<Object> list = new ArrayList<>();
        list.add(aLong);
        return new BaseResult(200, null, list);
    }

    @RequestMapping(value = "/keys")
    @ResponseBody
    public KeysResult keys(String host, @RequestParam(defaultValue = "0") int db , Integer page, Integer limit) {
        Jedis jedis = JedisFactory.getJedis4host(host);
        if (null == jedis)
            return new KeysResult(500, "您的会话已过期，请重新申请连接", 0, null);
        jedis.select(db);
        List<String> list = contentService.scanLimit(jedis, page, limit);
        List<Keys> sendList = new ArrayList<>();
        for (String s : list) {
            String type = jedis.type(s);
            sendList.add(new Keys(type, s));
        }
        return new KeysResult(0, null, Integer.valueOf(jedis.dbSize().toString()), sendList);
    }



//    @RequestMapping(value = "/keys")
//    @ResponseBody
//    public KeysResult keys(String host, @RequestParam(defaultValue = "0") int db , Integer page, Integer limit) {
//        // 先向redis中查询是否已存在客户端的keys
//        Long count = template.opsForList().size(host + db);
//        Jedis jedis = JedisFactory.getJedis4host(host);
//            Jedis jedis = JedisFactory.getJedis4host(host);
//            if (null == jedis)
//                return new KeysResult(500, "您的会话已过期，请重新申请连接", 0, null);
//            Set<String> keys = contentService.keys(jedis);
//            if (keys == null) {
//                return new KeysResult(0, null, 0, null);
//            }
//            List<String> list = new ArrayList<>(keys);
//            // 将keys存入
//            count = writeService.writeClientKeys2Redis(host + db, list);
//        }
//        // 开始取值后存如到list
//        List<String> list = contentService.keysList4Limit(host+db, Long.valueOf(page), Long.valueOf(limit));
//        Jedis jedis4host = JedisFactory.getJedis4host(host);
//        if (null == jedis4host)
//            return new KeysResult(500, "您的会话已过期，请重新申请连接", 0, null);
//        List<Keys> sendList = new ArrayList<>();
//        for (int i = 0; i < list.size(); i++) {
//            String s = list.get(i);
//            String type = jedis4host.type(s);
//            sendList.add(new Keys(type, s));
//        }
//        return new KeysResult(0, null, Integer.valueOf(count.toString()), sendList);
//    }
}
