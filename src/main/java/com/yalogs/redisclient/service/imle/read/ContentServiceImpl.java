package com.yalogs.redisclient.service.imle.read;

import com.yalogs.redisclient.service.read.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ContentServiceImpl implements ContentService {


    @Autowired
    private StringRedisTemplate template;

    @Override
    public Set<String> keys(Jedis jedis) {
        Set<String> keys = jedis.keys("*");
        return keys;
    }

    /**
     * 获取redis中对象数
     * @param jedis
     * @return
     */
    @Override
    public Integer dbNum(Jedis jedis) {
        List<String> list = jedis.configGet("databases");
        String s = list.get(1);
        return Integer.valueOf(s);
    }

    /**
     * 获取redis中key的数量
     * @param jedis
     * @return
     */
    @Override
    public Integer keysNum(Jedis jedis) {
        Long aLong = jedis.dbSize();
        return Integer.valueOf(aLong.toString());
    }

    /**
     * 分页
     * @param host
     * @param page
     * @param limit
     * @return
     */
    @Override
    public List<String> keysList4Limit(String host, Long page, Long limit) {
        List<String> list = new ArrayList<>();
        for (long i = page > 1 ? ((page-1) * limit) : 0; i < ((page * limit) > template.opsForList().size(host) ? template.opsForList().size(host) : (page * limit)); i++) {
            String s = template.opsForList().index(host, i);
            list.add(s);
        }
        return list;
    }

    /**
     * 使用scan来实现分页
     * @param jedis
     * @param page
     * @param limit
     */
    @Override
    public List<String> scanLimit(Jedis jedis, Integer page, Integer limit) {
        String cursor = ScanParams.SCAN_POINTER_START;
        List<String> list = new ArrayList<>();
        if (null == jedis)
            return null;
        ScanParams scanParams = new ScanParams();
        scanParams.count(page * limit);
        List<String> result = jedis.scan(cursor, scanParams).getResult();
        List<String> resultList = new ArrayList<>();
        for (int i = page > 1 ? ((page-1) * limit) : 0; i < ((page * limit) < jedis.dbSize() ? (page * limit) : jedis.dbSize()); i++) {
            resultList.add(result.get(i));
        }
        return resultList;
    }
}
