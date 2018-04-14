package com.example.demo.beanPostProcessors;

import com.example.demo.annotations.Profiling;
import com.example.demo.controllers.ProfilingControllerMBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProfilingBeanPostProcessor implements BeanPostProcessor {

    private Map<String, Class> map = new HashMap<>();
    private ProfilingControllerMBean profilingControllerMBean;

    @Autowired
    public ProfilingBeanPostProcessor(ProfilingControllerMBean profilingController) {
        this.profilingControllerMBean = profilingController;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class aClass = bean.getClass();
        if (aClass.isAnnotationPresent(Profiling.class)) {
            map.put(beanName, aClass);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class aClass = map.get(beanName);
        if (aClass != null) {
            return Proxy.newProxyInstance(aClass.getClassLoader(), aClass.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                    LocalTime start = LocalTime.now();
                    Object result = method.invoke(bean, objects);
                    LocalTime end = LocalTime.now();
                    long millis = Duration.between(start, end).toMillis();
                    if (profilingControllerMBean.isEnabled()) {
                        System.out.println(String.format("Finished in %d ms", millis));
                    }
                    return result;
                }
            });
        }
        return bean;
    }
}
