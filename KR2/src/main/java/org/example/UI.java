package org.example;

import org.example.daoClasses.AccountDao;
import org.example.daoClasses.TransactionDao;
import org.example.daoClasses.UserDao;
import org.example.exceptions.*;
import org.example.models.Account;
import org.example.models.User;

import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {

    private final Connection connection;
    private final Bank bank;

    public UI(Connection connection) {
        this.connection = connection;
        this.bank = new Bank(connection);
    }

    public void bankInterface() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Вас приветствует бансковский сервис.");

        while (true) {
            System.out.println("Выберите необходимую операцию и введите номер операции:\nСоздание нового пользователя\t- 1\nСоздание нового аккаунта\t\t- 2\n" +
                    "Пополнение счета\t\t\t\t- 3\nСнятие денежных средств\t\t\t- 4\nЗавершение работы\t\t\t\t- 5");
            try {
                byte input = scanner.nextByte();

                switch (input) {
                    case 1:
                        System.out.println("----/Добавление пользователя/-----");
                        registerUserUI();
                        break;
                    case 2:
                        System.out.println("----/Создание аккаунта/-----");
                        createAccountUI();
                        break;
                    case 3:
                        System.out.println("----/Полнение счета/-----");
                        addMoneyUI();
                        break;
                    case 4:
                        System.out.println("----/Снять средства со счета/-----");
                        withdrawMoneyUI();
                        break;
                    case 5:
                        System.out.println("Работа сервиса завершена.");
                        return;
                    // from 6 to 9 for testing purpose to check tables after actions
                    case 6:
                        System.out.println("----/Печать всех пользователей/----");
                        printAllUsersUI();
                    //    return;
                    case 7:
                        System.out.println("----/Печать всех аккаунтов/----");
                        printAllAccountsUI();
                    //    return;
                    case 8:
                        System.out.println("----/Печать всех транзакций/----");
                        printAllTransactionsUI();
                    //    return;
                    default:
                        System.out.println("Введите число от 1 до 5");
                }
            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод. Введите число 1 (Создание нового пользователя), 2 (Создание нового аккаунта), 3 (Пополнение счета), 4 (Снятие денежных средств) или 5 (Завершение работы).");
                scanner.nextLine();
            }
        }
    }


    public void registerUserUI() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя пользователя:");

        while (true) {
            try {

                String nameInput = scanner.nextLine().trim();

                if (nameInput.equals("-1")) {
                    System.out.println("Переход в основное меню.");
                    break;
                }
                System.out.println("name = " + nameInput);
                System.out.println("Введите адрес или нажмите Enter, если не планируете указывать адрес: ");
                String addressInput = scanner.nextLine().trim();
                System.out.println("address = " + addressInput);
                User user = new User(nameInput, addressInput);
                bank.registerUser(user);
                System.out.println("Пользователь " + user.getName() + " успешно зарегестрирован.");
                break;
            } catch (EmptyNameException ex) {
                System.out.println(ex.getMessage());
                System.out.println("Введите имя пользователя или '-1', чтобы вернуться в основное меню.");
            }
        }
    }

    public void createAccountUI() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите userId пользователя, для которого создается аккаунт: ");
        // цикл, который позволяет ввести userId заново при неправильном вводе
        while (true) {
            try {
                int userIdInput = scanner.nextInt();
                scanner.nextLine();            // нужно добавлять после scanner.nextInt(), чтобы следующий scanner.nextLine() не подхватывал \n после ввода числа

                if (userIdInput == -1) {           // если не хочется продолжать вводить userId
                    System.out.println("Переход в основное меню.");
                    break;
                }
                System.out.println("UserId = " + userIdInput);

                System.out.println("Введите код валюты (EUR, USD, GBP, RUB, BYN): "); // (EUR, USD, GBP, RUB, BYN)
                String currencyInput = scanner.nextLine().trim();

                Account account = new Account(userIdInput, currencyInput);
                try {
                    bank.addAccount(account);
                    System.out.println("Новый аккаунт в валюте " + account.getCurrency() + " для userId = " + account.getUserId() + " создан успешно.");
                    break;
                } catch (NoUserException ex) {
                    System.out.println(ex.getMessage());
                    System.out.println("Введите корректное значение userId или '-1', чтобы вернуться в основное меню:");
                } catch (AccountExistsException | EmptyAccountFieldsException ex) {
                    System.out.println(ex.getMessage());
                    System.out.println("Введите userId или '-1', чтобы вернуться в основное меню:");
                }
            } catch (InputMismatchException e) {
                System.out.println("Неверный формат userId. Введите корректное значение userId или '-1', чтобы вернуться в основное меню:");
                scanner.nextLine();          // нужно, чтобы очистить неправильный ввод
            }

        }
    }



    public void addMoneyUI() {
        Scanner scanner = new Scanner(System.in);
        int userIdInput;

        while (true) {
            try {
                System.out.println(" Введите корректное значение userId или '-1', чтобы вернуться в основное меню:");
                userIdInput = scanner.nextInt();
                scanner.nextLine();
                System.out.println("UserId = " + userIdInput);

                if (userIdInput == -1) {           // если не хочется продолжать вводить userId
                    System.out.println("Переход в основное меню.");
                    return;
                }

                break;
            } catch (InputMismatchException e) {
                System.out.println("Неверный формат userId.");
                scanner.nextLine();         // нужно, чтобы очистить неправильный ввод
            }
        }

        System.out.println("Введите код валюты (EUR, USD, GBP, RUB, BYN): "); // (EUR, USD, GBP, RUB, BYN)
        String currencyInput = scanner.nextLine().trim();

        while (true) {
            try {
                System.out.println("Введите сумму для пополнения баланса или '-1', чтобы вернуться в основное меню: ");

                double amountInput = scanner.nextDouble();

                if (amountInput == -1) {           // если не хочется продолжать вводить userId
                    System.out.println("Переход в основное меню.");
                    return;
                }

                double amountInputTruncated = Math.floor(amountInput * 1000) / 1000.0;    // обрезаем, если после запятой больше 3 знаков
                System.out.println("Указана сумма для пополнения баланса amount = " + amountInputTruncated);
                long amount = (long) (amountInputTruncated * 1000L);                       // умножаем на 1000, т.к. храним в такм виде в базе

                try {
                    bank.addMoney(userIdInput, currencyInput, amount);
                    System.out.println("Баланс пополнен на сумму " + amountInputTruncated);
                    break;
                } catch (NoUserException |
                         NoAccountException ex) {                  // если нет такого userId или аккаунта, то выходм в меню
                    System.out.println(ex.getMessage());
                    return;
                } catch (NegativeAmountException | ExceedAmountLimitException | ExceedBalanceLimitException ex) {
                    System.out.println(ex.getMessage());                             // если что-то не так с введенной суммой, то готовы принять новое введение суммы

                }

            } catch (InputMismatchException e) {
                System.out.println("Неверный формат суммы.");
                scanner.nextLine();         // нужно, чтобы очистить неправильный ввод
            }
        }
    }



    public void withdrawMoneyUI() {
        Scanner scanner = new Scanner(System.in);
        int userIdInput;

        while (true) {
            try {
                System.out.println(" Введите корректное значение userId или '-1', чтобы вернуться в основное меню:");
                userIdInput = scanner.nextInt();
                scanner.nextLine();
                System.out.println("UserId = " + userIdInput);

                if (userIdInput == -1) {           // если не хочется продолжать вводить userId
                    System.out.println("Переход в основное меню.");
                    return;
                }

                break;
            } catch (InputMismatchException e) {
                System.out.println("Неверный формат userId.");
                scanner.nextLine();
            }
        }

        System.out.println("Введите код валюты (EUR, USD, GBP, RUB, BYN): ");
        String currencyInput = scanner.nextLine().trim();

        while (true) {
            try {
                System.out.println("Введите положительную сумму, которую нужно снять, или '-1', чтобы вернуться в основное меню: ");

                double amountInput = scanner.nextDouble();

                if (amountInput == -1) {           // если не хочется продолжать вводить userId
                    System.out.println("Переход в основное меню.");
                    return;
                }

                double amountInputTruncated = Math.floor(amountInput * 1000) / 1000.0;    // обрезаем, если после запятой больше 3 знаков
                System.out.println("Указана сумма для снятия amount = " + amountInputTruncated);
                long amount = (long) (amountInputTruncated * 1000L);                       // умножаем на 1000, т.к. храним в такм виде в базе

                try {
                    bank.withdrawMoney(userIdInput, currencyInput, amount);
                    System.out.println("Со счета снята сумма " + amountInputTruncated);
                    break;
                } catch (NoUserException |
                         NoAccountException ex) {                  // если нет такого userId или аккаунта, то выходм в меню
                    System.out.println(ex.getMessage());
                    return;
                } catch (NegativeAmountException | ExceedAmountLimitException | NegativeBalanceException ex) {
                    System.out.println(ex.getMessage());                             // если что-то не так с введенной суммой, то готовы принять новое введение суммы

                }

            } catch (InputMismatchException e) {
                System.out.println("Неверный формат суммы.");
                scanner.nextLine();         // нужно, чтобы очистить неправильный ввод
            }
        }
    }



    public void printAllUsersUI() {
        UserDao userDao = new UserDao(connection);
        userDao.printAllUsers();
    }

    public void printAllAccountsUI() {
        AccountDao accountDao = new AccountDao(connection);
        accountDao.printAllAccounts();
    }

    public void printAllTransactionsUI() {
        TransactionDao transactionDaoDao = new TransactionDao(connection);
        transactionDaoDao.printAllTransactions();
    }
}