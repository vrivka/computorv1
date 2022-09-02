package edu.school21.computorv1.logic;

import edu.school21.computorv1.logic.exceptions.MultivariableError;
import edu.school21.computorv1.models.Polynomial;
import edu.school21.computorv1.utils.Printer;

import java.util.Arrays;
import java.util.NavigableMap;
import java.util.TreeMap;

import static edu.school21.computorv1.utils.Printer.getIncorrectString;
import static edu.school21.computorv1.utils.Printer.interSteps;

public class Equation {
    private final NavigableMap<Integer, Polynomial> polynomials;
    private String variableName;
    private int maxDegree;
    private int minDegree;

    public Equation() {
        this.variableName = "x";
        this.maxDegree = 0;
        this.minDegree = 0;
        this.polynomials = new TreeMap<>();
        polynomials.put(0, new Polynomial());
    }

    public void add(Polynomial polynomial) {
        int degree = polynomial.getDegree();

        if (minDegree > degree) {
            minDegree = degree;
        }
        if (maxDegree < degree) {
            maxDegree = degree;
        }
        if (polynomials.containsKey(degree)) {
            polynomials.get(degree).add(polynomial);
        } else {
            polynomials.put(degree, polynomial);
        }
    }

    public String getSolutionString() {
        if (maxDegree < 0) {
            return getIncorrectString("The polynomial degree is strictly less than 0, I can't solve");
        } else if (maxDegree > 2) {
            return getIncorrectString("The polynomial degree is strictly greater than 2, I can't solve");
        }

        Polynomial def = new Polynomial();
        float a = polynomials.getOrDefault(2, def).getFactor();
        float b = polynomials.getOrDefault(1, def).getFactor();
        float c = polynomials.getOrDefault(0, def).getFactor();

        interSteps("Factors:\n\ta = " + a + "\n\tb = " + b + "\n\tc = " + c);

        EquationType type = EquationType.getType(a, b, c);
        Solution solution = new Solution(a, b, c, variableName);

        return solution.compute(type).toString();
    }

    public int getMaxDegree() {
        return maxDegree;
    }

    public void setVariableName(String equationString) throws ExceptionInInitializerError {
        String[] a = Arrays.stream(equationString.split("[^a-zA-Z]"))
                .filter(s -> !s.isEmpty())
                .distinct()
                .toArray(String[]::new);
        int variableCount = a.length;

        if (variableCount == 1) {
            variableName = a[0];
        } else if (variableCount > 1) {
            throw new MultivariableError("Equation has more than one variable");
        }
    }

    public String getVariableName() {
        return variableName;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        polynomials.descendingMap().forEach((degree, polynomial) -> {
            if (polynomial.isEmpty()) {
                return;
            }
            if (res.isEmpty() && !polynomial.isEmpty()) {
                res.append(polynomial.isNegative() ? "-" : "")
                        .append(polynomial.toString(variableName));
            } else if (!polynomial.isEmpty()) {
                res.append(" ")
                        .append(polynomial.getSign())
                        .append(" ")
                        .append(polynomial.toString(variableName));
            }
        });
        return res.append(res.isEmpty() ? "0" : "").append(" = 0").toString();
    }
}
