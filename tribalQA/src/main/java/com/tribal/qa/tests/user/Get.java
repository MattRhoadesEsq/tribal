package com.tribal.qa.tests.user;

import com.tribal.application.dto.OkResultDTO;
import com.tribal.application.dto.ResultDTO;
import com.tribal.application.dto.UserDTO;
import com.tribal.qa.harness.TAssert;
import com.tribal.qa.tests.common.RestServiceTest;
import org.testng.annotations.Test;

public class Get extends RestServiceTest {

    @Test
    public void get01() {

        ResultDTO response = getRestClient().doGET();
        TAssert.assertEquals(response.getStatus(), "OK", "Verify positive response");

        TAssert.assertInstanceOf(response, OkResultDTO.class, "Verify the correct response type");
        OkResultDTO result = (OkResultDTO)response;

        TAssert.assertInstanceOf(result.getResult(), UserDTO.class, "Verify the correct result type");

    }
}
