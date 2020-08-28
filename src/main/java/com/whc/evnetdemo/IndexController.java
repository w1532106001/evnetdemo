package com.whc.evnetdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author whc
 * @date 2020/8/28 23:39
 */
@RestController
public class IndexController {

    @Resource
    private EventDemoPublish eventDemoPublish;

    @GetMapping("/push")
    public String push() {
        eventDemoPublish.publish("处理订单");
        return "结束";
    }
}
