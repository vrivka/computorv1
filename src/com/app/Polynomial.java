package com.app;

public class Polynomial {
	private boolean negative;
	private float factor;
	private final int degree;

	public Polynomial(float factor, int degree) {
		this.negative = factor < 0;
		this.factor = factor;
		this.degree = degree;
	}

	public void add(Polynomial rhs) {
		factor += rhs.factor;
		negative = factor < 0;
	}

	public float getFactor() {
		return factor;
	}

	public boolean isSameDegree(Polynomial p) {
		return degree == p.degree;
	}

	public boolean isNegative() {
		return negative;
	}

	@Override
	public String toString() {
		return (isNegative() ? -factor : factor) + " * " + Main.variable_name + "^" + degree;
	}
}
