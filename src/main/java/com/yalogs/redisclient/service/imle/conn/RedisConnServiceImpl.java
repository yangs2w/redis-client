package com.yalogs.redisclient.service.imle.conn;

import com.yalogs.redisclient.factory.jedis.JedisFactory;
import com.yalogs.redisclient.service.conn.RedisConnService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;


@Service
public class RedisConnServiceImpl implements RedisConnService {

    private static final Logger logger = LoggerFactory.getLogger(RedisConnServiceImpl.class);


    @Override
    public Jedis getJedis(String host, int port, String password, int timeout) {
        Jedis jedis = null;
        // 根据参数的不同使用不同的生成方法生成Jedis客户端
        if (null == host || "".equals(host.trim()))
            return null;
        if (port == 0)
            port = 6379;
        if (timeout == 0)
            timeout = 2000;
        jedis = JedisFactory.geneateJedis(host, port, timeout);
        if (StringUtils.isNotBlank((password))) {
            try {
                jedis.auth(password);
            }catch (Exception e) {
                logger.error("{}:没有开启密码", host);
                return null;
            }
         }
        return jedis;
    }

    /**
     * 测试jedis连接的可用性
     * @param jedis
     * @return
     */
    @Override
    public Boolean isCanConnected(Jedis jedis) {
        if (null == jedis)
            return false;
        try {
           jedis.connect();
        }catch (Exception e) {
            if (e instanceof JedisConnectionException)
                logger.error("{},连接不可用", jedis.getClient().getHost());
            return false;
        }
        try {
            jedis.dbSize();
        }catch (Exception e) {
            logger.error("Redis服务器需要密码.");
            return false;
        }
        return true;
    }
}
