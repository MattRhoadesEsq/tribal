package com.tribal.qa.harness;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Hello world!
 *
 */
public class Main
{
    private static Logger logger = LogManager.getLogger(Main.class);

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        logger.info("Hello, world!");
    }
}
