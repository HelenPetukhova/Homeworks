package org.example.exceptions;

public class NegativeAmountException extends IllegalArgumentException {
    public NegativeAmountException() {

      super("При пополнении баланса введено отрецательное значение");
    }
}
