package com.test;

public class Wrapper {
    private final String prefix;

    public Wrapper(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return prefix;
    }
}
