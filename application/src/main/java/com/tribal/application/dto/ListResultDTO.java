package com.tribal.application.dto;

import java.util.List;

public class ListResultDTO extends ResultDTO {

    private int count;

    public ListResultDTO() {
        super(ListResultDTO.class, "OK");
    }

    public ListResultDTO(List<BaseDTO> dtoList) {
        super(ListResultDTO.class, "OK");

        setResult(dtoList);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
