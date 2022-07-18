package com.homel.demo.project.error;

import lombok.Getter;

@Getter
public enum ErrorMessages {
    USER_IS_ALREADY_EXISTED("User is already existed"),
    USER_IS_NOT_FOUND("User is not found");

    private String value;

    ErrorMessages(String value) {
        this.value = value;
    }
}
