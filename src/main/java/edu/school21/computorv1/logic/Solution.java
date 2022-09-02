package edu.school21.computorv1.logic;

import edu.school21.computorv1.math.MyMath;
import edu.school21.computorv1.utils.Printer;

import static edu.school21.computorv1.utils.Printer.*;

public class Solution {
    private final String variableName;
    private final float a;
    private final float b;
    private final float c;
    private String result1 = "The equation has no solution";
    private String result2 = null;

    public Solution(float a, float b, float c, String variableName) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.variableName = variableName;
    }

    public Solution compute(EquationType type) {
        if (type == EquationType.LINEAR) {
            return computeLinear();
        } else if (type == EquationType.COMPLETE_QUADRATIC) {
            return computeCompleteQuadratic();
        } else if (type == EquationType.INCOMPLETE_QUADRATIC) {
            return computeIncompleteQuadratic();
        }
        return this;
    }

    private Solution computeLinear() {
        interSteps("We have equation form b" + variableName + " + c = 0");
        if (b == 0 && c == 0) {
            interSteps("'b' and 'c' equals to 0");
            interSteps("0" + variableName + " + 0 = 0");
            interSteps("0 = 0");
            result1 = variableName + " ∈ ℝ. Variable " + variableName + " is any real number";
        } else if (b == 0) {
            interSteps("'b' equals to 0");
            interSteps("0" + variableName + " + c = 0");
            interSteps("c = 0");
            interSteps("c != 0");
            result1 = getIncorrectString("The equation has no solution");
        } else if (c == 0) {
            Printer.stdPrintln("There is one solution:");
            interSteps("'c' equals to 0");
            interSteps("b" + variableName + " + 0 = 0");
            interSteps(variableName + " = 0 / b");
            result1 = variableName + " = 0";
        } else {
            Printer.stdPrintln("There is one solution:");
            interSteps("'b' and 'c' not equals to 0");
            interSteps("b" + variableName + " + c = 0");
            interSteps(variableName + " = -c / b");
            result1 = variableName + " = " + -c / b;
        }
        return this;
    }

    private Solution computeCompleteQuadratic() {
        double discriminant = b * b - 4 * a * c;

        interSteps("We have equation form a" + variableName + "^2 + b" + variableName + " + c = 0");
        interSteps("Lets find discriminant by formula b^2 - 4ac:\n\tD = " + b + "^2 - 4 * " + a + " * " + c + " = " + discriminant);
        if (discriminant == 0) {
            Printer.stdPrintln("Discriminant is zero, the one solution:");

            interSteps("Lets find solution by formula -b / 2a");
            result1 = variableName + " = " + -b / (2 * a);
        } else if (discriminant > 0) {
            Printer.stdPrintln("Discriminant is strictly positive, the two solutions are:");

            interSteps("Lets find solutions by formula (-b ± √D) / 2a");
            result1 = variableName + "₁ = " + (-b + MyMath.sqrt(discriminant)) / (2 * a);
            result2 = variableName + "₂ = " + (-b - MyMath.sqrt(discriminant)) / (2 * a);
        } else if (discriminant < 0) {
            Printer.stdPrintln("Discriminant is strictly negative, the two complex solutions are:");

            interSteps("Lets find complex solutions by formula -b / 2a ± √-D * i / 2a");
            discriminant = MyMath.sqrt(-discriminant) / (2 * a);
            result1 = variableName + "₁ = " + -b / (2 * a) + (discriminant < 0 ? " - " + -discriminant : " + " + discriminant) + 'i';
            result2 = variableName + "₂ = " + -b / (2 * a) + (discriminant < 0 ? " + " + -discriminant : " - " + discriminant) + 'i';
        }
        return this;
    }

    private Solution computeIncompleteQuadratic() {
        interSteps("We have equation form a" + variableName + "^2" +
                   ((b == 0 && c == 0) ? " = 0" :
                           (b == 0 ? " + c = 0" :
                                   " + b" + variableName + " = 0")));

        if (b == 0 && c == 0) {
            Printer.stdPrintln("There are one solution:");
            interSteps(variableName + "^2 = 0 / a ");
            interSteps(variableName + " = √0");
            result1 = variableName + " = 0";
        } else if (b == 0) {
            float result = -c / a;

            Printer.stdPrintln("There are two " + (result < 0 ? "complex " : "") + "solutions:");
            interSteps("a" + variableName + "^2 = -c");
            interSteps(variableName + "^ 2 = -c / a");
            interSteps(variableName + " = √(-c / a)");
            interSteps("Lets find R = -c / a");

            if (result > 0) {
                interSteps("R > 0. Lets find solutions by formula " + variableName + " = ±√R");
                result1 = variableName + "₁ = " + MyMath.sqrt(result);
                result2 = variableName + "₂ = -" + MyMath.sqrt(result);
            } else if (result < 0) {
                interSteps("R < 0. Lets find complex solutions by formula " + variableName + " = ±√-R * i");
                result1 = variableName + "₁ = " + MyMath.sqrt(-result) + 'i';
                result2 = variableName + "₂ = -" + MyMath.sqrt(-result) + 'i';
            }
        } else {
            Printer.stdPrintln("There are two solutions:");
            interSteps(variableName + "(a" + variableName + " + b) = 0");
            interSteps(variableName + " = 0 and a" + variableName + " + b = 0");
            interSteps("a" + variableName + " = -b");
            interSteps(variableName + " = -b / a");
            result1 = variableName + "₁ = 0";
            result2 = variableName + "₂ = " + -b / a;
        }
        return this;
    }

    @Override
    public String toString() {
        if (result2 != null) {
            return getCorrectString(result1 + '\n' + result2);
        }
        return getCorrectString(result1);
    }
}
