package com.example.demo.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface InjectRandomInteger {

    int min();

    int max();
}
