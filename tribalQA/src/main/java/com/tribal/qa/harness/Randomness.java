package com.tribal.qa.harness;

import java.util.concurrent.ThreadLocalRandom;

public class Randomness {

    public static final String ALPHA_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String randomEmail() {
        String local = randomString(10).toLowerCase();
        String domain = randomString(10).toLowerCase();
        return local + "@" + domain + ".com";
    }


    public static String randomString(int length) {
        return randomString(length, ALPHA_STRING);
    }

    public static String randomString(int length, String chars) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(randomInteger(0, chars.length() - 1)));
        }
        return sb.toString();

    }

    /**
     * Return a random positive integer
     * @return
     */
    public static int randomInteger() {
        return randomInteger(0, 1000000);
    }

    /**
     * Return a random integeger between (inclusive) the specified range
     * @param min
     * @param max
     * @return
     */
    public static int randomInteger(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
