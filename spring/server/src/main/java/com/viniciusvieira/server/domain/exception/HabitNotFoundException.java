package com.viniciusvieira.server.domain.exception;

import java.io.Serial;

public class HabitNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 8944030631485348003L;

    public HabitNotFoundException() {
    }

    public HabitNotFoundException(String message) {
        super(message);
    }
}
