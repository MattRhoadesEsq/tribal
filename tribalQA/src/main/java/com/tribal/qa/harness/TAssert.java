package com.tribal.qa.harness;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TAssert {
    private static Logger logger = LogManager.getLogger(TAssert.class);

    private static int checks = 0;
    private static int pass = 0;
    private static int fail = 0;

    public static void assertEquals(Object expected, Object actual, String message) {
        checks++;
        if (expected.equals(actual)) {
            logger.info("assertEquals("+ expected +", "+ actual +"): " + message);
            pass++;
        } else {
            fail++;
            throw new TAssertException("assertEquals("+ expected +", "+ actual +"): " + message);
        }
    }

    public static void assertInstanceOf(Object actual, Class<?> expected, String message) {
        checks++;
        if (expected.isInstance(actual)) {
            logger.info("assertInstanceOf("+ actual +" instanceof "+ expected +"): " + message);
            pass++;
        } else {
            fail++;
            throw new TAssertException("assertInstanceOf("+ actual +" instanceof "+ expected +"): " + message);
        }
    }


    public static class TAssertException extends AssertionError {
        public TAssertException(String message) {
            super(message);
            logger.error(message);
        }
    }
}
