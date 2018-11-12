package com.tribal.qa.tests.common.listeners;

import com.tribal.qa.harness.HarnessException;
import com.tribal.qa.harness.TestProperties;
import com.tribal.qa.tests.common.TSkipException;
import com.tribal.qa.webservices.HttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class BugListener implements IInvokedMethodListener {


    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        // Nothing to do
    }

    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {

        // Determine if the test method was decorated with the Bug ID
        Bug bug = iInvokedMethod.getTestMethod().getConstructorOrMethod().getMethod().getAnnotation(Bug.class);
        if (bug == null) {
            // No bug annotation, nothing to do.  Run the test.
            return;
        }

        String id = bug.id();
        if (id == null || id.trim().isEmpty()) {
            // No ID, nothing to do.  Run the test.
            return;
        }

        if (getStatus(id).equalsIgnoreCase("OPEN")) {
            // Skip the test if the bug is OPEN
            throw new TSkipException("Skipping test because status = OPEN for bug ID " + id);
        }
    }

    /**
     * Based on the bug ID, get the bug status from the bug database
     * @param id
     * @return
     */
    private String getStatus(String id) {
        try {
            String protocol = TestProperties.getInstance().getString("bug.server.protocol");
            String host = TestProperties.getInstance().getString("bug.server.hostname");;
            int port = TestProperties.getInstance().getInteger("bug.server.port");
            String file = TestProperties.getInstance().getString("bug.server.file");

            URL url = new URL(protocol, host, port, file);
            String jsonString = "TBD";
            try {
                HttpClient client = new HttpClient();
                jsonString = client.doMethod(HttpClient.Method.GET, url, "?id="+ id);
                JSONObject json = new JSONObject(jsonString);
                return json.getString("status");
            } catch (IOException e) {
                throw new HarnessException("Unable to get bug status: " + url, e);
            } catch (JSONException e) {
                throw new HarnessException("Unable to get bug status: " + jsonString, e);
            }
        } catch (MalformedURLException e) {
            throw new HarnessException("Unable to get bug DB URL", e);
        }

    }
}
