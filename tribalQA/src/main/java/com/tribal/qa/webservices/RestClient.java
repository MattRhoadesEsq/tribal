package com.tribal.qa.webservices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tribal.application.dto.BaseDTO;
import com.tribal.application.dto.ErrorResultDTO;
import com.tribal.application.dto.ResultDTO;
import com.tribal.qa.harness.HarnessException;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class RestClient extends HttpClient {



    public ResultDTO doGET() {
        return doGET("");
    }

    public ResultDTO doGET(String path) {
        try {
            String response = doMethod(Method.GET, getServiceURL(path), null);
            return (ResultDTO) RestClient.convertJsonToDTO(response);
        } catch (IOException e) {
            throw new HarnessException("Unable to REST GET", e);
        }
    }

    public ResultDTO doPOST(BaseDTO dto) {
        return doPOST("", dto);
    }

    public ResultDTO doPOST(String path, BaseDTO dto) {
        try {
            String request = RestClient.convertDtoToJson(dto);
            String response = doMethod(Method.POST, getServiceURL(path), request);
            return (ResultDTO) RestClient.convertJsonToDTO(response);
        } catch (IOException e) {
            throw new HarnessException("Unable to REST POST", e);
        }
    }

    public URL getServiceURL(String path) {
        String protocol = "http";
        String host = "localhost";
        int port = 8080;
        String file = "/user";
        try {
            return new URL(protocol, host, port, file);
        } catch (MalformedURLException e) {
            throw new HarnessException("Unable to create http://localhost:8080/user", e);
        }
    }

    public static String convertDtoToJson(BaseDTO dto) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            throw new HarnessException("Unable to convert " + dto, e);
        }
    }

    public static BaseDTO convertJsonToDTO(String jsonString) {

        try {
            // The JSON object specifies its type, determine it  now
            String type = getDtoType(jsonString);
            // Create the DTO object
            Class<?> cls = Class.forName(type);
            BaseDTO dto = (BaseDTO) getDeserializer().readValue(jsonString, cls);

            // Check if the response was an error result
            if (dto instanceof ErrorResultDTO) {
                throw new HarnessException("API ErrorResult: " + prettyPrintJson(jsonString));
            }

            return dto;

        } catch (IOException e) {
            throw new HarnessException("Unable to convert: "+ jsonString, e);
        } catch (ClassNotFoundException e) {
            throw new HarnessException("Unable to convert: "+ jsonString, e);
        }
    }

    public static String getDtoType(String jsonString) {
        try {
            JSONObject json = new JSONObject(jsonString);
            String type = json.getString("@class");
            return type;
        } catch (JSONException e) {
            throw new HarnessException("Unable to parse json: "+ jsonString, e);
        }
    }
    public static ObjectMapper deserializer;
    public static ObjectMapper getDeserializer() {
        if (deserializer == null) {
            deserializer = new ObjectMapper();
            deserializer.enableDefaultTyping(ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE);
        }
        return deserializer;
    }


}
