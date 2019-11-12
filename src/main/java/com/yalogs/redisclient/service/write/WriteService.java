package com.yalogs.redisclient.service.write;

import sun.rmi.runtime.Log;

import java.util.List;

public interface WriteService {

    Long writeClientKeys2Redis(String host, List<String> keys);

    Long addKey2KeysRedis(String host, String value);

    Long delKey2KeysRedis(String host, String value);
}
