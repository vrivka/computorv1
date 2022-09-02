package edu.school21.computorv1.logic;

import static edu.school21.computorv1.utils.Printer.interSteps;

public enum EquationType {
    LINEAR, COMPLETE_QUADRATIC, INCOMPLETE_QUADRATIC;

    public static EquationType getType(float a, float b, float c) {
        if (a == 0) {
            interSteps("'a' equals 0, is linear equation");
            return LINEAR;
        } else if (b == 0 || c == 0) {
            interSteps("'a' not equals 0 and 'b' or 'c' equals to 0, is incomplete quadratic equation");
            return INCOMPLETE_QUADRATIC;
        } else {
            interSteps("'a', 'b' or 'c' not equals to 0, is complete quadratic equation");
            return COMPLETE_QUADRATIC;
        }
    }
}
