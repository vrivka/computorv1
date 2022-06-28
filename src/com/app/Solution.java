package com.app;

public class Solution {
	private final Float a;
	private final Float b;
	private final Float c;

	private String res1 = "The equation has no solution";
	private String res2 = null;

	public Solution(float a, float b, float c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public Solution compute(EquationType type) {
		if (type == EquationType.LINEAR) return computeLinear();
		else if (type == EquationType.COMPLETE_QUADRATIC) return computeCompleteQuadratic();
		else if (type == EquationType.INCOMPLETE_QUADRATIC) return computeIncompleteQuadratic();
		return this;
	}

	private Solution computeLinear() {
		if (b == 0 && c == 0)
			res1 = Main.variable_name + " ∈ ℝ. Variable " + Main.variable_name + " is any real number";
		else if (b == 0) res1 = "The equation has no solution";
		else if (c == 0) res1 = Main.variable_name + " = 0";
		else res1 = Main.variable_name + " = " + -(c / b);
		return this;
	}

	private Solution computeCompleteQuadratic() {
		double D = b * b - 4 * a * c;

		if (D == 0) res1 = Main.variable_name + " = " + -(b / (2 * a));
		else if (D > 0) {
			res1 = Main.variable_name + "₁ = " + (-b + MyMath.sqrt(D)) / (2 * a);
			res2 = Main.variable_name + "₂ = " + (-b - MyMath.sqrt(D)) / (2 * a);
		} else if (D < 0) {
			D = MyMath.sqrt(-D) / (2 * a);

			res1 = Main.variable_name + "₁ = " + -b / (2 * a) + (D < 0 ? " - " + -D : " + " + D) + 'i';
			res2 = Main.variable_name + "₂ = " + -b / (2 * a) + (D < 0 ? " + " + -D : " - " + D) + 'i';
		}
		return this;
	}

	private Solution computeIncompleteQuadratic() {
		if (b == 0 && c == 0) res1 = Main.variable_name + " = 0";
		else if (b == 0) {
			float res = -c / a;

			if (res > 0) {
				res1 = Main.variable_name + "₁ = " + MyMath.sqrt(res);
				res2 = Main.variable_name + "₂ = -" + MyMath.sqrt(res);
			} else if (res < 0) {
				res1 = Main.variable_name + "₁ = " + MyMath.sqrt(-res) + 'i';
				res2 = Main.variable_name + "₂ = -" + MyMath.sqrt(-res) + 'i';
			}
		} else {
			res1 = Main.variable_name + "₁ = 0";
			res2 = Main.variable_name + "₂ = " + -b / a;
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
