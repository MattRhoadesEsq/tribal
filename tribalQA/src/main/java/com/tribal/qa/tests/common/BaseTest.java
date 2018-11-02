package com.tribal.qa.tests.common;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.*;

public class BaseTest {
    public static Logger logger = LogManager.getLogger(BaseTest.class);


    public BaseTest() {
        logger.debug("new "+ this.getClass().getCanonicalName());
    }

    @BeforeSuite
    public void beforeSuite() {
        logger.debug("before suite");
    }

    @AfterSuite
    public void afterSuite() {
        logger.debug("after suite");
    }

    @BeforeClass
    public void beforeClass() {
        logger.debug("before class");
    }

    @AfterClass
    public void afterClass() {
        logger.debug("after class");
    }

    @BeforeMethod
    public void beforeMethod() {
        logger.debug("before method");
    }

    @AfterMethod
    public void afterMethod() {
        logger.debug("after method");
    }

    @BeforeTest
    public void beforeTest() {
        logger.debug("before test");
    }

    @AfterTest
    public void afterTest() {
        logger.debug("after test");
    }

}
