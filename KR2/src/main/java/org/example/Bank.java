package org.example;

import org.example.daoClasses.AccountDao;
import org.example.daoClasses.TransactionDao;
import org.example.daoClasses.UserDao;
import org.example.exceptions.*;
import org.example.models.Account;
import org.example.models.Transaction;
import org.example.models.User;

import java.sql.Connection;

public class Bank {
    private final Connection connection;


    public Bank(Connection connection) {
        this.connection = connection;
    }

    public void registerUser(User user) {
        if (user.getName() == null || user.getName().trim().isEmpty()) {  // проверяем, что name не может быть пустым
            throw new EmptyNameException();
        } else {
            UserDao userDao = new UserDao(this.connection);
            userDao.createUser(user);
        }
    }

    public void addAccount(Account account) {
        if (account.getUserId() == null ||                                                  // проверяем, что userId не пустое
                account.getCurrency() == null || account.getCurrency().trim().isEmpty()   // проверяем, что currency не пустое
        ) {
            throw new EmptyAccountFieldsException();
        }

        UserDao userDao = new UserDao(connection);
        if (userDao.getUserByUserId(account.getUserId()) == null) {                     //проверяем, что пользователь с таким user_id зарегестрирован в системе
            throw new NoUserException();
        }
        AccountDao accountDao = new AccountDao(this.connection);
        if (accountDao.getAccountByUserIdCurrency(account.getUserId(), account.getCurrency()) != null) {
            throw new AccountExistsException();
        } else {
            accountDao.createAccount(account);
        }
    }

    public void addMoney(Integer userId, String currency, Long amount) {

        if (amount < 0L) {
            throw new NegativeAmountException();
        }
        if (amount > 100_000_000_000L) {
            throw new ExceedAmountLimitException();
        }
        UserDao userDao = new UserDao(connection);
        if (userDao.getUserByUserId(userId) == null) {                     //проверяем, что пользователь с таким user_id зарегестрирован в системе
            throw new NoUserException();
        }
        AccountDao accountDao = new AccountDao(this.connection);
        Account account = accountDao.getAccountByUserIdCurrency(userId, currency);  // опеределяем аккаунт для пополнения
        if (account == null) {                                                        // если не найден - exception
            throw new NoAccountException();
        } else {
            if (account.getBalance() + amount > 2_000_000_000_000L) {               // если найден, то проверяем на лимит по балансу
                throw new ExceedBalanceLimitException();                           // если видим, что баланс будет превышен, то выдаем exception и дальше не идем
            } else {
                TransactionDao transactionDao = new TransactionDao(this.connection);
                Transaction transaction = new Transaction(account.getAccountId(), amount);  //передаем AccountId и сумму для пополнения баланса
                transactionDao.createTransaction(transaction);                              // добавляем транзакцию
                accountDao.updateBalance(account.getAccountId(), amount);                   // теперь можем обновить баланс
            }
        }
    }


    public void withdrawMoney(Integer userId, String currency, Long amount) {

        if (amount < 0L) {
            throw new NegativeAmountException();
        }
        if (amount > 100_000_000_000L) {
            throw new ExceedAmountLimitException();
        }
        AccountDao accountDao = new AccountDao(this.connection);
        Account account = accountDao.getAccountByUserIdCurrency(userId, currency);  // опеределяем аккаунт для пополнения
        if (account == null) {                                                        // если не найден - exception
            throw new NoAccountException();
        }

        if (account.getBalance() - amount < 0L) {               // если найден, то проверяем, чтобы баланс не оказался отрецательным после операции
            throw new NegativeBalanceException();
        }
        Long amountWithSign = -amount;
        TransactionDao transactionDao = new TransactionDao(this.connection);
        Transaction transaction = new Transaction(account.getAccountId(), amountWithSign);  //передаем AccountId и сумму для пополнения баланса
        transactionDao.createTransaction(transaction);
        accountDao.updateBalance(account.getAccountId(), amountWithSign);

    }


}
