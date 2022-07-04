package com.app;

public class Solution {
	private final float a;
	private final float b;
	private final float c;

	private String res1 = "The equation has no solution";
	private String res2 = null;

	public Solution(float a, float b, float c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public Solution compute(EquationType type, String variableName) {
		if (type == EquationType.LINEAR) return computeLinear(variableName);
		else if (type == EquationType.COMPLETE_QUADRATIC) return computeCompleteQuadratic(variableName);
		else if (type == EquationType.INCOMPLETE_QUADRATIC) return computeIncompleteQuadratic(variableName);
		return this;
	}

	private Solution computeLinear(String variableName) {
		if (b == 0 && c == 0)
			res1 = variableName + " ∈ ℝ. Variable " + variableName + " is any real number";
		else if (b == 0) res1 = "The equation has no solution";
		else if (c == 0) {
			System.out.println("The equation is linear, there is one solution:");
			res1 = variableName + " = 0";
		}
		else {
			System.out.println("The equation is linear, there is one solution:");
			res1 = variableName + " = " + -(c / b);
		}
		return this;
	}

	private Solution computeCompleteQuadratic(String variableName) {
		double D = b * b - 4 * a * c;

		if (D == 0) {
			System.out.println("Discriminant is null, the one solution:");
			res1 = variableName + " = " + -(b / (2 * a));
		}
		else if (D > 0) {
			System.out.println("Discriminant is strictly positive, the two solutions are:");
			res1 = variableName + "₁ = " + (-b + MyMath.sqrt(D)) / (2 * a);
			res2 = variableName + "₂ = " + (-b - MyMath.sqrt(D)) / (2 * a);
		} else if (D < 0) {
			System.out.println("Discriminant is strictly positive, the two solutions are:");
			D = MyMath.sqrt(-D) / (2 * a);

			res1 = variableName + "₁ = " + -b / (2 * a) + (D < 0 ? " - " + -D : " + " + D) + 'i';
			res2 = variableName + "₂ = " + -b / (2 * a) + (D < 0 ? " + " + -D : " - " + D) + 'i';
		}
		return this;
	}

	private Solution computeIncompleteQuadratic(String variableName) {
		System.out.println("Incomplete quadratic equation, there are two solutions:");
		if (b == 0 && c == 0) res1 = variableName + " = 0";
		else if (b == 0) {
			float res = -c / a;

			if (res > 0) {
				res1 = variableName + "₁ = " + MyMath.sqrt(res);
				res2 = variableName + "₂ = -" + MyMath.sqrt(res);
			} else if (res < 0) {
				res1 = variableName + "₁ = " + MyMath.sqrt(-res) + 'i';
				res2 = variableName + "₂ = -" + MyMath.sqrt(-res) + 'i';
			}
		} else {
			res1 = variableName + "₁ = 0";
			res2 = variableName + "₂ = " + -b / a;
		}
		return this;
	}

	@Override
	public String toString() {
		if (res2 != null) {
			return res1 + '\n' + res2;
		}
		return res1;
	}
}
