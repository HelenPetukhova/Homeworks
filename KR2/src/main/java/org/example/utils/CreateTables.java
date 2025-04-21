package org.example.utils;

import java.sql.*;

public class CreateTables {
private final Connection connection;

    public CreateTables(Connection connection) {
        this.connection = connection;
    }
    // Создание пользователя

    public void createTables() {
        //Cоздаем statement = connection.createStatement(); connection = DriverManager.getConnection(connectionString);
        try (Statement statement = connection.createStatement()) {

            String createUsersTableQuery = "CREATE TABLE IF NOT EXISTS users (\n" +
                    "\t\tid INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\tname TEXT NOT NULL,\n" +
                    "\t\taddress TEXT NULL);\n";

            statement.execute(createUsersTableQuery);   // execute for SELECT and CREATE TABLE, где не нужно выводить результат
            System.out.println("Таблица users готова");

            String createAccountsTableQuery = "CREATE TABLE IF NOT EXISTS accounts (\n" +
                    "\t\tid INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "\t\tuser_id INTEGER NOT NULL,\n" +
                    "\t\tcurrency TEXT NOT NULL,\n" +
                    "\t\tbalance INTEGER NOT NULL DEFAULT 0,\n" +
                    "\t\tCONSTRAINT unique_user_currency UNIQUE (user_id, currency), \n" +
                    "\t\tCONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES users(id),\n" +
                    "\t\tCONSTRAINT CHK_accounts CHECK (balance BETWEEN 0 AND 2000000000000)\n" +
                    "\t\t);";

            statement.execute(createAccountsTableQuery);
            System.out.println("Таблица accounts готова");

            String createTransactionsTableQuery = "CREATE TABLE IF NOT EXISTS transactions (\n" +
                    "\t\tid INTEGER PRIMARY KEY,\n" +
                    "\t\taccount_id INTEGER NOT NULL,\n" +
                    "\t\tamount INTEGER NOT NULL,\n" +
                    "\t\ttimestamp DATETIME DEFAULT CURRENT_TIMESTAMP,\n" +
                    "\t\tCONSTRAINT account_fk FOREIGN KEY (account_id) REFERENCES accounts(id),\n" +
                    "\t\tCONSTRAINT CHK_transactions CHECK (amount BETWEEN -100000000000 AND 100000000000)\n" +
                    "\t\t);";

            statement.execute(createTransactionsTableQuery);
            System.out.println("Таблица transactions готова");

            // drop for testing purpose
//            String dropUsersTableQuery = "DROP TABLE IF EXISTS users;";
//            statement.execute(dropUsersTableQuery);
//            System.out.println("Таблица users удалена");
//
//            String dropAccountsTableQuery = "DROP TABLE IF EXISTS accounts;";
//            statement.execute(dropAccountsTableQuery);
//            System.out.println("Таблица accounts удалена");
//
//            String dropTransactionsTableQuery = "DROP TABLE IF EXISTS transactions;";
//            statement.execute(dropTransactionsTableQuery);
//            System.out.println("Таблица transactions удалена");



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
