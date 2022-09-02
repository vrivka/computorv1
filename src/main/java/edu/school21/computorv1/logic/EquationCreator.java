package edu.school21.computorv1.logic;

import edu.school21.computorv1.logic.exceptions.SyntaxError;
import edu.school21.computorv1.models.Polynomial;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EquationCreator {
    private static final String ALLOWED_SYMBOLS = "+/=xfd";
    private static Float[] nums;

    public static Equation getEquation(String equationString) {
        Equation equation = new Equation();

        equation.setVariableName(equationString);
        equationString = equationString.replaceAll(" ", "")
                .replaceAll(equation.getVariableName(), "x");
        equationString = setNums(equationString);

        if (!containAllowedSymbols(equationString)) {
            throw new SyntaxError("Equation string has illegal symbols");
        }

        String[] twoSides = equationString.split("=");

        if (twoSides.length != 2) {
            throw new SyntaxError("More than one equal sign");
        }

        int numIndex = loadPolynomials(equation, twoSides[0], new Polynomial(1, 0), 0);

        loadPolynomials(equation, twoSides[1], new Polynomial(-1, 0), numIndex);
        return equation;
    }

    private static String setNums(String equationString) throws NumberFormatException {
        Pattern pattern = Pattern.compile("[-]?\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(equationString);

        nums = matcher.results()
                .map(MatchResult::group)
                .map(Float::parseFloat)
                .toArray(Float[]::new);

        return equationString
                .replaceAll("\\d+(\\.\\d+)?", "f")
                .replaceAll("x\\^f", "d")
                .replaceAll("[*]", "")
                .replaceAll("-", "+");
    }

    private static int loadPolynomials(Equation equation, String side, Polynomial sign, int numIndex) {
        String[] splitPolynomials = Arrays.stream(side.split("\\+"))
                .filter(str -> !str.isEmpty())
                .toArray(String[]::new);

        for (String polynomial : splitPolynomials) {
            Polynomial resultPolynomial = new Polynomial(1, 0);
            char[] charArray = polynomial.toCharArray();
            Consumer<Polynomial> polynomialConsumer = resultPolynomial::multi;

            for (char character : charArray) {
                if (character == '/') {
                    polynomialConsumer = resultPolynomial::div;
                    continue;
                } else if (character == 'f') {
                    Polynomial pp = new Polynomial(nums[numIndex], 0);

                    polynomialConsumer.accept(pp);
                    ++numIndex;
                } else if (character == 'd') {
                    Polynomial pp = new Polynomial(1, nums[numIndex].intValue());

                    polynomialConsumer.accept(pp);
                    ++numIndex;
                } else if (character == 'x') {
                    Polynomial pp = new Polynomial(1, 1);

                    polynomialConsumer.accept(pp);
                }
                polynomialConsumer = resultPolynomial::multi;
            }
            resultPolynomial.multi(sign);
            equation.add(resultPolynomial);
        }
        return numIndex;
    }

    private static boolean containAllowedSymbols(String equationString) {
        return equationString.chars()
                .distinct()
                .allMatch(c -> ALLOWED_SYMBOLS.contains(String.valueOf((char) c)));
    }
}
