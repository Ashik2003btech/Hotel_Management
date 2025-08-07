package com.hotelbooking.response;

public class ResponseObject {
    private String status;
    private String message;

    public ResponseObject(String status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Status: " + status + "\nMessage: " + message;
    }
}
