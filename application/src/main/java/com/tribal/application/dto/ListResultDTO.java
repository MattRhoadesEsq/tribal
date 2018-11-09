package com.tribal.application.dto;

import java.util.List;

public class ListResultDTO extends ResultDTO {

    private int count;

    public ListResultDTO() {
        super(ListResultDTO.class, "OK");
    }

    public int getCount() {
        if (getResult() == null) {
            return 0;
        }
        if (getResult() instanceof List<?>) {
            return ((List) getResult()).size();
        }
        throw new UnsupportedOperationException("Unhandled result type: "+ getResult().getClass());
    }

}
