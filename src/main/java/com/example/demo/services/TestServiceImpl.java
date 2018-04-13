package com.example.demo.services;

import com.example.demo.annotations.InjectRandomInteger;
import com.example.demo.annotations.Profiling;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Data
@Service
@Scope(value = "prototype", proxyMode = ScopedProxyMode.INTERFACES)
@Profiling
public class TestServiceImpl implements TestService {

    @InjectRandomInteger(min = 0, max = 100000)
    private int testValue;

    @Override
    public void doSomeLogic() {
        System.out.println(String.format("Serving %d jobs", testValue));
        while (testValue > 0) {
            testValue--;
        }
    }
}
