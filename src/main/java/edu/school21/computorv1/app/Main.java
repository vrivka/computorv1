package edu.school21.computorv1.app;

import edu.school21.computorv1.logic.Equation;
import edu.school21.computorv1.logic.EquationCreator;
import edu.school21.computorv1.utils.Printer;

import java.util.Scanner;

public class Main {
    private static final String INTER_STEPS_ARGUMENT = "-inter";
    private static final int ERROR_CODE = -1;
    private static final String EXIT_COMMAND = "exit";
    public static boolean interStepsFlag = false;
    private static boolean stdinFlag = false;
    private static String line = "";


    public static void main(String[] args) {
        if (args.length == 1) {
            if (args[0].equals(INTER_STEPS_ARGUMENT)) {
                interStepsFlag = true;
                stdinFlag = true;
            } else {
                line = args[0];
            }
        } else if (args.length == 2) {
            if (args[0].equals(INTER_STEPS_ARGUMENT)) {
                interStepsFlag = true;
                line = args[1];
            } else {
                errorExit("If 2 arguments are specified, " +
                          "then the first one should be the argument " +
                          "that turns on the intermediate steps display " +
                          "mode (-inter)");
            }
        } else if (args.length > 2) {
            errorExit("Only 1 or 2 arguments are specified:\n" +
                      "1) '-inter' or '<equation>';\n" +
                      "2) '-inter <equation>'.");
        } else {
            stdinFlag = true;
        }

        try (Scanner scanner = new Scanner(System.in)) {
            if (stdinFlag) {
                while (getLine(scanner) && !line.equalsIgnoreCase(EXIT_COMMAND)) {
                    solve(line);
                }
            } else {
                solve(line);
            }
        } catch (Exception e) {
            errorExit(e.getMessage());
        }
    }

    private static void errorExit(String errorMessage) {
        Printer.errPrintln(errorMessage);
        System.exit(ERROR_CODE);
    }

    private static boolean getLine(Scanner scanner) {
        if (scanner.hasNextLine()) {
            line = scanner.nextLine();
            return true;
        }
        return false;
    }

    private static void solve(String line) {
        Equation equation = EquationCreator.getEquation(line);

        Printer.stdPrintln("Reduced form: " + equation);
        Printer.stdPrintln("Polynomial degree: " + equation.getMaxDegree());
        Printer.stdPrintln(equation.getSolutionString());
    }
}
