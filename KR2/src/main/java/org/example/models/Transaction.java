package org.example.models;

import java.time.LocalDateTime;

public class Transaction {
    private Integer transactionId;
    private final Integer accountId;
    private final Long amount;
    private final LocalDateTime timestamp;


    // Конструкторы без transactionId
    public Transaction(Integer accountId, Long amount, LocalDateTime timestamp) {

        this.accountId = accountId;
        this.amount = amount;
        this.timestamp = timestamp;
    }


    public Transaction(Integer accountId, Long amount) {

        this.accountId = accountId;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }


    // Конструкторы c transactionId
    public Transaction (Integer transactionId, Integer accountId, Long amount, LocalDateTime timestamp) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.amount = amount;
        this.timestamp = timestamp;
    }


    public Transaction(Integer transactionId, Integer accountId, Long amount) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();

        //    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//    String formattedNow = LocalDateTime.now().format(formatter);
    }


    public Integer getTransactionId() {
        return transactionId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public Long getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void printTransaction(){
        double showAmount = (double) amount/1000;
        System.out.printf("[Transactions] transactionId = %d\taccountId = %d\tamountToDisplay = %.3f\tamount = %d\ttimestamp=%tY-%tm-%td %tH-%tM-%tS%n",
                transactionId, accountId, showAmount, amount, timestamp, timestamp, timestamp, timestamp, timestamp, timestamp);
    }
}
