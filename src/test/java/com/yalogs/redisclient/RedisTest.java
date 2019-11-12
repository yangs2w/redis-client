package com.yalogs.redisclient;

import com.yalogs.redisclient.factory.jedis.JedisFactory;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RedisTest {

    @Test
    public void test01() {
        Jedis jedis = new Jedis("127.0.0.1");
        try {
            jedis.connect();
        }catch (Exception e) {
            System.out.println("连接错误");
        }
        Set<String> keys = jedis.keys("*8");
        for (String str: keys) {
            System.out.println(str);
        }
    }


    @Test
    public void test02() {
        Jedis jedis = new Jedis("127.0.0.1");
        String cursor = ScanParams.SCAN_POINTER_START;
        ScanParams scanParams = new ScanParams();
        scanParams.count(100);
        List<String> result = jedis.scan(cursor, scanParams).getResult();
        for (String str: result)
            System.out.println(str);

    }

    @Test
    public void test9() {
        Jedis jedis = new Jedis("127.0.0.1");
        jedis.select(2);
        String s1 = jedis.lindex("s1", 1);
        System.out.println(s1);
    }

    @Test
    public void test6() {
        Jedis jedis = new Jedis("127.0.0.1");
        jedis.select(0);
        Set<String> keys = jedis.keys("*");
        List<String> list = new ArrayList<>(keys);
        jedis.select(2);
        String key = "127.0.0.1";
        int count = 0;
        for (String value: list) {
            Long aLong = jedis.lpush(key, value);
            count += aLong;
        }
        System.out.println(count);
    }

    @Test
    public void test7() {
        Jedis jedis = new Jedis("127.0.0.1");
        jedis.select(2);
//        Long lpushx = jedis.lpush("127.0.0.1", "2324");
//        System.out.println(lpushx);
        String lindex = jedis.lindex("127.0.0.1", 1);
        System.out.println(lindex);

    }

    @Test
    public void test8() {
        Jedis jedis = new Jedis("127.0.0.1");
        jedis.select(2);
        Long expire = jedis.expire("127.0.0.1", 12);
        System.out.println(expire);
    }

    @Test
    public void test1() {
        Jedis jedis = new Jedis("yangs-itus.redis.rds.aliyuncs.com");
        jedis.auth("@noblind110511");
        List<String> list = jedis.configGet("databases");
        Integer integer = Integer.valueOf(list.get(1));
        System.out.println(integer);
        System.out.println(list.get(0));
    }

    @Test
    public void test2() {
        Jedis jedis = new Jedis("127.0.0.1");
        String ping = jedis.ping();
        System.out.println(ping);
    }

    @Test
    public void test3() {
        Jedis jedis = new Jedis("127.0.0.1");
        jedis.select(0);
        Long aLong = jedis.dbSize();
        System.out.println(aLong);
    }

    @Test
    public void test4() {
        Jedis jedis = new Jedis("127.0.0.1");
        for (int i = 0; i <1000000; i++) {
            jedis.set(String.valueOf(i), String.valueOf(i));
        }
    }

    @Test
    public void test5() {
        Jedis jedis = new Jedis("127.0.0.1");
//        jedis.select(5);
        jedis.flushDB();
    }
}
