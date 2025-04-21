package org.example.exceptions;

public class ExceedAmountLimitException extends IllegalArgumentException {
    public ExceedAmountLimitException() {

      super("Величина транзакции не должна превышать 100'000'000");
    }
}
