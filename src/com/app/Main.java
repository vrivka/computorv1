package com.app;

import java.util.Arrays;

public class Main {
    static public final String variable_name = "x";

    public static void main(String[] args) {
        String s = " =0";

        EquationParser ep = new EquationParser(s);

        ep.getEquation();
    }
}
