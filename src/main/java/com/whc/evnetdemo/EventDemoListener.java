package com.whc.evnetdemo;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author whc
 * @date 2020/8/28 22:52
 * 定义一个事件监听者
 */
@Component
public class EventDemoListener implements ApplicationListener<EventDemo> {

    @EventType(value = EventTypeEnum.SYNC)
    @Override
    public void onApplicationEvent(EventDemo eventDemo) {
        System.out.println("任务1线程名："+Thread.currentThread().getName());
        System.out.println("开始处理任务1");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("receiver1" + eventDemo.getMessage());
        System.out.println("任务结束1");
    }
}
