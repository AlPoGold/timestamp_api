package org.example.timestamp_api.adviсes;

import org.example.timestamp_api.dto.ErrorObject;
import org.example.timestamp_api.exceptions.WrongDataUTCException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class TimeStampAdviсer {

    @ExceptionHandler(WrongDataUTCException.class)
    public ResponseEntity<ErrorObject> handleWrongDataException(WrongDataUTCException ex, WebRequest request) {
        // Можно возвращать ResponseEntity с описанием ошибки
        String message = "Invalid utcData: " + ex.getMessage();
        return new ResponseEntity<>(new ErrorObject(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorObject> handleNumberFormatException(NumberFormatException ex, WebRequest request){
        return new ResponseEntity<>(new ErrorObject(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
        String message = "An unexpected error occurred: " + ex.getMessage();
        return new ResponseEntity<>(new ErrorObject(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
