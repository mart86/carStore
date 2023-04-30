package com.martynov.carStore.utils;

public enum BMWModel {
    X5("BMW X5"),
    X6("BMW X6"),
    X7("BMW X7");

    private String name;

    BMWModel(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
