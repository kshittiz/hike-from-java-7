package com.trail.util;

public class Greeter {
    // public: usable anywhere
    public static String greet(String name) { return "Hello, " + name; }
    // only inside com.trail.util
    static String secret() { return "package-private"; }
}
