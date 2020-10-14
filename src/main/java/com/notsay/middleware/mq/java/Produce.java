package com.notsay.middleware.mq.java;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.BlockingQueue;

/**
 * @description: 生产者
 * @author: dsy
 * @date: 2020/9/25 15:09
 */
@Data
@NoArgsConstructor
public class Produce {

    private int id;

    private BlockingQueue<String> queue;


    public void produce(String message) {
        boolean add = queue.add(message);
        if (add) {
            System.out.println("生产者" + id + "号,产生了一条消息:" + message);
        }
    }

    public Produce(int id, BlockingQueue<String> queue) {
        this.id = id;
        this.queue = queue;
        System.out.println("创建了生产者" + id + "号");
    }
}
