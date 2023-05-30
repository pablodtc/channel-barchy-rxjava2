package com.barchy.rxjava.errors.controller;

import com.barchy.rxjava.errors.model.ApiResponse;
import com.barchy.rxjava.errors.model.GeneralError;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//@ControllerAdvice
@Slf4j
public class WebControllerAdvice {

  //@ExceptionHandler(GeneralError.class)
  public ResponseEntity<Single<ApiResponse<?>>> handleUnhandledExceptions(GeneralError error) {
    log.error("Ocurrio un error no controlado", error);
    ApiResponse<?> apiResponse = ApiResponse.builder()
        .code(error.getCode())
        .message(error.getMessage())
        .build();
    return new ResponseEntity<>(Single.just(apiResponse), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
