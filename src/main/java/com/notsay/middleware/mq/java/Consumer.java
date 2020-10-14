package com.notsay.middleware.mq.java;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.*;

/**
 * @description: 消费者
 * @author: dsy
 * @date: 2020/9/26 15:26
 */
@Data
@NoArgsConstructor
public class Consumer {

    private int id;

    private BlockingQueue<String> queue;

    private static ScheduledExecutorService executors = Executors.newScheduledThreadPool(1);

    public Consumer(BlockingQueue<String> queue, int id) {
        this.id = id;
        this.queue = queue;
        System.out.println("创建了消费者：" + id + "号");
        consumer();
    }

    public void consumer() {

        executors.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("消费者手里的线程池核武器收到了一个命令，此时队列中的任务数" + queue.size() + "个");
                try {
                    String message = queue.take();
                    System.out.println("消费者： " + id + "号，开始消费了");
                    Thread.sleep(3000);
                    System.out.println("消费者： " + id + "消费结束了，" + message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 5, TimeUnit.SECONDS);

    }

    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingDeque<String> queue = new LinkedBlockingDeque<>(1000);
        Produce produce = new Produce(1, queue);
        new Consumer(queue, 1);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (int i = 0; i < 20; i++) {
            Thread.sleep(2000);
            produce.produce("@" + i + "@");
        }
    }
}
