package com.itacademy.aqa;

public class LimitTransactionException extends IllegalArgumentException {
    public LimitTransactionException() {
        super("Превышен лимит транзакций по карте");
    }
}
