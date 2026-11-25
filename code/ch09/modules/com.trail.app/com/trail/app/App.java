package com.trail.app;

import com.trail.greet.Greetings;

public class App {
    public static void main(String[] args) {
        System.out.println(Greetings.hello("modules"));
        System.out.println("running in module: "
                + App.class.getModule().getName());
    }
}
