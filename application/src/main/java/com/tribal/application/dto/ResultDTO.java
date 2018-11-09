package com.tribal.application.dto;


public class ResultDTO extends TypedDTO {
    private String status;
    private Object result;

    protected ResultDTO(Class<?> type, String status) {
        super(type);

        this.status = status;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

}
