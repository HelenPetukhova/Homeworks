package org.example.exceptions;

public class EmptyAccountFieldsException extends IllegalArgumentException{

    public EmptyAccountFieldsException() {
        super("Поля userId, currency не могут быть пустым");
    }
}
