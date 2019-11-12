package com.yalogs.redisclient.factory.jedis;


import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用于生产出Jedis客户端，并且将客户端集中管理的工具集
 */
public class JedisFactory {

// 用于存储产生的jedis对象
    /**
     * 由于Jedis连接Redis服务器的时候，使用的是Socket进行长连接，所以需要将客户的Jedis对象不存起来
     */
    private static final Map<String, Jedis> jedisMap = new ConcurrentHashMap<>();

    /**
     * 用于存储Host的有效时间
     */
    private static final Map<String, Long> jedisTerm = new ConcurrentHashMap<>();

    public static Map<String, Long> getJedisTerm() {
        return jedisTerm;
    }


    /**
     * 设置jedis对象的有效期
     * 统一设置有效期为12分钟
     * @param host
     */
    public static void setTermTime(String host) {
        long millis = System.currentTimeMillis() + 1800000; //1800000;
        jedisTerm.put(host, millis);
    }

    /**
     * 更新有效期
     * @param host
     */
    public static void updateTermTime(String host) {
        setTermTime(host);
    }

    /**
     * 过期后移除jedis对象
     * @param host
     * @return
     */
    public static Integer removeTerm(String host) {
        // 首先移除jedisTerm中的对象
        Long remove = jedisTerm.remove(host);
        // 移除jedisMap中的对象
        if (remove < 1L)
            return 0;
        Jedis remove1 = jedisMap.remove(host);
        if (null == remove1)
            return 0;
        return 1;
    }

    /**
     * 通过host从jedisMap中获取Jedis
     * @param host
     * @return
     */
    public static Jedis getJedis4host(String host) {
        return jedisMap.get(host);
    }

    /**
     * 向jedisMap中加入客户的Jedis对象
     */
    private static void addJedis2Map(String host, Jedis jedis) {
        jedisMap.put(host, jedis);
    }

    /**
     * 从Jedis的Map中移除Jedis客户端对象
     * @param host
     * @return
     */
    public static Jedis removeJedis4Map(String host) {
        return jedisMap.remove(host);
    }


    /**
     * 用于生产一个Jedis对象
     * @param host
     * @param port
     * @param password
     * @param timeout
     * @return
     */
    public static Jedis generateJedis(String host, int port, String password, int timeout) {
        Jedis jedis = new Jedis(host, port, timeout);
        jedis.multi();
        jedis.auth(password);
        addJedis2Map(host, jedis);
        return jedis;
    }

    public static Jedis geneateJedis(String host, int port, int timeout) {
        Jedis jedis = new Jedis(host, port, timeout);
        addJedis2Map(host, jedis);
        return jedis;
    }

    public static Jedis generateJedis(String host, String password) {
        Jedis jedis = new Jedis(host, 6379);
        jedis.auth(password);
        addJedis2Map(host, jedis);
        return jedis;
    }

    public static Jedis generateJedis(String host, int port) {
        Jedis jedis = new Jedis(host, port);
        addJedis2Map(host, jedis);
        return jedis;
    }

    public static Jedis generateJedis(String host) {
        Jedis jedis = new Jedis(host, 6379);
        addJedis2Map(host, jedis);
        return jedis;
    }


}
