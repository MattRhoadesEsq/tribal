package com.tribal.qa.tests.common;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.SkipException;

public class TSkipException extends SkipException {
    public static Logger logger = LogManager.getLogger(TSkipException.class);

    public TSkipException(String message) {
        super(message);
        logger.info(message);
    }
}
