package com.yalogs.redisclient.service.conn;

import redis.clients.jedis.Jedis;

public interface RedisConnService {

    Jedis getJedis(String host, int port, String password, int timeout);

    Boolean isCanConnected(Jedis jedis);
}
