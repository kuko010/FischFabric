package net.kuko.fisch;

public class MathUtils {
    /**
     * merge the given r, g, b and a values into a packed integer
     */
    static int ARGBtoInt(int a, int r, int g, int b) {
        int returnable = (a << 8) + r;
        returnable = (returnable << 8) + g;
        return (returnable << 8) + b;
    }
}
