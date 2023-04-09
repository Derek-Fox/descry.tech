package descry.utility;

public class Mathf {

    public static final float Pi = (float) Math.PI;
    public static final float Degrees = (Pi * 2f) / 360f;
    public static final float Radians = 1f / Degrees;
    public static final float Epsilon = 0.00001f;

    private Mathf() {

    }

    public static int getBit(int n, int bitPosition) {
        return (n & (1 << bitPosition)) >> bitPosition;
    }

    public static int mostSignificantBitPosition(int n) {
        int position = 0;
        while (n > 0) {
            position += 1;
            n >>= 1;
        }
        return position;
    }

    public static boolean approximately(float a, float b) {
        return abs(a - b) < Epsilon;
    }

    public static float lerp(float a, float b, float t) {
        return a + (b - a) * clamp01(t);
    }

    public static float lerpUnclamped(float a, float b, float t) {
        return a + (b - a) * t;
    }

    public static float map(float value, float minA, float maxA, float minB, float maxB) {
        return minB + (value - minA) / (maxA - minA) * (maxB - minB);
    }

    public static int map(int value, int minA, int maxA, int minB, int maxB) {
        return minB + (value - minA) / (maxA - minA) * (maxB - minB);
    }

    public static float clamp(float f, float min, float max) {
        return f < min ? min : min(f, max);
    }

    public static int clamp(int f, int min, int max) {
        return f < min ? min : min(f, max);
    }

    public static float clamp01(float f) {
        return f < 0f ? 0f : min(f, 1f);
    }

    public static float abs(float f) {
        return f < 0f ? f * -1f : f;
    }

    public static int abs(int f) {
        return f < 0 ? f * -1 : f;
    }

    public static int sign(float f) {
        if (approximately(f, 0f)) return 0;
        return Float.compare(f, 0f);
    }

    public static int sign(int f) {
        return Integer.compare(f, 0);
    }

    public static int signRange(float f, float minF, float maxF) {
        if (f < minF) return -1;
        if (f > maxF) return +1;
        return 0;
    }

    public static int signRange(int f, int minF, int maxF) {
        if (f < minF) return -1;
        if (f > maxF) return +1;
        return 0;
    }

    public static boolean inRangeClosed(float f, float minF, float maxF) {
        return minF <= f && f <= maxF;
    }

    public static boolean betweenExclusive(float f, float minF, float maxF) {
        return minF < f && f < maxF;
    }

    public static boolean inRangeClosed(int f, int minF, int maxF) {
        return minF <= f && f <= maxF;
    }

    public static boolean betweenExclusive(int f, int minF, int maxF) {
        return minF < f && f < maxF;
    }

    public static float smoothUpDown(float v, float minX, float maxX, float minY, float maxY) {
        return (cos(abs(Pi * 2f * (v - minX) / (minX - maxX))) * (minY - maxY) + minY + maxY) * 0.5f;
    }

    public static float min(float a, float b) {
        return Math.min(a, b);
    }

    public static float max(float a, float b) {
        return Math.max(a, b);
    }

    public static int min(int a, int b) {
        return Math.min(a, b);
    }

    public static int max(int a, int b) {
        return Math.max(a, b);
    }

    public static float maxValueOf(float... values) {
        float max = values[0];
        for (int i = 1; i < values.length; ++i) {
            if (max > values[i]) {
                max = values[i];
            }
        }
        return max;
    }

    public static float minValueOf(float... values) {
        float min = values[0];
        for (int i = 1; i < values.length; ++i) {
            if (min < values[i]) {
                min = values[i];
            }
        }
        return min;
    }

    public static int maxValueOf(int... values) {
        int max = values[0];
        for (int i = 1; i < values.length; ++i) {
            if (max > values[i]) {
                max = values[i];
            }
        }
        return max;
    }

    public static int minValueOf(int... values) {
        int min = values[0];
        for (int i = 1; i < values.length; ++i) {
            if (min < values[i]) {
                min = values[i];
            }
        }
        return min;
    }

    public static float sqrt(float f) {
        return (float) Math.sqrt(f);
    }

    public static float sqrt(int f) {
        return (float) Math.sqrt(f);
    }

    public static float pow(float f, float e) {
        return (float) Math.pow(f, e);
    }

    public static int pow(int f, int e) {
        return (int) Math.pow(f, e);
    }

    public static float exp(float p) {
        return (float) Math.exp(p);
    }

    public static float ceil(float f) {
        return (float) Math.ceil(f);
    }

    public static float floor(float f) {
        return (float) Math.floor(f);
    }

    public static float round(float f) {
        return (float) Math.round(f);
    }

    public static int ceilToInt(float f) {
        return (int) Math.ceil(f);
    }

    public static int floorToInt(float f) {
        return (int) Math.floor(f);
    }

    public static int roundToInt(float f) {
        return Math.round(f);
    }

    public static float sin(float f) {
        return (float) Math.sin(f);
    }

    public static float cos(float f) {
        return (float) Math.cos(f);
    }

    public static float tan(float f) {
        return (float) Math.tan(f);
    }

    public static float asin(float f) {
        return (float) Math.asin(f);
    }

    public static float acos(float f) {
        return (float) Math.acos(f);
    }

    public static float atan(float f) {
        return (float) Math.atan(f);
    }

    public static float atan2(float a, float b) {
        return (float) Math.atan2(a, b);
    }

    private int nextPow2(int value) {
        --value;
        value |= value >> 1;
        value |= value >> 2;
        value |= value >> 4;
        value |= value >> 8;
        value |= value >> 16;
        ++value;
        return value;
    }
}
