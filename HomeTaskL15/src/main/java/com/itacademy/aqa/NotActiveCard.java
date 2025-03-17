package com.itacademy.aqa;

public class NotActiveCard extends RuntimeException {
    public NotActiveCard() {
        super("Карта заблокирована");
    }
}
