package edu.school21.computorv1.logic;

public enum EquationType {
    LINEAR, COMPLETE_QUADRATIC, INCOMPLETE_QUADRATIC;

    public static EquationType getType(float a, float b, float c) {
        if (a == 0) {
            return LINEAR;
        } else if (b == 0 || c == 0) {
            return INCOMPLETE_QUADRATIC;
        } else {
            return COMPLETE_QUADRATIC;
        }
    }
}
