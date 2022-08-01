package edu.school21.computorv1.logic;

import edu.school21.computorv1.math.MyMath;

public class Solution {
    private final float a;
    private final float b;
    private final float c;

    private String result1 = "The equation has no solution";
    private String result2 = null;

    public Solution(float a, float b, float c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Solution compute(EquationType type, String variableName) {
        if (type == EquationType.LINEAR) {
            return computeLinear(variableName);
        } else if (type == EquationType.COMPLETE_QUADRATIC) {
            return computeCompleteQuadratic(variableName);
        } else if (type == EquationType.INCOMPLETE_QUADRATIC) {
            return computeIncompleteQuadratic(variableName);
        }
        return this;
    }

    private Solution computeLinear(String variableName) {
        if (b == 0 && c == 0) {
            result1 = variableName + " ∈ ℝ. Variable " + variableName + " is any real number";
        } else if (b == 0) {
            result1 = "The equation has no solution";
        } else if (c == 0) {
            System.out.println("The equation is linear, there is one solution:");
            result1 = variableName + " = 0";
        } else {
            System.out.println("The equation is linear, there is one solution:");
            result1 = variableName + " = " + -(c / b);
        }
        return this;
    }

    private Solution computeCompleteQuadratic(String variableName) {
        double discriminant = b * b - 4 * a * c;

        if (discriminant == 0) {
            System.out.println("Discriminant is null, the one solution:");

            result1 = variableName + " = " + -(b / (2 * a));
        } else if (discriminant > 0) {
            System.out.println("Discriminant is strictly positive, the two solutions are:");

            result1 = variableName + "₁ = " + (-b + MyMath.sqrt(discriminant)) / (2 * a);
            result2 = variableName + "₂ = " + (-b - MyMath.sqrt(discriminant)) / (2 * a);
        } else if (discriminant < 0) {
            System.out.println("Discriminant is strictly positive, the two solutions are:");

            discriminant = MyMath.sqrt(-discriminant) / (2 * a);
            result1 = variableName + "₁ = " + -b / (2 * a) + (discriminant < 0 ? " - " + -discriminant : " + " + discriminant) + 'i';
            result2 = variableName + "₂ = " + -b / (2 * a) + (discriminant < 0 ? " + " + -discriminant : " - " + discriminant) + 'i';
        }
        return this;
    }

    private Solution computeIncompleteQuadratic(String variableName) {
        System.out.println("Incomplete quadratic equation, there are two solutions:");

        if (b == 0 && c == 0) {
            result1 = variableName + " = 0";
        } else if (b == 0) {
            float result = -c / a;

            if (result > 0) {
                result1 = variableName + "₁ = " + MyMath.sqrt(result);
                result2 = variableName + "₂ = -" + MyMath.sqrt(result);
            } else if (result < 0) {
                result1 = variableName + "₁ = " + MyMath.sqrt(-result) + 'i';
                result2 = variableName + "₂ = -" + MyMath.sqrt(-result) + 'i';
            }
        } else {
            result1 = variableName + "₁ = 0";
            result2 = variableName + "₂ = " + -b / a;
        }
        return this;
    }

    @Override
    public String toString() {
        if (result2 != null) {
            return result1 + '\n' + result2;
        }
        return result1;
    }
}
