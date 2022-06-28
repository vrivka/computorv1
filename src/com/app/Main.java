package com.app;

public class Main {
    static public String variable_name = "x";

    public static void main(String[] args) {
        Equation equation = new Equation();

        equation.add(new Polynomial(-1, 2));
        equation.add(new Polynomial(-1, 1));
        equation.add(new Polynomial(-1, 0));

        System.out.println(equation.getSolution());
    }
}
