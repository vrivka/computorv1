package com.app;

import java.util.Arrays;
import java.util.NavigableMap;
import java.util.TreeMap;

public class Equation {
	public String variableName = "x";
	private final NavigableMap<Integer, Polynomial> polynomials = new TreeMap<>();
	private int maxDegree = 0;
	private int minDegree = 0;

	public Equation() {
		polynomials.put(0, new Polynomial());
	}

	public void add(Polynomial polynomial) {
		int degree = polynomial.getDegree();
		if (minDegree > degree) minDegree = degree;
		if (maxDegree < degree) maxDegree = degree;
		if (polynomials.containsKey(degree)) polynomials.get(degree).add(polynomial);
		else polynomials.put(degree, polynomial);
	}

	public String getSolution() {
		if (maxDegree < 0) return "The polynomial degree is strictly less than 0, I can't solve";
		else if (maxDegree > 2) return "The polynomial degree is strictly greater than 2, I can't solve";

		Polynomial def = new Polynomial();
		float a = polynomials.getOrDefault(2, def).getFactor();
		float b = polynomials.getOrDefault(1, def).getFactor();
		float c = polynomials.getOrDefault(0, def).getFactor();
		EquationType type = EquationType.getType(a, b, c);
		Solution solution = new Solution(a, b, c);

		return solution.compute(type, variableName).toString();
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
		if (variableCount == 1)	variableName = a[0];
		else throw new ExceptionInInitializerError("Equation has more than one variable");
	}

	public String getVariableName() {
		return variableName;
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();

		polynomials.descendingMap().forEach((degree, polynomial) -> {
			if (polynomial.isEmpty()) return ;
			if (res.isEmpty() && !polynomial.isEmpty()) {
				res.append(polynomial.isNegative() ? "-" : "")
						.append(polynomial.toString(variableName));
			}
			else if (!polynomial.isEmpty()) {
				res.append(" ")
						.append(polynomial.getSign())
						.append(" ")
						.append(polynomial.toString(variableName));
			}
		});
		return res.append(res.isEmpty() ? "0" : "").append(" = 0").toString();
	}
}
