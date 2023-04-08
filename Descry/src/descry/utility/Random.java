package descry.utility;

import java.util.List;

public class Random {

    private static final java.util.Random _rng = new java.util.Random();
    private static long _seed;

    static {
        _seed = _rng.nextLong();
        _rng.setSeed(_seed);
    }

    public static float range01() {
        return _rng.nextFloat();
    }

    public static int range(int range) {
        return _rng.nextInt(range);
    }

    public static int range(int min, int max) {
        return _rng.nextInt(max - min) + min;
    }

    public static float range(float range) {
        return _rng.nextFloat() * range;
    }

    public static float range(float min, float max) {
        return _rng.nextFloat() * (max - min) + min;
    }

    public static void setSeed(long seed) {
        _rng.setSeed(seed);
        _seed = seed;
    }

    public static long getSeed() {
        return _seed;
    }

    public static boolean bool() {
        return _rng.nextBoolean();
    }

    public static <T> T choice(List<T> pool) {
        return pool.get(range(pool.size()));
    }

    public static void shuffle(Object[] array) {
        for (int i = array.length - 1; i > 0; --i) {
            int j = Random.range(i);
            Object temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static void shuffle(int[] array) {
        for (int i = array.length - 1; i > 0; --i) {
            int j = Random.range(i);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static void shuffle(float[] array) {
        for (int i = array.length - 1; i > 0; --i) {
            int j = Random.range(i);
            float temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}
