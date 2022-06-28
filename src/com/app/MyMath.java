package com.app;

public class MyMath {
	static double sqrt(double n) {
		if (n < 0) return Double.NaN;
		else if (n == 0d) return 0d;
		else if (n == 1d) return 1d;

		double x = 1;

		while (true) {
			double xn = (x + n / x) / 2;
			if (abs(x - xn) < 1e-15) break;
			x = xn;
		}
		return x;
	}

	static double abs(double n) {
		return n < 0 ? -n : n;
	}
}
