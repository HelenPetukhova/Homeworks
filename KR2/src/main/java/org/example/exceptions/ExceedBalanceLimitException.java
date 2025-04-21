package org.example.exceptions;

public class ExceedBalanceLimitException extends IllegalArgumentException {
    public ExceedBalanceLimitException() {

      super("Операция не может быть произведена, поскольку после операции баланс превысит лимит в 2'000'000'000");
    }
}
