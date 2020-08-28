package com.whc.evnetdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.ResolvableType;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/**
 * @author whc
 * @date 2020/8/29 2:16
 */
@Component("applicationEventMulticaster")
public class BaseApplicationEventMulticaster extends SimpleApplicationEventMulticaster {
    private static final Logger log = LoggerFactory.getLogger(BaseApplicationEventMulticaster.class);

    public BaseApplicationEventMulticaster() {
        this.setTaskExecutor(new SimpleAsyncTaskExecutor());
    }


    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void multicastEvent(final ApplicationEvent event, @Nullable ResolvableType eventType) {
        //默认同步
        EventTypeEnum defaultEventType = EventTypeEnum.SYNC;
        for (ApplicationListener listener : getApplicationListeners()) {
            try {
                Class listenerClass = Class.forName(listener.getClass().getName());
                Method onApplicationEventMethod = listenerClass.getMethod("onApplicationEvent", ApplicationEvent.class);
                if (onApplicationEventMethod.isAnnotationPresent(EventType.class)) {
                    //获取该元素上指定类型的注解
                    EventType eventMethodAnnotation = onApplicationEventMethod.getAnnotation(EventType.class);
                    defaultEventType = eventMethodAnnotation.value();
                }
            } catch (Exception e) {
                log.error("获取监听类实例出错~");
            }

            Executor executor = getTaskExecutor();
            if (executor != null && defaultEventType == EventTypeEnum.ASYNC) {
                executor.execute(() ->
                        this.invokeListener(listener, event)
                );
            } else {
                this.invokeListener(listener, event);
            }
        }
    }
}
