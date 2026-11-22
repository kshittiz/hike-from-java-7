package com.notes.greet;

import com.notes.greet.internal.Formatter;

public class Greetings {
    public static String hello(String who) { return Formatter.box("Hello, " + who); }
}
