package com.example.demo.prototypeinsidesingleton;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SingletonClass {

    @Lookup
    public PrototypeClassByLookup getPrototypeClass() {
        return null;
    }

    public final PrototypeClassByProxyMode prototypeClassByProxyMode;

    public void saySomething() {
        System.out.println("hello i'm a singleton " + this);
        getPrototypeClass().saySomething();
        prototypeClassByProxyMode.saySomething();
    }
}
