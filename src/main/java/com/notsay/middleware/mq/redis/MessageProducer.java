package com.notsay.middleware.mq.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: dsy
 * @date: 2020/11/23 12:40
 */
@Service
public class MessageProducer {
    @Autowired
    RedisTemplate<String, Message> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public Long sendMessage(Message message) {
        System.out.println("发送了" + message);
        return redisTemplate.opsForList().leftPush("queue", message);
    }

    public String sendMessage(String name) {
        try {
            stringRedisTemplate.convertAndSend("TOPIC_USERNAME", name);
            return "消息发送成功了";

        } catch (Exception e) {
            e.printStackTrace();
            return "消息发送失败了";
        }
    }
}
