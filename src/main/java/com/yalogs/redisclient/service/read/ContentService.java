package com.yalogs.redisclient.service.read;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

public interface ContentService {

    Set<String> keys(Jedis jedis);

    Integer dbNum(Jedis jedis);

    Integer keysNum(Jedis jedis);

    List<String> keysList4Limit(String host, Long page, Long limit);

    List<String> scanLimit(Jedis jedis, Integer page, Integer limit);
}
