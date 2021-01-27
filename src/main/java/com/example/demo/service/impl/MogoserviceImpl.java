package com.example.demo.service.impl;


import com.example.demo.service.MogoService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @auther zoujialiang
 * @date 2020/10/31 15:01
 */

@Service
class MogoserviceImpl implements MogoService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Document> aggregation() {

        redisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                operations.executePipelined(new SessionCallback<Object>() {
                    @Override
                    public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                        V v = operations.opsForValue().get("234");
                        return null;
                    }
                });
                return null;
            }
        });

        redisTemplate.execute(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> operations) throws DataAccessException {
                //开启事务
                operations.multi();
                for (int i = 1; i <= 5; i++) {
                    redisTemplate.convertAndSend("channel:test", String.format("我是消息{%d}号: %tT", i, new Date()));
                    redisTemplate.opsForValue().set("key" + i, "key" + i);
                }
                //执行事务
                operations.exec();
                return null;
            }
        });
        return null;
    }

//    public List<ChatUserList> test2(){
//        return mongoTemplate.find(new Query().addCriteria(Criteria.where("followCount").is(55)),ChatUserList.class,"chatUser");
//    }


}
