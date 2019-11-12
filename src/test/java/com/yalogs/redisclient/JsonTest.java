package com.yalogs.redisclient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.yalogs.redisclient.factory.jedis.JedisFactory;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JsonTest {


    @Test
    public void Test1() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        Jedis jedis = JedisFactory.generateJedis("127.0.0.1");
        String string = mapper.writeValueAsString(jedis);
        System.out.println(string);
    }
}
