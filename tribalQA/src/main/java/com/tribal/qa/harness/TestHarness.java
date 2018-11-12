package com.tribal.qa.harness;

import com.google.common.reflect.ClassPath;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestHarness {
    private static Logger logger = LogManager.getLogger(TestHarness.class);

    /**
     * A TestNG harness for executing tests.
     */
    public TestHarness() {
        logger.debug("new: "+ this.getClass().getCanonicalName());
    }


    /**
     * Execute all tests.
     *
     * @throws IOException
     */
    public void execute() throws IOException {


        // Create the suite
        XmlSuite xmlSuite = new XmlSuite();
        // TODO: Set threads, etc.

        // Create the test with test classes/methods
        XmlTest myTest = new XmlTest(xmlSuite);
        myTest.setXmlClasses(getTestClasses());

        // Link the suite and test
        xmlSuite.setTests(Arrays.asList(myTest));

        // Create a TestNG object to run tests
        TestNG testng = new TestNG();
        testng.setOutputDirectory("testng");
        testng.setXmlSuites(Arrays.asList(xmlSuite));

        // Execute tests!
        testng.run();

    }

    /**
     * Get test classes that include @Test methods.  Apply filters based on the
     * parameters/settings specified.
     *
     * @return
     * @throws IOException
     */
    public List<XmlClass> getTestClasses() throws IOException {

        List<XmlClass> classes = new ArrayList<>();

        ClassPath cp = ClassPath.from(Thread.currentThread().getContextClassLoader());

        // Load all classes in the jar
        // Filter based on a path
        for (ClassPath.ClassInfo info : cp.getTopLevelClassesRecursive("com.tribal.qa.tests")) {

            // Find all methods in the class that are decorated with @Test annotation
            XmlClass xmlClass = null;
            List<XmlInclude> methods = getTestMethods(info);
            if (methods.size() > 0) {
                if (xmlClass == null) {
                    xmlClass = new XmlClass(info.getName());
                }
                xmlClass.setIncludedMethods(methods);
            }

            // If any test methods were found in the class, add it to the list of test classes
            if (xmlClass != null) {
                classes.add(xmlClass);
            }

        }

        return classes;
    }

    /**
     * Get a list of methods with the @Test annotation.  Apply filters based on the
     * parameters/settings specified.
     *
     * @param info
     * @return
     */
    public List<XmlInclude> getTestMethods(ClassPath.ClassInfo info) {

        List<XmlInclude> methods = new ArrayList<>();

        for (Method m : info.load().getDeclaredMethods()) {
            if (m.isAnnotationPresent(Test.class)) {
                methods.add(new XmlInclude(m.getName()));
            }
        }

        return methods;

    }




}
