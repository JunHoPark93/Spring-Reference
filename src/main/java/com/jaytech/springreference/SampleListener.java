package com.jaytech.springreference;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
public class SampleListener {

    public SampleListener(ApplicationArguments arguments) {
        System.out.println("args1 : " + arguments.containsOption("args1"));
        System.out.println("args2 : " + arguments.containsOption("args2"));
    }
}
