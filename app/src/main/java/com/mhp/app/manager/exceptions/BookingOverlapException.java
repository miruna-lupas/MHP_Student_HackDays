package com.mhp.app.manager.exceptions;

import java.text.MessageFormat;

public class BookingOverlapException extends Exception{
    public BookingOverlapException(String message) {
        super(message);
    }

}
