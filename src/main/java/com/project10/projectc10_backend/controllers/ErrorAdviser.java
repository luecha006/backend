package com.project10.projectc10_backend.controllers;

import com.project10.projectc10_backend.exception.BaseException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorAdviser {
    //ถ้าเป็น error ที่มาจาก BaseException return output ตามที่ออกแบบ

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException e) {
        ErrorResponse response = new ErrorResponse();
        response.setError(e.getMessage()); //setError
        response.setStatus(HttpStatus.EXPECTATION_FAILED.value()); //setStatus error
        return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
    }

    @Data
    public static class ErrorResponse {

        //เมื่อมีการสร้าง object ของ class นี้จะมีการ set เวลาทันที เพื่อให้รู้ว่า error ตอนไหน
        private LocalDateTime timestimp = LocalDateTime.now();

        private int status;

        private String error;
    }

}
