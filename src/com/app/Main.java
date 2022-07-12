package com.app;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String s;
        if (args.length == 0) s = new Scanner(System.in).nextLine();
        else s = args[0];
        Equation equation = new EquationCreator(s).getEquation();

        System.out.println("Reduced form: " + equation);
        System.out.println("Polynomial degree: " + equation.getMaxDegree());
        System.out.println(equation.getSolution());
    }
}
