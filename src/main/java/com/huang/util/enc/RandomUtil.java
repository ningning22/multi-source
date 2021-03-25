package com.huang.util.enc;

import java.util.Arrays;
import java.util.Random;

public class RandomUtil {
    static Random random = new Random();

    public static byte[] getRandomKey(int length) {
        byte[] result = new byte[length];
        random.nextBytes(result);
        if (result[0] == 0) result[0] = 1;
        if (result[length - 1] == 0) result[length - 1] = 1;
        return result;
    }

    public static byte[] getFillKey(int length, byte fill) {
        byte[] result = new byte[length];
        Arrays.fill(result, fill);
        return result;
    }
}
