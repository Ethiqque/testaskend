package com.danielpyld.backend.validator;

public final class ValidatorMessage {

    private ValidatorMessage() {
        // Prevent instantiation
    }

    public static final String MANDATORY_MESSAGE = "Field is mandatory";
    public static final String AT_LEAST_ONE_CRITERIA = "At least one criteria must be selected";
}
