package com.itacademy.aqa;

public class DebitCard extends Card {

    public DebitCard (String owner, double balance, int expireMonth, int expireYear){
        super(owner, balance, expireMonth, expireYear);
    }



    @Override
    public void withdrawal(double withdrawalAmount) {
        if (withdrawalAmount > limit) {
            throw new LimitTransactionException();
        } else if (this.balance - withdrawalAmount < 0) {
            throw new InsufficientFundsException();
        } else {
            this.balance -= withdrawalAmount;
        }
    }
}
