package org.example.libman.dtos;

import lombok.Getter;

@Getter
public class ConflictMessage {

    private final String code;
    private final String task;
    private final String message;

    public ConflictMessage(String code, String task, String message) {
        this.code = code;
        this.task = task;
        this.message = message;
    }
}
