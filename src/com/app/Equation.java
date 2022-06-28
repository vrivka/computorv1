package com.app;

public class Equation {
	private final Polynomial a = new Polynomial(0, 2);
	private final Polynomial b = new Polynomial(0, 1);
	private final Polynomial c = new Polynomial(0, 0);

	public void add(Polynomial p) {
		if (a.isSameDegree(p)) a.add(p);
		else if (b.isSameDegree(p)) b.add(p);
		else if (c.isSameDegree(p)) c.add(p);
	}

	public String getSolution() {
		EquationType type;

		float A = a.getFactor();
		float B = b.getFactor();
		float C = c.getFactor();

		if (A == 0) type = EquationType.LINEAR;
		else if (B == 0 || c.getFactor() == 0) type = EquationType.INCOMPLETE_QUADRATIC;
		else type = EquationType.COMPLETE_QUADRATIC;

		Solution solution = new Solution(A, B, C);

		return solution.compute(type).toString();
	}

	@Override
	public String toString() {
		float A = a.getFactor();
		float B = b.getFactor();
		float C = c.getFactor();

		StringBuilder res = new StringBuilder();


		if (A < 0) {
			res.append("-");
			A = -A;
		}
		if (A != 0) {
			res.append(A == 1 ? "" : A).append(Main.variable_name).append("²");
			if (B < 0) {
				res.append(" - ");
				B = -B;
			} else if (B > 0) res.append(" + ");
			else {
				if (C < 0) {
					res.append(" - ");
					C = -C;
				} else if (C > 0) res.append(" + ");
			}
		}
		if (B != 0) {
			res.append(B == 1 ? "" : B).append(Main.variable_name);
			if (C < 0) {
				res.append(" - ");
				C = -C;
			} else if (C > 0) res.append(" + ");
		}
		if (C != 0) res.append(C);
		res.append(" = 0");
		return res.toString();
	}
}
