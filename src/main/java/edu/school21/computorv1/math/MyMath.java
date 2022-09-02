package edu.school21.computorv1.math;

public class MyMath {
    public static double sqrt(double number) {
        double result;

        if (number < 0) {
            return Double.NaN;
        } else if (number == 0d || number == 1d) {
            return number;
        }
        result = 1d;

        while (true) {
            double tmp = (result + number / result) / 2;

            if (abs(result - tmp) < 1e-15) {
                break;
            }
            result = tmp;
        }
        return result;
    }

    public static double abs(double number) {
        return number < 0 ? -number : number;
    }
}
