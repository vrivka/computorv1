package com.app;

import java.util.NavigableMap;
import java.util.TreeMap;

public class Equation {
	private final NavigableMap<Integer, Polynomial> polynomials = new TreeMap<>();
	private int maxDegree = 0;

	public void add(Polynomial polynomial) {
		int degree = polynomial.getDegree();
		if (maxDegree < degree) maxDegree = degree;
		if (polynomials.containsKey(degree)) polynomials.get(degree).add(polynomial);
		else polynomials.put(degree, polynomial);
	}

	public String getSolution() {
		if (maxDegree > 2) return "The polynomial degree is strictly greater than 2, I can't solve";
		Polynomial def = new Polynomial();
		float a = polynomials.getOrDefault(2, def).getFactor();
		float b = polynomials.getOrDefault(1, def).getFactor();
		float c = polynomials.getOrDefault(0, def).getFactor();
		EquationType type = EquationType.getType(a, b, c);
		Solution solution = new Solution(a, b, c);

		return solution.compute(type).toString();
	}

	public int getMaxDegree() {
		return maxDegree;
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();

		polynomials.descendingMap().forEach((d, p) -> {
			if (p.isEmpty()) return ;
			if (res.isEmpty() && !p.isEmpty()) res.append(p.isNegative() ? "-" : "").append(p);
			else if (!p.isEmpty()) res.append(" ").append(p.getSign()).append(" ").append(p);
		});
		return res.append(" = 0").toString();
	}
}
