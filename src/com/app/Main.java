package com.app;

import java.util.Arrays;

public class Main {
    static public String variable_name = "x";

    public static void main(String[] args) {
        String s = "-4x^2+2x-12=5x-3x^2+9";

        String[] sa = s.split("=");
        if (sa.length != 2) System.out.println("error");

        sa = Arrays.stream(sa)
                .map(str -> {
                    str = str.replaceAll(" ", "");
                    char firstChar = str.charAt(0);

                    if (firstChar != '-') str = "+" + str;
                    str = str.replaceAll("\\+", " +");
                    return str;
                })
                .toArray(String[]::new);

        sa[0] = sa[0].replaceAll("-", " -");
        sa[1] = sa[1].replaceAll("-", "+").replaceAll(" \\+", " -");
        sa[1] = sa[1].replaceAll("\\+", " +");
        s = sa[0] + sa[1];
        System.out.println(s);
    }
}
