package com.tribal.application.dto;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;


@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
public class BaseDTO extends JSONObject {
    protected static Logger LOG = LogManager.getLogger(BaseDTO.class);
}
