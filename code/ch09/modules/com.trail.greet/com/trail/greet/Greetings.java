package com.trail.greet;

import com.trail.greet.internal.Formatter;

public class Greetings {
    public static String hello(String who) {
        return Formatter.box("Hello, " + who);
    }
}
