package org.example.daoClasses;

import org.example.models.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    private final Connection connection;

    public static final String ADD_ACCOUNT_QUERY =
            "INSERT INTO accounts(user_id, currency, balance) VALUES (?, ?, ?)";

    public static final String FIND_ACCOUNT_BY_USER_AND_CURRENCY_QUERY =
            "SELECT * FROM accounts WHERE user_id = ? AND currency = ?";

    public static final String FIND_ALL_ACCOUNTS_OF_USER_QUERY =
            "SELECT * FROM accounts WHERE user_id = ?";

    public static final String FIND_ALL_ACCOUNTS_QUERY =
            "SELECT * FROM accounts";

    public static final String UPDATE_BALANCE_QUERY =
            "UPDATE accounts SET balance = balance + ? WHERE id = ?";

    public AccountDao(Connection connection) {
        this.connection = connection;
    }


    // Добавляем новый аккаунт в таблицу accounts
    public void createAccount(Account account) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_ACCOUNT_QUERY)) {
            preparedStatement.setInt(1, account.getUserId());
            preparedStatement.setString(2, account.getCurrency());
            preparedStatement.setLong(3, account.getBalance());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Получаем строчку из accounts по user_id и currency
    public Account getAccountByUserIdCurrency(Integer userId, String currency) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ACCOUNT_BY_USER_AND_CURRENCY_QUERY)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, currency);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {                          // проходим по всем строкам результата
                return new Account(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("currency"),
                        resultSet.getLong("balance"));
            } else {
                return null;
                // throw new SQLException("Аккаунта с user_id = " + userId + " в " + currency + " не существует");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // Получаем список аккаунтов для одного пользователя
    public List<Account> getAllAccountsOfUser(Integer userId) {
        List<Account> accounts = new ArrayList<>();  // cоздаем пустой список, куда будем сохранять данные по аккаунтам пользователя

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_ACCOUNTS_OF_USER_QUERY)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {                          // проходим по всем строкам результата
                accounts.add(new Account(                             // каждый раз добавляем новый объект Account
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("currency"),
                        resultSet.getLong("balance")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accounts;                                           // возвращаем список пользователей
    }


    // обновляем баланс при изменении его
    public void updateBalance(Integer accountId, Long transactionAmount) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BALANCE_QUERY)) {
            preparedStatement.setLong(1, transactionAmount);
            preparedStatement.setInt(2, accountId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void printAllAccounts() {
        List<Account> accounts = new ArrayList<>();  // cоздаем пустой список, куда будем сохранять данные по аккаунтам пользователя

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_ACCOUNTS_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {                          // проходим по всем строкам результата
                accounts.add(new Account(                             // каждый раз добавляем новый объект Account
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("currency"),
                        resultSet.getLong("balance")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Список всех аккаунтов из таблицы accounts");
        for (Account account : accounts) {
            account.printAccount();
        }
    }

}
