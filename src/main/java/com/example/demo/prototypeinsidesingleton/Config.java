package com.example.demo.prototypeinsidesingleton;

import org.springframework.context.annotation.*;

@Configuration
public class Config {

    @Bean
    @Scope("prototype")
    public PrototypeClassByLookup prototypeClass() {
        return new PrototypeClassByLookup();
    }

    @Bean
    @Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public PrototypeClassByProxyMode prototypeClassByProxyMode() {
        return new PrototypeClassByProxyMode();
    }
}
