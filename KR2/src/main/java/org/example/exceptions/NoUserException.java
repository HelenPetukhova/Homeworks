package org.example.exceptions;

public class NoUserException extends IllegalArgumentException {
    public NoUserException() {

      super("Пользователь с таким userid не зарегестрирован в системе");
    }
}
