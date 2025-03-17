package com.itacademy.aqa;

import org.testng.Assert;

import org.testng.annotations.Test;

public class AtmTest {

    @Test(groups = {"smoke","regression"})
    public void cardIsActiveTest() {
        Card card1 = new DebitCard("MAKSIM TANK", 100d, 5, 2026);
        Assert.assertTrue(card1.isActive(), "Card is not active, but should be active");
    }


    @Test(groups = {"regression"}, expectedExceptions = NotActiveCard.class, expectedExceptionsMessageRegExp = "Карта заблокирована")
    public void testNotActiveCardException() {
        Card card1 = new DebitCard("MAKSIM TANK", 100d, 5, 2022);
        card1.isActive();
    }

    @Test(groups = {"smoke","regression"})
    public void addMoneyDebitTest() {
        Card card1 = new DebitCard("MAKSIM TANK", 100d, 5, 2026);
        card1.addMoney(105.2d);
        Assert.assertEquals(card1.getBalance(), 205.2d, "Balance is not correct after adding money");
    }

    @Test(groups = {"smoke","regression"})
    public void addMoneyCreditTest() {
        Card card2 = new CreditCard("YAKUB KOLAS", 0d, 12, 2025);
        card2.addMoney(1400.52d);
        Assert.assertEquals(card2.getBalance(), 1400.52d, "Balance is not correct after adding money");
    }


    @Test(groups = {"smoke","regression"}, dataProvider = "testAddMoneyCreditData", dataProviderClass = DataProviders.class)
    public void addMoneyCreditParamTest(Card card, double addingAmount) {
        double currentBalance = card.getBalance();
        card.addMoney(addingAmount);
        Assert.assertEquals(card.getBalance(), currentBalance + addingAmount, "Balance is not correct after adding money");
    }

    @Test(groups = {"smoke","regression"}, dataProvider = "testAddMoneyDebitData", dataProviderClass = DataProviders.class)
    public void addMoneyDebitParamTest(Card card, double addingAmount) {
        double currentBalance = card.getBalance();
        card.addMoney(addingAmount);
        Assert.assertEquals(card.getBalance(), currentBalance + addingAmount, "Balance is not correct after adding money");
    }

    @Test(groups = {"regression"})
    public void convertDebitTest() {
        Card card1 = new DebitCard("MAKSIM TANK", 100d, 5, 2026);
        card1.convert(0.31, "USD");
        Assert.assertEquals(card1.getConvertedBalance(), 31.0d, "The converted balance is not correct");
    }


    @Test(groups = {"regression"}, dataProvider = "testConvertData", dataProviderClass = DataProviders.class)
    public void convertParamTest(Card card, double exchangeRate) {
        card.convert(exchangeRate, "USD");
        Assert.assertEquals(card.getConvertedBalance(), card.getBalance() * exchangeRate, "The converted balance is not correct");
    }

    @Test(groups = {"smoke","regression"})
    public void withdrawCreditTest() {
        Card card2 = new CreditCard("YAKUB KOLAS", 5000d, 12, 2025);
        double currentBalance = card2.getBalance();
        card2.withdrawal(card2.limit - 1);
        Assert.assertEquals(card2.getBalance(), currentBalance - (card2.limit - 1), "The balance is not correct after withdrawing");
    }


    @Test(groups = {"smoke","regression"}, dataProvider = "testWithdrawCreditData", dataProviderClass = DataProviders.class)
    public void withdrawCreditParamTest(Card card, double withdrawAmount) {
        double currentBalance = card.getBalance();
        card.withdrawal(withdrawAmount);
        Assert.assertEquals(card.getBalance(), currentBalance - withdrawAmount, "The balance is not correct after withdrawing");
    }


    @Test(groups = {"regression"}, expectedExceptions = LimitTransactionException.class, expectedExceptionsMessageRegExp = "Превышен лимит транзакций по карте")
    public void testLimitTransactionCreditException() {
        Card card1 = new CreditCard("YAKUB KOLAS", 400d, 12, 2025);
        card1.withdrawal(card1.limit + 1);
    }


    @Test(groups = {"smoke","regression"}, dataProvider = "testWithdrawDebitData", dataProviderClass = DataProviders.class)
    public void withdrawDebitParamTest(Card card, double withdrawAmount) {
        double currentBalance = card.getBalance();
        card.withdrawal(withdrawAmount);
        Assert.assertEquals(card.getBalance(), currentBalance - withdrawAmount, "The balance is not correct after withdrawing");
    }

