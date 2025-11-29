package com.example.gyakorlatbead;

public class SutiNotFoundException extends RuntimeException {
    SutiNotFoundException (int id) {
        super("A süti nem található:" + id);
    }

}
