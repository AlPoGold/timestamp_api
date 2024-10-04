package org.example.timestamp_api.exceptions;

public class WrongDataUTCException extends RuntimeException{
    public WrongDataUTCException(String message) {
        super(message);
    }
}
