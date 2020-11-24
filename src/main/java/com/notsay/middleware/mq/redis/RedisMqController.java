package com.notsay.middleware.mq.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: dsy
 * @date: 2020/11/23 12:44
 */
@RestController
@RequestMapping("/redisMq")
public class RedisMqController {
    @Autowired
    private MessageProducer produce;
    @Autowired
    private MessageConsumer messageConsumer;

    @GetMapping
    @RequestMapping("produce")
    public String produce() {
        Message message = new Message("111", "redis mq test");
        produce.sendMessage(message);

        return "produce success";
    }

    @GetMapping
    @RequestMapping("consume")
    public String consume() {
        messageConsumer.run();
        return "consumer success";
    }

    @RequestMapping("{name}")
    public String sendMessage(@PathVariable("name") String name) {
        return produce.sendMessage(name);
    }


}
