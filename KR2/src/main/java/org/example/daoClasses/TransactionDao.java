package org.example.daoClasses;

import org.example.models.Transaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao {
    private final Connection connection;

public static final String ADD_TRANSACTION_QUERY =
        "INSERT INTO transactions (account_id, amount, timestamp) VALUES(?, ?, ?)";

    public static final String FIND_ALL_TRANSACTION_OF_ACCOUNT_QUERY =
            "SELECT * FROM transactions WHERE account_id = ?";

    public static final String FIND_ALL_TRANSACTIONS_QUERY =
            "SELECT * FROM transactions";

// Конструктор, передаем в него connection
    public TransactionDao(Connection connection) {
        this.connection = connection;
    }

// Метод по добавлению транзакции в transactions таблицу
    public void createTransaction(Transaction transaction){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_TRANSACTION_QUERY)){
            preparedStatement.setInt(1, transaction.getAccountId());
            preparedStatement.setLong(2, transaction.getAmount());
            preparedStatement.setString(3, transaction.getTimestamp().format(dateTimeFormatter));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

// Метод, чтобы получить все транзакции по одному аккаунту

public List<Transaction> getAllTransactionsOfAccount(Integer accountId) {
    List<Transaction> transactions = new ArrayList<>();

    try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_TRANSACTION_OF_ACCOUNT_QUERY)) {
        preparedStatement.setInt(1, accountId);
        ResultSet resultSet = preparedStatement.executeQuery();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        while (resultSet.next()) {
            transactions.add(new Transaction(
                    resultSet.getInt("id"),
                    resultSet.getInt("account_id"),
                    resultSet.getLong("amount"),
                    LocalDateTime.parse(resultSet.getString("timestamp"),dateTimeFormatter)));

        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return transactions;
}


    public void printAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_TRANSACTIONS_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            while (resultSet.next()) {                          // проходим по всем строкам результата
                        transactions.add(new Transaction(
                                resultSet.getInt("id"),
                                resultSet.getInt("account_id"),
                                resultSet.getLong("amount"),
                                LocalDateTime.parse(resultSet.getString("timestamp"), dateTimeFormatter)));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Список всех аккаунтов из таблицы accounts");
        for(Transaction transaction : transactions) {
            transaction.printTransaction(); }
    }

}
