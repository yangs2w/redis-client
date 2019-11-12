package com.yalogs.redisclient.scheduled;

import com.yalogs.redisclient.factory.jedis.JedisFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Component
public class Term {

    @Scheduled(fixedRate = 300000)
//    @Scheduled(fixedRate = 10000)
    public void inspectTerm() {
        // 读取JedisTerm
        Map<String, Long> jedisTerm = JedisFactory.getJedisTerm();
        // 获取jedisTerm中的数据
        boolean empty = jedisTerm.isEmpty();
        if (empty)
            return;
        Set<String> strings = jedisTerm.keySet();
        List<String> list = new ArrayList<>(strings);
        long now = System.currentTimeMillis();
        for (String key: list) {
            if (jedisTerm.get(key) <= now) {
                JedisFactory.removeTerm(key);
            }
        }
    }
}
