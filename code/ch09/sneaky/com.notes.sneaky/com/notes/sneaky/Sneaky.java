package com.notes.sneaky;

import com.notes.greet.internal.Formatter;     // a public class in a package that is not exported

public class Sneaky {
    String s = Formatter.box("x");
}
