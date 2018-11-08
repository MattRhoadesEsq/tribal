package com.tribal.qa.tests.user;

import com.tribal.application.dto.ResultDTO;
import com.tribal.application.dto.UserDTO;
import com.tribal.qa.harness.Randomness;
import com.tribal.qa.harness.TAssert;
import com.tribal.qa.harness.UserBuilder;
import com.tribal.qa.tests.common.RestServiceTest;
import org.testng.annotations.Test;

public class Delete extends RestServiceTest {

    @Test
    public void delete01() {

        // Create a new user on the server
        UserDTO dto = new UserBuilder().create();

        // DELETE the user on the service
        ResultDTO response = getRestClient().doDELETE("/" + dto.getId(), null);
        TAssert.assertEquals(response.getStatus(), "OK", "Verify positive response");

    }

}
