package com.example.demo;

public class WeatherAlert {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "WeatherAlert{" +
                "message='" + message + '\'' +
                '}';
    }
}
