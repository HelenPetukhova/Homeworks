package Task1;

import java.time.LocalDate;
import java.util.Random;

public class Card {
    protected String owner;
    protected long number;
    protected double balance;
    protected int expireMonth;
    protected int expireYear;
    protected double convertedBalance;
    protected boolean isActive = false;
    protected double limit = 1000d;
//    protected String cardType;


    Random random = new Random();
    LocalDate currentDate = LocalDate.now();

    // подумать, что должно передаваться в конструктор без параметров
    public Card() {
        this.owner = "";
        this.number = random.nextLong(1000000000000000L, 9999999999999999L);
        this.balance = 0d;

    }

    // Конструктор с параметрами, номер карточки условно возьмем ранодоное 16-значное число
    public Card(String owner, double balance, int expireMonth, int expireYear) {
        this.owner = owner;
        this.number = random.nextLong(1000000000000000L, 9999999999999999L);
        this.balance = balance;
        this.expireMonth = expireMonth;
        this.expireYear = expireYear;
        if (currentDate.getYear() < this.expireYear) {
            this.isActive = true;
        } else if (currentDate.getYear() == this.expireYear && currentDate.getMonthValue() < this.expireMonth) {
            this.isActive = true;
        } else {
            this.isActive = false;
        }

    }

    // Метод для получения статуса активности карты:
    public boolean getIsActive() {
        if (!isActive) {
            throw new RuntimeException("Карта заблокирована или не актививирована");
        }
        return isActive;
    }

    // Метод для получения баланса
    public double getBalance() {
        return balance;
    }

    // Метод для получения владельца карты

    public String getOwner() {
        return owner;
    }

    // Метод для получения номера карты

    public long getNumber() {
        return number;
    }

    // Метод для получения месяца, до которого активна карта

    public int getExpireMonth() {
        return expireMonth;
    }


    // Метод для получения года, до которого активна карта

    public int getExpireYear() {
        return expireYear;
    }

    // Метод для активирования карты:
    public void setIsActive() {
        isActive = true;
    }

    // Метод для блокировки карты:
    public void cardBlock() {
        isActive = false;
    }

    //Метод для пополнения баланса
    public void addMoney(double rechargeAmount) {

        this.balance += rechargeAmount;
    }

    //Метод для снятия денег
    public void withdrawal(double withdrawalAmount) {
        if (withdrawalAmount > limit) {
            throw new IllegalArgumentException("Превышен лимит транзакций по карте");
        } else {
            this.balance -= withdrawalAmount;
        }
    }

    //Метод изменения лимита по карте
    public void setLimit(double limit) {
        this.limit = limit;
    }

    //Метод канвертации баланса в другую валюту
    public void convert(double exchangeRate, String currencyCode) {
        convertedBalance = balance * exchangeRate;
    }


}
