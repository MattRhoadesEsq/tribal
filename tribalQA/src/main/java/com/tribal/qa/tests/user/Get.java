package com.tribal.qa.tests.user;

import com.tribal.application.dto.*;
import com.tribal.qa.harness.TAssert;
import com.tribal.qa.harness.UserBuilder;
import com.tribal.qa.tests.common.RestServiceTest;
import org.testng.annotations.Test;

import java.util.List;

public class Get extends RestServiceTest {

    @Test(description = "Verify GET request for all users")
    public void get01() {

        // Create a user
        UserDTO user = new UserBuilder().create();

        // Do a basic GET request (no parameters)
        ResultDTO response = getRestClient().doGET();
        TAssert.assertEquals(response.getStatus(), "OK", "Verify positive response");

        TAssert.assertInstanceOf(response, ListResultDTO.class, "Verify the correct response type");
        ListResultDTO result = (ListResultDTO)response;

        // Verify the user was found in the list
        boolean found = false;
        for (BaseDTO dto : (List<BaseDTO>) result.getResult()) {
            TAssert.assertInstanceOf(dto, UserDTO.class, "Verify the correct list object type");
            UserDTO u = (UserDTO)dto;
            if (u.getId().equals(user.getId())) {
                found = true;
                break;
            }
        }
        TAssert.assertTrue(found, "Verify the user was found in the list");

    }

    @Test(description = "Verify GET request for a specific user, by email address")
    public void get02() {

        // Create a user
        UserDTO user = new UserBuilder().create();

        ResultDTO response = getRestClient().doGET("?email="+ user.getEmail());
        TAssert.assertEquals(response.getStatus(), "OK", "Verify positive response");

        TAssert.assertInstanceOf(response, OkResultDTO.class, "Verify the correct response type");
        OkResultDTO result = (OkResultDTO)response;

        TAssert.assertInstanceOf(result.getResult(), UserDTO.class, "Verify the correct result type");
        UserDTO actual = (UserDTO) result.getResult();

        TAssert.assertEquals(user.getEmail(), actual.getEmail(), "Verify the email address matches");
        TAssert.assertEquals(user.getId(), actual.getId(), "Verify the ID matches");
    }

}
