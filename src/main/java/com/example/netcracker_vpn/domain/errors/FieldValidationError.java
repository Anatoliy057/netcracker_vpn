package com.example.netcracker_vpn.domain.errors;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FieldValidationError {

    private String object;
    private String field;
    private Object rejectedValue;
    private String message;
}
