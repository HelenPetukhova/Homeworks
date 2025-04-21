package org.example.models;

public class Account {
    private Integer accountId;
    private final Integer userId;
    private final String currency;
    private final Long balance;

    // Конструктор, когда userId не известен
    public Account(Integer userId, String currency, Long balance) {
        this.userId = userId;
        this.currency = currency;
        this.balance = balance;
    }
    // Конструктор, когда userId не известен и баланс не указан
    public Account(Integer userId, String currency) {
        this.userId = userId;
        this.currency = currency;
        this.balance = 0L;
    }

    // Конструктор, когда userId известен
    public Account(Integer accountId, Integer userId, String currency, Long balance) {
        this.accountId = accountId;
        this.userId = userId;
        this.currency = currency;
        this.balance = balance;
    }

    // Конструктор, когда userId известен и баланс не указан --?? может, не нужен??
    public Account(Integer accountId, Integer userId, String currency) {
        this.accountId = accountId;
        this.userId = userId;
        this.currency = currency;
        this.balance = 0L;
    }


    public Integer getAccountId() {
        return accountId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getCurrency() {
        return currency;
    }

    public Long getBalance() {
        return balance;
    }

    public void printAccount(){
        double showBalance = (double) balance/ 1000;
        System.out.printf("[Accounts] accountId = %d\tuserId = %d\tcurrency = %s\tbalanceToDisplay = %.3f\tbalanceToDisplay = %d%n", accountId, userId, currency, showBalance,balance);  //%d - integer, %s - string
    }
}
