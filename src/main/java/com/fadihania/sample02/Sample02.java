package com.fadihania.sample02;

import static java.lang.System.lineSeparator;


public class Sample02 {
    public static void run() {
        System.out.println(TextExtractor.parse("test.pdf").trim());
        System.out.println(lineSeparator() + "----------------------------------------" + lineSeparator());
        System.out.println(TextExtractor.parse("test.txt"));
    }
}