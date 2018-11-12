package com.tribal.qa.harness;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;

public class TestProperties {
    private static Logger logger = LogManager.getLogger(TestHarness.class);

    private static final String TEST_PROPERTIES = "test.properties";
    private static final String LOCAL_PROPERTIES = "/temp/test.properties";

    private static volatile TestProperties instance = null;

    private Properties myProperties = null;

    private TestProperties() {
        logger.debug("new "+ this.getClass().getCanonicalName());

        myProperties = new Properties();

        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(TEST_PROPERTIES)) {
            myProperties.load(is);
        } catch (IOException e) {
            logger.warn("Unable to load resource: " + TEST_PROPERTIES, e);
        }

        // Also, /tmp/test.properties overrides any values in the default
        File f = new File(LOCAL_PROPERTIES);
        if (f.exists()) {
            try (InputStream is = new FileInputStream(f)) {

                Properties local = new Properties();
                local.load(is);
                myProperties.putAll(local);

            } catch (FileNotFoundException e) {
                logger.warn("Unable to load resource: " + TEST_PROPERTIES, e);
            } catch (IOException e) {
                logger.warn("Unable to load resource: " + TEST_PROPERTIES, e);
            }
        }
    }


    public static TestProperties getInstance() {
        if (instance == null) {
            synchronized(TestProperties.class) {
                if (instance == null) {
                    instance = new TestProperties();
                }
            }
        }
        return instance;
    }

    public String getString(String key) {
        return getProperty(key);
    }

    public Integer getInteger(String key) {
        return Integer.parseInt(getProperty(key));
    }

    public String getProperty(String key) {
        String value = myProperties.getProperty(key, null);
        if (value == null) {
            throw new HarnessException("Properties: key does not exist: "+ key);
        }
        return value;
    }

    public String getProperty(String key, String defaultValue) {
        return myProperties.getProperty(key, defaultValue);
    }

}
