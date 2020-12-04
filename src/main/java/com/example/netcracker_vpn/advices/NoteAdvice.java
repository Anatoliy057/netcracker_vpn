package com.example.netcracker_vpn.advices;

import com.example.netcracker_vpn.controllers.NoteController;
import com.example.netcracker_vpn.domain.errors.ApiError;

import com.example.netcracker_vpn.exceptions.ServerAlreadyExistsException;
import com.example.netcracker_vpn.exceptions.ServiceNotFoundByIdException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice(assignableTypes = {NoteController.class})
public class NoteAdvice extends ResponseEntityExceptionHandler {

    public NoteAdvice() {
        super();
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = new ApiError();
        apiError.addValidationErrors(ex.getFieldErrors());
        return new ResponseEntity<>(apiError, BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = new ApiError();
        apiError.addValidationErrors(ex.getFieldErrors());
        return new ResponseEntity<>(apiError, BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handlePSQLException(DataIntegrityViolationException ex) {
        return new ResponseEntity<>(new ApiError(ex.getCause().getLocalizedMessage()), BAD_REQUEST);
    }

    @ExceptionHandler(ServerAlreadyExistsException.class)
    protected ResponseEntity<Object> handleServerAlreadyExistsException(ServerAlreadyExistsException ex) {
        return new ResponseEntity<>(new ApiError(ex.getMessage()), BAD_REQUEST);
    }

    @ExceptionHandler(ServiceNotFoundByIdException.class)
    protected ResponseEntity<Object> handleServerAlreadyExistsException(ServiceNotFoundByIdException ex) {
        return new ResponseEntity<>(new ApiError(ex.getMessage()), NOT_FOUND);
    }
}
