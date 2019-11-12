package com.yalogs.redisclient;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class StringTest {

    @Test
    public void test3() {
        int size = Integer.SIZE;
        System.out.println(size);
    }

    @Test
    public void test5() {
        int count = 0;
        boolean interrupted = Thread.interrupted();
        for (;;) {
            if (count >= 100)
                return;
            System.out.println(count);
            count++;
        }
    }

    @Test
    public void tes4() {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        pool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("在云心");
            }
        });
    }


    @Test
    public void test1() {
        String s1 = "abc";
        String s2 = new String(s1);
        System.out.println(s1 == s2);
    }

    @Test
    public void test2() throws UnsupportedEncodingException {
        String str = "abc";
        byte[] bytes = str.getBytes("UTF-8");
        str.trim();
        str.isEmpty();
        str.length();
        str.toLowerCase();
        str.toCharArray();
        int bcs = str.compareTo("bcs");
        str.compareToIgnoreCase("ABC");
        str.contains("a");
        byte[] bytes1 = str.getBytes(Charset.forName("UTF-8"));
        System.out.println(bytes.length);
        System.out.println(bytes1.length);
    }
}
