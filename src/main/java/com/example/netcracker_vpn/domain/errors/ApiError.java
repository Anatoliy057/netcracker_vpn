package com.example.netcracker_vpn.domain.errors;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class ApiError {
    private String message;

    private List<FieldValidationError> fieldValidationErrors;


    public ApiError(String message) {
        this.message = message;
    }

    public void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(error ->
                addSubError(
                        FieldValidationError.builder()
                                .field(error.getField())
                                .message(error.getDefaultMessage())
                                .rejectedValue(error.getRejectedValue())
                                .object(error.getObjectName())
                                .build()
                )
        );
    }

    private void addSubError(FieldValidationError subError) {
        if (fieldValidationErrors == null) {
            fieldValidationErrors = new ArrayList<>();
        }
        fieldValidationErrors.add(subError);
    }
}
