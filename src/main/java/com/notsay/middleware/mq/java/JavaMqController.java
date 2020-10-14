package com.notsay.middleware.mq.java;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.LinkedBlockingDeque;

/**
 * @description:
 * @author: dsy
 * @date: 2020/9/27 08:55
 */
@RestController
@RequestMapping("/javaMq")
public class JavaMqController {
    LinkedBlockingDeque<String> queue = new LinkedBlockingDeque<>(1000);
    Produce produce = new Produce(1, queue);

    @GetMapping
    @RequestMapping("produce")
    public String produce() {
        produce.produce("====");
        return "produce success";
    }

    @GetMapping
    @RequestMapping("consume")
    public String consume() {
        new Consumer(queue, 1);
        return "consumer success";
    }


}