    @Test(groups = {"regression"}, expectedExceptions = LimitTransactionException.class, expectedExceptionsMessageRegExp = "Превышен лимит транзакций по карте")
    public void testLimitTransactionDebitException() {
        Card card1 = new DebitCard("MAKSIM TANK", 2500d, 5, 2026);
        card1.withdrawal(card1.limit + 1);
    }

    @Test(groups = {"regression"}, expectedExceptions = InsufficientFundsException.class, expectedExceptionsMessageRegExp = "Недостаточно средств на счёте. Вы можете снять не более: ")
    public void testInsufficientFundsException() {
        Card card1 = new DebitCard("MAKSIM TANK", 100d, 5, 2026);
        card1.withdrawal(card1.balance + 1);
    }

    @Test(groups = {"smoke","regression"})
    public void cardAcceptedTest() {
        Card card1 = new DebitCard("MAKSIM TANK", 100d, 5, 2026);
        Atm atm = new Atm();
        atm.cardAccept(card1);
        Assert.assertTrue(atm.cardIsReady, "Card is not ready for work");
    }

    @Test(groups = {"regression"})
    public void cardNotAcceptedTest() {
        Card card1 = new DebitCard("MAKSIM TANK", 100d, 5, 2024);
        Atm atm = new Atm();
        atm.cardAccept(card1);
        Assert.assertTrue(atm.isWorkCompleted, "Error: Not active card is accepted for work");
    }


    @Test(groups = {"regression"}, expectedExceptions = CardIsNotReadyException.class, expectedExceptionsMessageRegExp = "Карта не вставлена")
    public void testActiveCardNotInsertedException() {
        Card card1 = new DebitCard("MAKSIM TANK", 100d, 5, 2025);
        Atm atm = new Atm();
        atm.getCardIsReady(card1);
    }

    @Test(groups = {"regression"}, expectedExceptions = CardIsNotReadyException.class, expectedExceptionsMessageRegExp = "Карта не вставлена")
    public void testNotActiveCardIsInsertedException() {
        Card card1 = new DebitCard("MAKSIM TANK", 100d, 5, 2022);
        Atm atm = new Atm();
        atm.cardAccept(card1);
        atm.getCardIsReady(card1);
    }


    @Test(groups = {"smoke","regression"}, dataProvider = "testInfoData", dataProviderClass = DataProviders.class)
    public void infoCreditParamAtmTest(Card card) {
        Atm atm = new Atm();
        atm.cardAccept(card);
        Assert.assertEquals(atm.cardInfo(card),
                "Владелец карты: \t" + card.getOwner() + "\n" + "Номер карты: \t" + card.getNumber() + "\n" + "Срок действия до: \t" + card.getExpireMonth() + "/" + card.getExpireYear() + "\n" + "Баланс: \t" + card.getBalance() + "BYN" + "\n" + "Статус активности карты: \t" + "Активна"
                , "The info message is not correct");
    }


    @Test(groups = {"smoke","regression"}, dataProvider = "testWithdrawCreditData", dataProviderClass = DataProviders.class)
    public void withdrawCreditParamAtmTest(Card card, double withdrawAmount) {
        Atm atm = new Atm();
        atm.cardAccept(card);
        double currentBalance = card.getBalance();
        Assert.assertEquals(atm.withdrawMoney(card, withdrawAmount), "Баланс на карте: " + (currentBalance - withdrawAmount), "The message is not correct after withdrawing");
    }

    @Test(groups = {"smoke","regression"},dataProvider = "testWithdrawDebitData", dataProviderClass = DataProviders.class)
    public void withdrawDebitParamAtmTest(Card card, double withdrawAmount) {
        Atm atm = new Atm();
        atm.cardAccept(card);
        double currentBalance = card.getBalance();
        Assert.assertEquals(atm.withdrawMoney(card, withdrawAmount), "Баланс на карте: " + (currentBalance - withdrawAmount), "The message is not correct after withdrawing");
    }

    @Test(groups = {"regression"})
    public void withdrawInsufficientFundsDebitParamAtmTest() {
        Atm atm = new Atm();
        Card card = new DebitCard("MAKSIM TANK", 100d, 5, 2026);
        atm.cardAccept(card);
        Assert.assertEquals(atm.withdrawMoney(card, card.balance + 0.1), "Недостаточно средств на счёте. Вы можете снять не более: " + card.balance, "Message is incorrect when trying to withdraw more than balance");
    }

