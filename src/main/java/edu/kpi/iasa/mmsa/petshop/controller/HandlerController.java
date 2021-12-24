package edu.kpi.iasa.mmsa.petshop.controller;

import edu.kpi.iasa.mmsa.petshop.exception.*;
import edu.kpi.iasa.mmsa.petshop.exception.Error;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class HandlerController {
    @ExceptionHandler(value
            = { StatusNotFoundException.class })
    protected ResponseEntity<Error> handleConflict(
            StatusNotFoundException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Status not found").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value
            = { AccountNotFoundException.class })
    protected ResponseEntity<Error> handleConflict(
            AccountNotFoundException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Account not found").build();
        return ResponseEntity.badRequest().body(error);
    }
    @ExceptionHandler(value
            = { RoleNotFoundException.class })
    protected ResponseEntity<Error> handleConflict(
            RoleNotFoundException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Role not found").build();
        return ResponseEntity.badRequest().body(error);
    }
    @ExceptionHandler(value
            = { PurchaseNotFoundException.class })
    protected ResponseEntity<Error> handleConflict(
            PurchaseNotFoundException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Purchase not found").build();
        return ResponseEntity.badRequest().body(error);
    }
    @ExceptionHandler(value
            = { TypeNotFoundException.class })
    protected ResponseEntity<Error> handleConflict(
            TypeNotFoundException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Type not found").build();
        return ResponseEntity.badRequest().body(error);
    }
    @ExceptionHandler(value
            = { ShelterNotFoundException.class })
    protected ResponseEntity<Error> handleConflict(
            ShelterNotFoundException ex, WebRequest request) {
        Error error = Error.builder().code("BAD_REQUEST").description("Shelter not found").build();
        return ResponseEntity.badRequest().body(error);
    }
}
