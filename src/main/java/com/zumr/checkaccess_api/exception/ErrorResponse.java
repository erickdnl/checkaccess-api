package com.zumr.checkaccess_api.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
@Builder
public class ErrorResponse {

    private Instant timestamp;
    private int status;
    private String error;
    private String path;
    private List<FieldErrorResponse> errors;
}
