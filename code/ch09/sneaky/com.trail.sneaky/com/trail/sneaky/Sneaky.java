package com.trail.sneaky;

// a public class in a package that is not exported
import com.trail.greet.internal.Formatter;

public class Sneaky {
    String s = Formatter.box("x");
}
