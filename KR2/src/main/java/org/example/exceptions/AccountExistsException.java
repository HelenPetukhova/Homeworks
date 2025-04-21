package org.example.exceptions;

public class AccountExistsException extends IllegalArgumentException {
    public AccountExistsException() {

      super("Аккаунт в такой валюте для данного пользователя уже существует");
    }
}
