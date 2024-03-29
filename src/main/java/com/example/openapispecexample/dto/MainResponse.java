package com.example.openapispecexample.dto;

public interface MainResponse {

    public record Get(String message) {

    }

    public record Post(Long id) {

    }

    public record Put(String message) {

    }

    public record Patch(String message) {

    }
}
