package com.notsay.middleware.mq.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: dsy
 * @date: 2020/11/23 12:43
 */
@Service
public class MessageConsumer extends Thread {
    @Autowired
    RedisTemplate<String, Message> redisTemplate;


    @Override
    public void run() {
        while (true) {
            Message message = redisTemplate.opsForList().rightPop("queue", 1000L, TimeUnit.SECONDS);
            System.out.println("接收到了" + message);
        }
    }
}
