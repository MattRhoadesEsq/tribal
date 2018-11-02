package com.tribal.application;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.io.InputStream;

public class Logging {
    private static Logger logger = LogManager.getLogger(Logging.class);
    private static final String LOG4J_PROPERTIES = "log4j.properties";

    static {
        BasicConfigurator.configure();
    }

    private static boolean initialized = false;

    public static void initialize() {
        if (initialized) {
            return;
        }

        logger.debug("initializing ...");

        try(InputStream is =  Thread.currentThread().getContextClassLoader().getResourceAsStream(LOG4J_PROPERTIES)) {

            PropertyConfigurator.configure(is);
            logger.debug("initializing ... done");
            initialized = true;

        } catch (IOException e) {
            logger.error("Unable to load resource: "+ LOG4J_PROPERTIES, e);
            e.printStackTrace();
        }

    }

}
