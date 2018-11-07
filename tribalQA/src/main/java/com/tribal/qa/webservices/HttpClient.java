package com.tribal.qa.webservices;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class HttpClient {
    protected static final Logger logger = LogManager.getLogger(HttpClient.class);

    public enum Method {
        GET, POST, HEAD, PUT, DELETE, OPTIONS, CONNECT
    }

    public HttpClient() {

    }

    public String doMethod(Method method, URL url, String data) throws IOException {
        logger.info(method +": "+ url.toString());

        String responseContentType = "text/plain";

        HttpURLConnection conn = null;
        try {

            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod(method.toString().toUpperCase());
            conn.setDoOutput(true);

            // TODO: cookies

            if (data != null) {
                conn.setDoInput(true);

                conn.setRequestProperty("Content-Type", "application/json; charset=utf8");
                conn.setRequestProperty("Content-Length", Integer.toString(data.getBytes().length));
                conn.setRequestProperty("Content-Language", "en-US");

                logger.info("Request:\n" + data);

                try (DataOutputStream out = new DataOutputStream(conn.getOutputStream())) {
                    out.writeBytes(data);
                    out.flush();
                }

            }
            for(Map.Entry<String, List<String>> entry : conn.getHeaderFields().entrySet()){
                logger.info("Response Header: "+ entry.getKey() + ": "+ entry.getValue());

                if (entry.getKey() != null) {

                    if (entry.getKey().equals("Content-Type")) {
                        // Remember the content type, for logging.
                        responseContentType = entry.getValue().get(0);
                    }

                    // TODO: "Set-Cookie"

                }

            }

            StringBuilder sb = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            }

            String response = sb.toString();

            if (responseContentType.contains("application/json")) {
                response = prettyPrintJson(response);
            }

            logger.info("Response:\n"+ response);
            return response;

        } finally {
            conn.disconnect();;
        }

    }

    public static String prettyPrintJson(String jsonString) {
        if (jsonString.startsWith("[")) {
            try {
                JSONArray array = new JSONArray(jsonString);
                for (int i = 0; i < array.length(); i++) {
                    return prettyPrintJson(array.get(i).toString());
                }
            } catch (JSONException e) {
                // On failure, log the error, but fallback to the original string
                logger.warn(e);
            }
            // Return original string, if failed
            return jsonString;
        }
        try {
            JSONObject json = new JSONObject(jsonString);
            return json.toString(4);
        } catch (JSONException e) {
            // On failure, log the error, but fallback to the original string
            logger.warn(e);
        }
        // Return original string, if failed
        return jsonString;
    }

}
