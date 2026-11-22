package com.notes.util;

public class Greeter {
    public static String greet(String name) { return "Hello, " + name; }   // public: usable anywhere
    static String secret() { return "package-private"; }                   // only inside com.notes.util
}
