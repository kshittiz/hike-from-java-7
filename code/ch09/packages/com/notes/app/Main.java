package com.notes.app;

import com.notes.util.Greeter;                 // a single-type import
import static java.lang.Math.max;              // a static import (Java 5)

public class Main {
    public static void main(String[] args) {
        System.out.println(Greeter.greet("packages"));
        System.out.println("max = " + max(7, 27));
    }
}
