package com.computerv1;

public class Main {
    static public String variable_name = "x";

    public static void main(String[] args) {
        Equation equation = new Equation();

        equation.add(new Polynom(-1, 2));
        equation.add(new Polynom(-1, 1));
        equation.add(new Polynom(-1, 0));

        System.out.println(equation.getSolution());
    }
}
