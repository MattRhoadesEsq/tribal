package com.tribal.qa.tests.harness;

import com.tribal.qa.harness.TAssert;
import com.tribal.qa.tests.common.BaseTest;
import org.testng.annotations.Test;

public class ParallelTests extends BaseTest {

    @Test(description = "Verify parallel tests")
    public void test01() {
        TAssert.assertNotNull(Thread.currentThread().getName(), "Verify the parallel test thread name");
    }


}
