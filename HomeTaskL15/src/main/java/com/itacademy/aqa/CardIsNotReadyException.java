package com.itacademy.aqa;

public class CardIsNotReadyException extends IllegalArgumentException {
    public CardIsNotReadyException() {
        super("Карта не вставлена");
    }
}
