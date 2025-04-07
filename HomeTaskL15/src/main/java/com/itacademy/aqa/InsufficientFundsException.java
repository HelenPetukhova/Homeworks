package com.itacademy.aqa;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException() {
        super("Недостаточно средств на счёте. Вы можете снять не более: ");
    }
}
