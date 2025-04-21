package org.example.exceptions;

public class NoAccountException extends IllegalArgumentException {
    public NoAccountException() {

      super("Нет аккаунта с таким userid и currency");
    }
}
