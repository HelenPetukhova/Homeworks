package com.itacademy.aqa;

public class Main {
    public static void main(String[] args) throws Exception {

        Atm atm = new Atm();

        Card card1 = new DebitCard("MAKSIM TANK", 100d, 5, 2026);
        Card card2 = new CreditCard("YAKUB KOLAS", 0d, 12, 2025);
        // карта с просроченной датой
        Card card3 = new CreditCard("MAKSIM BAHDANOVICH", -100d, 10, 2024);

//card3.convert(2, "USD");
//        System.out.println(card3.getConvertedBalance());
        System.out.println("Вставьте карту и введите Start");
        do { //card2.cardBlock();
            //card2.setIsActive();
            atm.operations(card1);
        }
        while (!atm.isWorkCompleted());





//        // Активировали карту
//        card2.setIsActive();
//
//        //  Заблокировали карту
//        card2.cardBlock();
//
//        // Передали в atm для работы
//        atm.cardAccept(card2);
//
//        //Проверили баланс
//        atm.balanceInfo(card2);
//
//        //Посмотрели информацию по карте:
//        atm.cardInfo(card2);
//
//        //Пополнили баланс
//        atm.addMoney(card2, 2000d);
//
//        // Снять сумму, превышающую лимит транзакци
//        atm.withdrawMoney(card2, 1001d);
//
//        // Снять сумму
//        atm.withdrawMoney(card2, 400d);
//
//        // Снять сумму, превышающую сумму на карте
//        atm.withdrawMoney(card2, 200d);
//
//        // Посмотреть баланс в другой валюте
//        atm.convert(card2, 0.3, "USD");
//
//        // Завершили работу
//        atm.cardWorkComplete(card2);
//
//        // Попытались узнать баланс, если карта не готова к работе
//        atm.balanceInfo(card2);

    }
}
