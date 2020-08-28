package com.whc.evnetdemo;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author whc
 * @date 2020/8/28 23:55
 */
@Component
public class EventDemoListener2 implements ApplicationListener<EventDemo> {

    @EventType(value = EventTypeEnum.ASYNC)
    @Override
    public void onApplicationEvent(EventDemo event) {
        System.out.println("任务2线程名："+Thread.currentThread().getName());
        System.out.println("开始处理任务2");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("receiver2" + event.getMessage());
        System.out.println("任务结束2");
    }
}
