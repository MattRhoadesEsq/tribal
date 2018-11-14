package com.tribal.qa.harness;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TAssert {
    private static Logger logger = LogManager.getLogger(TAssert.class);

    private static int checks = 0;
    private static int pass = 0;
    private static int fail = 0;

    public static void assertNull(Object actual, String message) {
        checks++;
        if (actual == null) {
            logger.info("assertNull("+ actual +"): " + message);
            pass++;
        } else {
            fail++;
            throw new TAssertException("assertNull("+ actual +"): " + message);
        }
    }

    public static void assertNotNull(Object actual, String message) {
        checks++;
        if (actual != null) {
            logger.info("assertNotNull("+ actual +"): " + message);
            pass++;
        } else {
            fail++;
            throw new TAssertException("assertNotNull("+ actual +"): " + message);
        }
    }

    public static void assertTrue(boolean actual, String message) {
        checks++;
        if (actual) {
            logger.info("assertTrue("+ actual +"): " + message);
            pass++;
        } else {
            fail++;
            throw new TAssertException("assertTrue("+ actual +"): " + message);
        }
    }

    public static void assertFalse(boolean actual, String message) {
        checks++;
        if (!actual) {
            logger.info("assertFalse("+ actual +"): " + message);
            pass++;
        } else {
            fail++;
            throw new TAssertException("assertFalse("+ actual +"): " + message);
        }
    }

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

    public static void assertContains(String str, CharSequence substr, String message) {
        checks++;
        if (str.contains(substr)) {
            logger.info("assertContains("+ str +" contains "+ substr +")): " + message);
            pass++;
        } else {
            fail++;
            throw new TAssertException("assertContains("+ str +" contains "+ substr +")): " + message);
        }
    }

    public static void assertDoesNotContain(String str, CharSequence substr, String message) {
        checks++;
        if (!str.contains(substr)) {
            logger.info("assertDoesNotContain("+ str +" does not contain "+ substr +"): " + message);
            pass++;
        } else {
            fail++;
            throw new TAssertException("assertDoesNotContain("+ str +" does not contain "+ substr +"): " + message);
        }
    }

    public static void assertInstanceOf(Object actual, Class<?> expected, String message) {
        checks++;
        if (expected.isInstance(actual)) {
            logger.info("assertInstanceOf("+ actual.getClass() +" instanceof "+ expected +"): " + message);
            pass++;
        } else {
            fail++;
            throw new TAssertException("assertInstanceOf("+ actual.getClass() +" instanceof "+ expected +"): " + message);
        }
    }


    public static class TAssertException extends AssertionError {
        public TAssertException(String message) {
            super(message);
            logger.error(message);
        }
    }
}
