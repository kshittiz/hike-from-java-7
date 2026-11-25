package com.trail.greet.internal;

// public, but in a package the module does not export
public class Formatter {
    public static String box(String s) { return "[ " + s + " ]"; }
}
