package com.tribal.qa.tests.user;

import com.tribal.application.dto.OkResultDTO;
import com.tribal.application.dto.ResultDTO;
import com.tribal.application.dto.UserDTO;
import com.tribal.qa.harness.Randomness;
import com.tribal.qa.harness.TAssert;
import com.tribal.qa.tests.common.RestServiceTest;
import org.testng.annotations.Test;

public class Post extends RestServiceTest {

    @Test
    public void post01() {

        // Define a new user
        UserDTO dto = new UserDTO();
        dto.setEmail(Randomness.randomEmail());

        // Post the new user to the service
        ResultDTO response = getRestClient().doPOST(dto);
        TAssert.assertEquals(response.getStatus(), "OK", "Verify positive response");

    }
}
