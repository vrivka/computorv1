package com.app;

public class Polynomial {
	private int degree = 0;
	private float factor = 0;
	private boolean negative = false;

	public Polynomial() {}

	public Polynomial(float factor, int degree) {
		this.degree = degree;
		this.factor = factor;
		this.negative = factor < 0;
	}

	public void add(Polynomial rhs) {
		if (degree != rhs.degree) return ;
		factor += rhs.factor;
		negative = factor < 0;
	}

	public void multi(Polynomial rhs) {
		degree += rhs.degree;
		factor *= rhs.factor;
		negative = factor < 0;
	}

	public void div(Polynomial rhs) {
		degree -= rhs.degree;
		factor /= rhs.factor;
		negative = factor < 0;
	}

	public float getFactor() {
		return factor;
	}

	public int getDegree() {
		return degree;
	}

	public boolean isSameDegree(Polynomial p) {
		return degree == p.degree;
	}

	public boolean isNegative() {
		return negative;
	}

	public boolean isEmpty() {
		return factor == 0;
	}

	public String getSign() {
		return isNegative() ? "-" : "+";
	}

	public String toString(String variableName) {
		if (isEmpty()) return "";
		StringBuilder res = new StringBuilder();
		float f = isNegative() ? -factor : factor;

		if (degree == 0) {
			res.append(f);
		} else if (degree == 1) {
			if (f != 1) res.append(f);
			res.append(variableName);
		} else {
			if (f != 1) res.append(f);
			res.append(variableName).append("^").append(degree);
		}
		return res.toString();
	}
}
