package com.whc.evnetdemo;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author whc
 * @date 2020/8/28 22:55
 * 事件发布
 */
@Component
public class EventDemoPublish {

    @Resource
    private ApplicationEventPublisher applicationEventPublisher;

    public void publish(String message) {
        EventDemo eventDemo = new EventDemo(this, message);
        applicationEventPublisher.publishEvent(eventDemo);
    }
}
