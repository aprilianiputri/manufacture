package com.restapi.manufacture.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FailureResponse {
    private Long timeStamp;
    private String message;
    private String[] errors;
}
