package com.yalogs.redisclient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisClientApplicationTests {

    @Autowired
    public StringRedisTemplate template;


    @Test
    public void contextLoads() {
    }

    @Test
    public void test0() {
        int a = 23 << 3;
        System.out.println(a);
        // 23     --> 10111
        // 左移三位 --> 10111000 --> 184
    }

    @Test
    public void test01() {
        int a = 23 >> 3;
        System.out.println(a);
        // 23     --> 10111
        // 右移三位 --> 00010 --> 2
    }

    @Test
    public void test02() {
        int a = 23 >>> 3;
        System.out.println(a);
        // 23           --> 00010111
        // 无符号右移三位  --> 00000010 --> 2
    }

    @Test
    public void test03() {
        int a = -23 >>> 3;
        System.out.println(a);
        // -23的二进制是23进制的取反加1--->参考二进制补码，反码
        // 23       --> 0000 0000 0000 0000 0000 0000 0001 0111 在java中int类型占4个字节，也就是32位
        // -23      --> 1111 1111 1111 1111 1111 1111 1110 1001
        // 右移三位   --> 0001 1111 1111 1111 1111 1111 1111 1101 --> 536870909
    }


    @Test
    public void testWei() {
        int a = 23 ^ 31;
        System.out.println(a);
        // 23 --> 10111 补位后 --> 00010111
        // 31 --> 11111 补位后 --> 00011111
        // 运算^后得到          --> 00001000 --> 8
    }

    @Test
    public void test1() {
        int a = 23 & 31;
        System.out.println(a);
        // 23 --> 10111 补位后 --> 00010111
        // 31 --> 11111 补位后 --> 00011111
        // 运算&后得到          --> 00010111 --> 23 // 只要两个中的比较都为1时结果为1
    }

    @Test
    public void test2() {
        int a = 23 | 31;
        System.out.println(a);
        // 23 --> 10111 补位后 --> 00010111
        // 31 --> 11111 补位后 --> 00011111
        // 运算|后得到          --> 00011111 --> 31 // 只要两个中的比较有一个为1时结果为1
    }


    @Test
    public void test04() {
        int a = ~23;
        System.out.println(a);
        // 23 --> 10111 --> 0000 0000 0000 0000 0000 0000 0001 0111
        // 取反后        --> 1111 1111 1111 1111 1111 1111 1110 1000 --> 最高位是1，所以明显是个负数 --> -24
    }

    @Test
    public void test3() {
        String sq = new String("hello");
        String sp = "hello";
        System.out.println(sq.equals(sp));
        System.out.println(sq == sp);
    }

}
