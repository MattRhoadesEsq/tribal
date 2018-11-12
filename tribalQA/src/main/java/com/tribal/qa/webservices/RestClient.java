package com.tribal.qa.webservices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tribal.application.dto.BaseDTO;
import com.tribal.application.dto.ErrorResultDTO;
import com.tribal.application.dto.ResultDTO;
import com.tribal.qa.harness.HarnessException;
import com.tribal.qa.harness.TestProperties;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public ResultDTO doDELETE(BaseDTO dto) {
        return doPOST("", dto);
    }

    public ResultDTO doDELETE(String path, BaseDTO dto) {
        try {
            if (dto == null) {
                String response = doMethod(Method.DELETE, getServiceURL(path), null);
                return (ResultDTO) RestClient.convertJsonToDTO(response);
            }
            String request = RestClient.convertDtoToJson(dto);
            String response = doMethod(Method.DELETE, getServiceURL(path), request);
            return (ResultDTO) RestClient.convertJsonToDTO(response);
        } catch (IOException e) {
            throw new HarnessException("Unable to REST DELETE", e);
        }
    }

    public URL getServiceURL(String path) {
        String protocol = TestProperties.getInstance().getString("sut.server.protocol");
        String host = TestProperties.getInstance().getString("sut.server.hostname");;
        int port = TestProperties.getInstance().getInteger("sut.server.port");
        String file = "/user" + path;
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

            // The JSON object specifies its object type in the 'type' attribute.
            // Determine the type, so we can create that class
            String type = getDtoType(jsonString);
            Class<?> cls = Class.forName(type);

            // De-serialize to that type
            BaseDTO dto = (BaseDTO) getDeserializer().readValue(jsonString, cls);

            // Result objects contain String, DTO (Map), Array (List)
            // The default serializer will not convert the attribute, so do it now.
            if (dto instanceof ResultDTO) {

                if (((ResultDTO) dto).getResult() instanceof Map<?,?>) {

                    // Map is a JSONObject DTO
                    Map<?,?> m = (Map<?, ?>) ((ResultDTO) dto).getResult();
                    JSONObject json = new JSONObject(m);
                    BaseDTO base = convertJsonToDTO(json.toString());
                    ((ResultDTO) dto).setResult(base);

                } else if (((ResultDTO) dto).getResult() instanceof List<?>) {

                    // List is an array of JSONObject DTO
                    List<BaseDTO> dtoList = new ArrayList<>();
                    for (Object o : (List<?>) ((ResultDTO) dto).getResult()) {
                        if (!(o instanceof Map<?,?>)) {
                            throw new UnsupportedOperationException("Unable to parse result type: "+ o.getClass());
                        }
                        JSONObject json = new JSONObject((Map<?, ?>)o);
                        BaseDTO base = convertJsonToDTO(json.toString());
                        dtoList.add(base);

                    }
                    ((ResultDTO) dto).setResult(dtoList);
                } else {
                    throw new UnsupportedOperationException("Unable to parse result type: "+
                            ((ResultDTO) dto).getResult().getClass());
                }
            }

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
            String type = json.getString("type");
            return type;
        } catch (JSONException e) {
            throw new HarnessException("Unable to parse json: "+ jsonString, e);
        }
    }
    public static ObjectMapper deserializer;
    public static ObjectMapper getDeserializer() {
        if (deserializer == null) {
            deserializer = new ObjectMapper();
        }
        return deserializer;
    }


}
