package com.tribal.qa.harness;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;


/**
 * Hello world!
 *
 */
public class Main
{
    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main( String[] args ) throws IOException {
        logger.info("Hello, World!");

        TestHarness harness = new TestHarness();
        harness.execute();
    }

}