    @Test(groups = {"regression"})
    public void withdrawLimitDebitParamAtmTest() {
        Atm atm = new Atm();
        Card card = new DebitCard("MAKSIM TANK", 2000d, 5, 2026);
        atm.cardAccept(card);
        Assert.assertEquals(atm.withdrawMoney(card, card.limit + 1), "Превышен лимит транзакций по карте", "Message is incorrect when trying to withdraw more than the limit set");
    }

    @Test(groups = {"regression"})
    public void withdrawLimitCreditParamAtmTest() {
        Atm atm = new Atm();
        Card card = new CreditCard("YAKUB KOLAS", 400d, 12, 2025);
        atm.cardAccept(card);
        Assert.assertEquals(atm.withdrawMoney(card, card.limit + 1), "Превышен лимит транзакций по карте", "Message is incorrect when trying to withdraw more than the limit set");
    }


    @Test(groups = {"smoke","regression"}, dataProvider = "testAddMoneyCreditData", dataProviderClass = DataProviders.class)
    public void AddMoneyCreditParamAtmTest(Card card, double addAmount) {
        Atm atm = new Atm();
        atm.cardAccept(card);
        double currentBalance = card.getBalance();
        Assert.assertEquals(atm.addMoney(card, addAmount), "Баланс на карте: " + (currentBalance + addAmount), "The message is not correct after adding money");
    }

    @Test(groups = {"smoke","regression"}, dataProvider = "testAddMoneyDebitData", dataProviderClass = DataProviders.class)
    public void AddMoneyDebitParamAtmTest(Card card, double addAmount) {
        Atm atm = new Atm();
        atm.cardAccept(card);
        double currentBalance = card.getBalance();
        Assert.assertEquals(atm.addMoney(card, addAmount), "Баланс на карте: " + (currentBalance + addAmount), "The message is not correct after adding money");
    }

    @Test(groups = {"regression"})
    public void cardNotAcceptedToAddMoneyTest() {
        Card card1 = new DebitCard("MAKSIM TANK", 100d, 5, 2024);
        Atm atm = new Atm();
        atm.cardAccept(card1);
        Assert.assertEquals(atm.addMoney(card1,100d), "Карта не вставлена", "Wrong message when trying to add money to not active card");
    }


    @Test(groups = {"regression"}, dataProvider = "testConvertAtmData", dataProviderClass = DataProviders.class)
    public void convertParamAtmTest(Card card, double exchangeRate, String currencyCode) {
        Atm atm = new Atm();
        atm.cardAccept(card);
        double currentBalance = card.getBalance();
        Assert.assertEquals(atm.convert(card, exchangeRate, currencyCode), "Баланс на карте: " + currentBalance * exchangeRate + " " + currencyCode, "The converting is not correct");
    }

    @Test(groups = {"regression"})
    public void cardNotAcceptedToConvertTest() {
        Card card1 = new DebitCard("MAKSIM TANK", 100d, 5, 2024);
        Atm atm = new Atm();
        atm.cardAccept(card1);
        Assert.assertEquals(atm.convert(card1,0.32d, "USD"), "Карта не вставлена", "Wrong message when trying to convert money using not active card");
    }

    @Test(groups = {"regression"})
    public void cardWorkCompleteTest() {
        Card card1 = new DebitCard("MAKSIM TANK", 100d, 5, 2026);
        Atm atm = new Atm();
        atm.cardAccept(card1);
        Assert.assertFalse(atm.isWorkCompleted, "Work with card is not completed");
    }

    @Test(groups = {"smoke","regression"})
    public void cardIsNotReadyAfterWorkCompleteTest() {
        Card card1 = new DebitCard("MAKSIM TANK", 100d, 5, 2026);
        Atm atm = new Atm();
        atm.cardAccept(card1);
        atm.cardWorkComplete(card1);
        Assert.assertFalse(atm.cardIsReady, "After work completion, the card is still ready for work");
    }

    @Test(groups = {"regression"})
    public void workIsCompletedMessageTest() {
        Card card1 = new DebitCard("MAKSIM TANK", 100d, 5, 2026);
        Atm atm = new Atm();
        atm.cardAccept(card1);
        atm.cardWorkComplete(card1);
        Assert.assertEquals(atm.cardWorkComplete(card1), "Работа с картой завершена", "Wrong message when work with card is completed");
    }
}

