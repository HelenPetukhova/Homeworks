package org.example.exceptions;

public class NegativeBalanceException extends IllegalArgumentException {
    public NegativeBalanceException() {

      super("Недостаточно средств на счете. Введенная сумма для снятия превышает баланс");
    }
}
