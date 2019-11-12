package com.yalogs.redisclient.service.imle.write;

import com.yalogs.redisclient.service.write.WriteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class WriteServiceImpl implements WriteService {

    private static final Logger logger = LoggerFactory.getLogger(WriteServiceImpl.class);

    @Autowired
    private StringRedisTemplate template;



    /**
     * 将客户的redis中的keys保存到服务器的redis中
     * 以list列表保存，为了分页而设计
     * host作为key保存
     * @param keys
     * @return
     */
    @Override
    public Long writeClientKeys2Redis(String host,List<String> keys) {
        Long aLong = 0L;
        try {
            aLong = template.opsForList().rightPushAll(host, keys);
            // 统一保存30分钟
            template.expire(host, 60, TimeUnit.MINUTES);
        }catch (Exception e) {
            logger.error("存入客户端:{}:的keys失败，失败原因:{}", host, e.getMessage());
            return aLong;
        }
        return aLong;
    }

    @Override
    public Long addKey2KeysRedis(String host, String value) {
        Long aLong = 0L;
        try {
            aLong = template.opsForList().rightPush(host, value);
        }catch (Exception e) {
            logger.error("存入客户端:{}:的keys失败，失败原因:{}", host, e.getMessage());
            return aLong;
        }
        return aLong;
    }

    @Override
    public Long delKey2KeysRedis(String host, String value) {
        Long aLong = 0L;;
        try {
            aLong = template.opsForList().remove(host, 0, value);
        }catch (Exception e) {
            logger.error("删除客户端{}:的key失败，失败原因:{}", host, e.getMessage());
            return aLong;
        }
        return aLong;
    }


}
