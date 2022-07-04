package com.app;

public class Main {
    public static void main(String[] args) {
        String s = "-4.5n^2 / n^1 - 3.3n + 4 = 0";

		EquationCreator e = new EquationCreator(s);

		System.out.println(e.getEquation().getSolution());
		System.out.println(e);
    }
}
