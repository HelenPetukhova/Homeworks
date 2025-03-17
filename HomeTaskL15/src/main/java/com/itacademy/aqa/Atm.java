package com.itacademy.aqa;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Atm {

    protected boolean cardIsReady = false;
    protected boolean isWorkCompleted = false;

    public Atm() {
    }

    // Карту передаем в банкомат, проверяем, активная ли карта, если нет - она не готова к работе и мы ее не принимаем в работу,
    // если да, то маркируем ее как готовую к работе
    public void cardAccept(Card card) {
        try {
            card.isActive();
            this.cardIsReady = true;    // проверка, что меняется на true +
        } catch (NotActiveCard ex) {
            System.out.println(ex.getMessage());
            System.out.println(cardWorkComplete(card));    //   проверка сообщения, что работа закончена
        }
    }


    // Подготовлена ли карта к работе
    public boolean getCardIsReady(Card card) {
        if (!cardIsReady) {
            throw new CardIsNotReadyException(); // поверка сообщения с картой, которая не вставлена +
        }
        return cardIsReady;
    }

    // Информация по банковской карте:
    public String cardInfo(Card card) {     // проверка текста???
        StringBuilder info = new StringBuilder();
        info.append("Владелец карты: \t" + card.getOwner() + "\n");
        info.append("Номер карты: \t" + card.getNumber() + "\n");
        info.append("Срок действия до: \t" + card.getExpireMonth() + "/" + card.getExpireYear() + "\n");
        info.append("Баланс: \t" + card.getBalance() + "BYN" + "\n");
        info.append("Статус активности карты: \t");
        try {
            card.isActive();
            info.append("Активна");
        } catch (NotActiveCard e) {
            info.append(e.getMessage());
        }

        return info.toString();
    }


    //Реализация операции просмотра баланса и информации по карте
//    public void balanceInfo(Card card) {
//        try {
//            getCardIsReady(card);
//            System.out.println("Баланс на карте: " + card.getBalance());
//        } catch (IllegalArgumentException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }

    //Реализация операции снятия суммы
    public String withdrawMoney(Card card, double amount) {
        try {
            getCardIsReady(card);
            card.withdrawal(amount);
            return "Баланс на карте: " + card.getBalance();  // +Проверка сообщения
        } catch (InsufficientFundsException ex) {
            return ex.getMessage() + card.balance;  // проверка сообщения +
        } catch (LimitTransactionException ex) {
            return ex.getMessage();                 // проверка сообщений+
        }
    }


    //Реализация операции пополнения баланса
    public String addMoney(Card card, double amount) {
        try {
            getCardIsReady(card);
            card.addMoney(amount);
            return "Баланс на карте: " + card.getBalance(); // проверка баланса +
        } catch (CardIsNotReadyException ex) {
            return ex.getMessage();  // проверка сообщения+
        }
    }

    // Реализация операции перевода баланса в другой валюте
    public String convert(Card card, double exchangeRate, String currencyCode) {
        try {
            getCardIsReady(card);
            card.convert(exchangeRate, currencyCode);
            return "Баланс на карте: " + card.convertedBalance + " " + currencyCode; // проверка сообщения/баланса
        } catch (CardIsNotReadyException ex) {
            return ex.getMessage();  // проверка сообщения+
        }
    }

    // Отключение карты (завершение работы с картой)
    public String cardWorkComplete(Card card) {

        this.cardIsReady = false;    // проверка статуса+
        this.isWorkCompleted = true;    // проверка статуса+
        return "Работа с картой завершена";   // проверка сообщения
    }

    // маркер завершения работы
    public boolean isWorkCompleted() {
        return isWorkCompleted;      // проверка завершения
    }

    // Метод для взаимодействия с Atm: Start - карта вставлена в банкомат, Info - получить информацию по карте
    //Add - добавить сумму на карту, Withdraw - снять сумму с карты, Complete - закончить работу
    Scanner scanner = new Scanner(System.in);

    public void operations(Card card) {

        String input = scanner.nextLine();
        if (input.equals("Complete")) {
            System.out.println(cardWorkComplete(card));
//        } else if (input.equals("Start") && !card.isActive()) {
//            cardAccept(card);
//            cardInfo(card);
        } else if (input.equals("Start")) {
            try {
                card.isActive();
            } catch (NotActiveCard e) {
                System.out.println(e.getMessage());
                System.out.println(cardWorkComplete(card));
                return;
            }
            cardAccept(card);
            System.out.println(cardInfo(card));
            System.out.println("--------------------------");
            System.out.println("Карта готова к работе.");
            System.out.println("--------------------------");
            System.out.println("Выберите операцию:\nнаберите Start, чтобы начать работать с картой,\nнаберите Info, чтобы получить информацию по карте,\nнаберите Add, чтобы добавить сумму на карту,\nнаберите Withdraw, чтобы снять сумму с карты,\nнаберите Complete, чтобы закончить работу");

        } else if (input.equals("Info")) {
            System.out.println(cardInfo(card));
            System.out.println("--------------------------");
            System.out.println("Выберите операцию:\nнаберите Start, чтобы начать работать с картой,\nнаберите Info, чтобы получить информацию по карте,\nнаберите Add, чтобы добавить сумму на карту,\nнаберите Withdraw, чтобы снять сумму с карты,\nнаберите Complete, чтобы закончить работу");

        } else if (input.equals("Add")) {
            System.out.println("Введите сумму, которую хотите добавить на карту");
            try {
                double amount = scanner.nextDouble();
                scanner.nextLine();
                System.out.println(addMoney(card, amount));
                System.out.println("--------------------------");
            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод. Введите число");
                scanner.nextLine();
            }
            System.out.println("Выберите операцию:\nнаберите Start, чтобы начать работать с картой,\nнаберите Info, чтобы получить информацию по карте,\nнаберите Add, чтобы добавить сумму на карту,\nнаберите Withdraw, чтобы снять сумму с карты,\nнаберите Complete, чтобы закончить работу");

        } else if (input.equals("Withdraw")) {
            System.out.println("Введите сумму, которую хотите снять с карты");
            try {
                double amount = scanner.nextDouble();
                scanner.nextLine();
                System.out.println(withdrawMoney(card, amount));
                System.out.println("--------------------------");
                System.out.println("Выберите операцию:\nнаберите Start, чтобы начать работать с картой,\nнаберите Info, чтобы получить информацию по карте,\nнаберите Add, чтобы добавить сумму на карту,\nнаберите Withdraw, чтобы снять сумму с карты,\nнаберите Complete, чтобы закончить работу");
            } catch (InputMismatchException ex) {
                System.out.println("Неверный ввод. Введите число");
                scanner.nextLine();

            }
        } else {
            System.out.println("Неверный ввод");
            System.out.println("Выберите операцию:\nнаберите Start, чтобы начать работать с картой,\nнаберите Info, чтобы получить информацию по карте,\nнаберите Add, чтобы добавить сумму на карту,\nнаберите Withdraw, чтобы снять сумму с карты,\nнаберите Complete, чтобы закончить работу");

        }

    }
}