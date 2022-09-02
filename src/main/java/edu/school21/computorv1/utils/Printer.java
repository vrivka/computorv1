package edu.school21.computorv1.utils;

import edu.school21.computorv1.app.Main;

public class Printer {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static String getCorrectString(String string) {
        if (Main.interStepsFlag) {
            return ANSI_GREEN + string + ANSI_RESET;
        }
        return string;
    }

    public static String getIncorrectString(String string) {
        return ANSI_RED + string + ANSI_RESET;
    }

    public static void interSteps(String string) {
        if (Main.interStepsFlag) {
            System.out.println(ANSI_CYAN + string + ANSI_RESET);
        }
    }

    public static void stdPrintln(String string) {
        System.out.println(string);
    }

    public static void errPrintln(String string) {
        System.err.println(getIncorrectString(string));
    }
}
