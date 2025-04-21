package org.example.exceptions;

public class EmptyNameException extends IllegalArgumentException{

    public EmptyNameException() {
        super("Поле name не может быть пустым");
    }
}
