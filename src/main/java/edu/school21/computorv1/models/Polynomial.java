package edu.school21.computorv1.models;

import edu.school21.computorv1.math.MyMath;

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
		if (degree != rhs.degree) {
			return;
		}
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
		if (isEmpty()) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		double absFactor = MyMath.abs(factor);

		if (degree == 0) {
			result.append(absFactor);
		} else if (degree == 1) {
			if (absFactor != 1) {
				result.append(absFactor);
			}
			result.append(variableName);
		} else {
			if (absFactor != 1) {
				result.append(absFactor);
			}
			result.append(variableName).append("^").append(degree);
		}
		return result.toString();
	}
}
