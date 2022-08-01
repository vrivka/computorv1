package edu.school21.computorv1.app;

import edu.school21.computorv1.logic.Equation;
import edu.school21.computorv1.logic.EquationCreator;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String line;

        if (args.length == 0) {
            line = new Scanner(System.in).nextLine();
        } else {
            line = args[0];
        }
        Equation equation = new EquationCreator(line).getEquation();

        System.out.println("Reduced form: " + equation);
        System.out.println("Polynomial degree: " + equation.getMaxDegree());
        System.out.println(equation.getSolutionString());
    }
}
