package com.tribal.qa.tests.user;

import com.tribal.application.dto.ResultDTO;
import com.tribal.application.dto.UserDTO;
import com.tribal.qa.harness.TAssert;
import com.tribal.qa.tests.common.RestServiceTest;
import org.testng.annotations.Test;

public class Get extends RestServiceTest {

    @Test
    public void get01() {

        ResultDTO result = getRestClient().doGET();
        TAssert.assertEquals(result.getStatus(), "OK", "Verify positive result");

    }
}
