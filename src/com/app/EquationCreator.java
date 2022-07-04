package com.app;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EquationCreator {
	static public final String allowedSymbols = "+/=xfd";
	private String equationString;
	private Float[] nums;
	private final Equation equation = new Equation();

	public EquationCreator(String equationString) throws ExceptionInInitializerError {
		equation.setVariableName(equationString);
		this.equationString = equationString.replaceAll(" ", "")
				.replaceAll(equation.getVariableName(), "x");
	}

	private void setNums() throws NumberFormatException {
		Pattern pattern = Pattern.compile("[-]?\\d+(\\.\\d+)?");
		Matcher matcher = pattern.matcher(equationString);

		nums = matcher.results()
				.map(MatchResult::group)
				.map(Float::parseFloat)
				.toArray(Float[]::new);

		equationString = equationString
				.replaceAll("\\d+(\\.\\d+)?", "f")
				.replaceAll("x\\^f", "d")
				.replaceAll("[*]", "")
				.replaceAll("-", "+");
	}

	public Equation getEquation() {
		setNums();

		if (!containAllowedSymbols()) throw new IllegalArgumentException("Equation string has illegal symbols");

		String[] a = equationString.split("=");
		if (a.length != 2) throw new IllegalArgumentException("More than one equal sign");

		int numIndex = loadPolynomials(a[0], new Polynomial(1, 0), 0);
		loadPolynomials(a[1], new Polynomial(-1, 0), numIndex);

		return equation;
	}

	private int loadPolynomials(String s, Polynomial sign, int numIndex) {
		String[] a = Arrays.stream(s.split("\\+"))
				.filter(str -> !str.isEmpty())
				.toArray(String[]::new);

		for (String str : a) {
			Polynomial p = new Polynomial(1, 0);
			char[] cs = str.toCharArray();
			Consumer<Polynomial> func = p::multi;

			for (char c : cs) {
				if (c == '/') {
					func = p::div;
					continue ;
				}
				else if (c == 'f') {
					Polynomial pp = new Polynomial(nums[numIndex], 0);
					func.accept(pp);
					++numIndex;
				}
				else if (c == 'd') {
					Polynomial pp = new Polynomial(1, nums[numIndex].intValue());
					func.accept(pp);
					++numIndex;
				}
				else if (c == 'x') {
					Polynomial pp = new Polynomial(1, 1);
					func.accept(pp);
				}
				func = p::multi;
			}
			p.multi(sign);
			equation.add(p);
		}
		return numIndex;
	}

	private boolean containAllowedSymbols() {
		return equationString.chars()
				.distinct()
				.allMatch(c -> allowedSymbols.contains(String.valueOf((char)c)));
	}

	@Override
	public String toString() {
		return  equationString + '\n' +
				Arrays.toString(nums);
	}
}
