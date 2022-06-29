package com.app;

import java.util.Arrays;
import java.util.stream.Stream;

public class EquationParser {
	static public final String allowedSymbols = " +-*=^x0123456789";
	private final String equationString;

	public EquationParser(String equationString) {
		this.equationString = equationString;
		if (!containAllowedSymbols()) System.exit(1);
	}

	public Equation getEquation() {
		String[] tmp = equationString.replaceAll(" ", "").split("=");

		if (tmp.length != 2) System.exit(1);

		String[] lhs = getLeftSideTokens(tmp[0]);
		String[] rhs = getRightSideTokens(tmp[1]);

		tmp = Stream.concat(Arrays.stream(lhs), Arrays.stream(rhs))
				.filter(s -> !s.isEmpty())
				.toArray(String[]::new);

		System.out.println(tmp.length);
		System.out.println(Arrays.toString(tmp));

		return null;
	}

	private String[] getLeftSideTokens(String s) {
		String tmp = s.replaceAll("\\+", " ").replaceAll("-", " -");
		return tmp.split(" ");
	}

	private String[] getRightSideTokens(String s) {
		if (s.charAt(0) != '-' && s.charAt(0) != '0') s = "+" + s;
		String tmp = s.replaceAll("-", " ").replaceAll("\\+", " -");
		return tmp.split(" ");
	}

	private boolean containAllowedSymbols() {
		return equationString.chars()
				.distinct()
				.allMatch(c -> allowedSymbols.contains(String.valueOf((char)c)));
	}
}
