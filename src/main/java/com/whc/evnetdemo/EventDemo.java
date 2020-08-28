package com.whc.evnetdemo;

import org.springframework.context.ApplicationEvent;

/**
 * @author whc
 * @date 2020/8/28 22:51
 */
public class EventDemo extends ApplicationEvent {

    private String message;
    public EventDemo(Object source,String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
