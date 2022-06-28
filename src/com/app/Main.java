package com.app;

public class Main {
    static public String variable_name = "x";

    public static void main(String[] args) {
        Equation e = new Equation();

        e.add(new Polynomial(4, 2));
        e.add(new Polynomial(2, 1));
        e.add(new Polynomial(-12, 0));

        System.out.println("Reduced form: " + e);
        System.out.println("Polynomial degree: " + e.getMaxDegree());
        System.out.println(e.getSolution());
    }
}
