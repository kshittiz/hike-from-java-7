package com.trail.sneaky;

import com.trail.greet.internal.Formatter;     // a public class in a package that is not exported

public class Sneaky {
    String s = Formatter.box("x");
}
