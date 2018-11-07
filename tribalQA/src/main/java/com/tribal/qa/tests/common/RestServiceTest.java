package com.tribal.qa.tests.common;

import com.tribal.qa.webservices.RestClient;

public class RestServiceTest extends BaseTest {

    private RestClient httpClient = new RestClient();

    protected RestServiceTest() {
    }

    public RestClient getRestClient() {
        return httpClient;
    }
}
