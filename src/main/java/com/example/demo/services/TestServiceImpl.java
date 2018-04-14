package com.example.demo.services;

import com.example.demo.annotations.InjectRandomInteger;
import com.example.demo.annotations.PostProxy;
import com.example.demo.annotations.Profiling;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Data
@Service
@Scope(value = "prototype", proxyMode = ScopedProxyMode.INTERFACES)
@Profiling
public class TestServiceImpl implements TestService {

    @InjectRandomInteger(min = 0, max = 100000)
    private int testValue;

    public TestServiceImpl() {
        System.out.println("Phase 1: Constructor");
    }

    @PostConstruct
    public void init() {
        System.out.println("Phase 2: PostConstruct (init)");
    }

    @PostProxy
    public void setUpCache(){
        System.out.println("Phase 3: PostProxyConstruct (cache)");
    }

    @Override
    public void doSomeLogic() {
        System.out.println(String.format("Serving %d jobs", testValue));
    }
}
