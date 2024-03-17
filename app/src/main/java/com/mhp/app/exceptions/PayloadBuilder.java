package com.mhp.app.exceptions;

import lombok.Getter;

@Getter
public class PayloadBuilder {

    private final String message;
    private final boolean success;

    public PayloadBuilder(String message) {
        this.success = true;
        this.message = message;
    }


}