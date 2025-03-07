package Task1;

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
            card.getIsActive();
            this.cardIsReady = true;
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
            cardWorkComplete(card);
        }
    }


    // Подготовлена ли карта к работе
    public boolean getCardIsReady(Card card) {
        if (!cardIsReady) {
            throw new IllegalArgumentException("Карта не вставлена");
        }
        return cardIsReady;
    }

    // Информация по банковской карте:
    public void cardInfo(Card card) {
        System.out.println("Владелец карты: \t" + card.getOwner());
        System.out.println("Номер карты: \t" + card.getNumber());
        System.out.println("Срок действия до: \t" + card.getExpireMonth() + "/" + card.getExpireYear());
        System.out.println("Баланс: \t" + card.getBalance() + "BYN");
        System.out.println("Статус активности карты: \t");
        try {
            card.getIsActive();
            System.out.println("Активна");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

    }


    //Реализация операции просмотра баланса и информации по карте
    public void balanceInfo(Card card) {
        try {
            getCardIsReady(card);
            System.out.println("Баланс на карте: " + card.getBalance());
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //Реализация операции снятия суммы
    public void withdrawMoney(Card card, double amount) {
        try {
            getCardIsReady(card);
            card.withdrawal(amount);
            System.out.println("Баланс на карте: " + card.getBalance());
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }


    //Реализация операции пополнения баланса
    public void addMoney(Card card, double amount) {
        try {
            getCardIsReady(card);
            card.addMoney(amount);
            System.out.println("Баланс на карте: " + card.getBalance());
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Реализация операции перевода баланса в другой валюте
    public void convert(Card card, double exchangeRate, String currencyCode) {
        try {
            getCardIsReady(card);
            card.convert(exchangeRate, currencyCode);
            System.out.println("Баланс на карте: " + card.convertedBalance + " " + currencyCode);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Отключение карты (завершение работы с картой)
    public void cardWorkComplete(Card card) {

        this.cardIsReady = false;
        System.out.println("Работа с картой завершена");
        this.isWorkCompleted = true;

    }

// маркер завершения работы
    public boolean isWorkCompleted() {
        return isWorkCompleted;
    }

    // Метод для взаимодействия с Atm: Start - карта вставлена в банкомат, Info - получить информацию по карте
    //Add - добавить сумму на карту, Withdraw - снять сумму с карты, Complete - закончить работу
    Scanner scanner = new Scanner(System.in);

    public void operations(Card card) {

        String input = scanner.nextLine();
        if (input.equals("Complete")) {
            cardWorkComplete(card);
        } else if (input.equals("Start") && !card.isActive) {
            cardAccept(card);
            cardInfo(card);
        } else if (input.equals("Start")) {
            cardAccept(card);
            cardInfo(card);
            System.out.println("--------------------------");
            System.out.println("Карта готова к работе.");
            System.out.println("--------------------------");
            System.out.println("Выберите операцию:\nнаберите Start, чтобы начать работать с картой,\nнаберите Info, чтобы получить информацию по карте,\nнаберите Add, чтобы добавить сумму на карту,\nнаберите Withdraw, чтобы снять сумму с карты,\nнаберите Complete, чтобы закончить работу");

        } else if (input.equals("Info")) {
            cardInfo(card);
            System.out.println("--------------------------");
            System.out.println("Выберите операцию:\nнаберите Start, чтобы начать работать с картой,\nнаберите Info, чтобы получить информацию по карте,\nнаберите Add, чтобы добавить сумму на карту,\nнаберите Withdraw, чтобы снять сумму с карты,\nнаберите Complete, чтобы закончить работу");

        } else if (input.equals("Add")) {
            System.out.println("Введите сумму, которую хотите добавить на карту");
            try{double amount = scanner.nextDouble();
            scanner.nextLine();
            addMoney(card, amount);
            System.out.println("--------------------------");
            }
            catch (InputMismatchException e){
                System.out.println("Неверный ввод. Введите число");
                scanner.nextLine();
            }
            System.out.println("Выберите операцию:\nнаберите Start, чтобы начать работать с картой,\nнаберите Info, чтобы получить информацию по карте,\nнаберите Add, чтобы добавить сумму на карту,\nнаберите Withdraw, чтобы снять сумму с карты,\nнаберите Complete, чтобы закончить работу");

        } else if (input.equals("Withdraw")) {
            System.out.println("Введите сумму, которую хотите снять с карты");
            try{
            double amount = scanner.nextDouble();
            scanner.nextLine();
            withdrawMoney(card, amount);
            System.out.println("--------------------------");
            System.out.println("Выберите операцию:\nнаберите Start, чтобы начать работать с картой,\nнаберите Info, чтобы получить информацию по карте,\nнаберите Add, чтобы добавить сумму на карту,\nнаберите Withdraw, чтобы снять сумму с карты,\nнаберите Complete, чтобы закончить работу");
            }
            catch (InputMismatchException ex) {
                System.out.println("Неверный ввод. Введите число");
                scanner.nextLine();

            }
        } else {
            System.out.println("Неверный ввод");
            System.out.println("Выберите операцию:\nнаберите Start, чтобы начать работать с картой,\nнаберите Info, чтобы получить информацию по карте,\nнаберите Add, чтобы добавить сумму на карту,\nнаберите Withdraw, чтобы снять сумму с карты,\nнаберите Complete, чтобы закончить работу");

        }

    }
}

