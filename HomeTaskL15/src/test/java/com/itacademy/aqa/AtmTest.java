package com.itacademy.aqa;

import org.testng.Assert;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({AtmTestListener.class, AnnotationTransformer.class})
public class AtmTest {



    @Test(description = "1.Card that has expiration date in future ", groups = {"smoke", "regression"}, dataProvider = "testActiveCardData", dataProviderClass = DataProviders.class)
    public void cardIsActiveTest(Card card) {
        Assert.assertTrue(card.isActive(), "Card is not active, but should be active");
    }




    @Test(description = "2.For expired card the message \"Карта заблокирована\" is thrown",
            groups = {"regression"}, dataProvider = "testExpiredCardData", dataProviderClass = DataProviders.class,
            expectedExceptions = NotActiveCard.class, expectedExceptionsMessageRegExp = "Карта заблокирована")
    public void testNotActiveCardException(Card card) {
        card.isActive();
        ;
    }




    @Test(description = "3.Add money", groups = {"smoke", "regression"}, dataProvider = "testAddMoneyData", dataProviderClass = DataProviders.class)
    public void addMoneyDebitParamTest(Card card, double addingAmount, double balance) {
        card.addMoney(addingAmount);
        Assert.assertEquals(card.getBalance(), balance, "Balance is not correct after adding money");
    }




    @Test(description = "4. Withdraw money from Credit card  less than limit", groups = {"smoke", "regression"}, dataProvider = "testWithdrawCreditData", dataProviderClass = DataProviders.class)
    public void withdrawCreditParamTest(Card card, double withdrawAmount, double balance) {
        card.withdrawal(withdrawAmount);
        Assert.assertEquals(card.getBalance(), balance, "The balance is not correct after withdrawing");
    }



    @Test(description = "5. Check message thrown when withdraw money  from Credit card more than limit set",
            groups = {"regression"},
            dataProvider = "testWithdrawMoreThanLimitCreditData", dataProviderClass = DataProviders.class,
            expectedExceptions = LimitTransactionException.class,
            expectedExceptionsMessageRegExp = "Превышен лимит транзакций по карте")
    public void testLimitTransactionCreditException(Card card, double withdrawAmount) {
        card.withdrawal(withdrawAmount);
    }

    @Test(description = "6. Check that balance is not changed after withdrawing money  from Credit card more than limit set ",
            groups = {"regression"},
            dataProvider = "testWithdrawMoreThanLimitCreditData", dataProviderClass = DataProviders.class,
            expectedExceptions = LimitTransactionException.class,
            expectedExceptionsMessageRegExp = "Превышен лимит транзакций по карте")
    public void testBalanceAfterWithdrawMoreLimitCredit(Card card, double withdrawAmount) {
        double initialBalance = card.getBalance();
        try {
            card.withdrawal(withdrawAmount);
        } catch (LimitTransactionException e) {
            Assert.assertEquals(card.getBalance(), initialBalance, "The balance should not change after exceeding the limit");
            throw e; // Повторно выбрасываем исключение для удовлетворения условия expectedExceptions
        }
    }



    @Test(description = "7.Converted balance is correct", groups = {"regression"}, dataProvider = "testConvertData", dataProviderClass = DataProviders.class)
    public void convertParamTest(Card card, double exchangeRate, String currencyCode, double convertedBalance) {
        card.convert(exchangeRate, currencyCode);
        Assert.assertEquals(card.getConvertedBalance(), convertedBalance, "The converted balance is not correct");
    }


    @Test(description = "8. Withdraw money from Debit card  less than limit and less than balance",
            groups = {"smoke", "regression"}, dataProvider = "testWithdrawDebitData", dataProviderClass = DataProviders.class)
    public void withdrawDebitParamTest(Card card, double withdrawAmount, double balance) {
        card.withdrawal(withdrawAmount);
        Assert.assertEquals(card.getBalance(), balance, "The balance is not correct after withdrawing");
    }

    @Test(description = "9. Check that balance is not change after trying to withdraw money from Debit card more than limit set",
            groups = {"regression"}, expectedExceptions = LimitTransactionException.class, expectedExceptionsMessageRegExp = "Превышен лимит транзакций по карте")
    public void testBalanceAfterWithdrawMoreLimitDebit() {
        Card card1 = new DebitCard("MAKSIM TANK", 2500d, 5, 2026);
        double initialBalance = card1.getBalance();
        try {
            card1.withdrawal(card1.limit + 1);
        } catch (LimitTransactionException e) {
            Assert.assertEquals(card1.getBalance(), initialBalance, "The balance should not change after exceeding the limit");
            throw e; // Повторно выбрасываем исключение для удовлетворения условия expectedExceptions
        }
    }


    @Test(description = "10. Check message thrown when withdraw money  from Debit card more than limit set ",
            groups = {"regression"}, expectedExceptions = LimitTransactionException.class, expectedExceptionsMessageRegExp = "Превышен лимит транзакций по карте")
    public void testLimitTransactionDebitException() {
        Card card1 = new DebitCard("MAKSIM TANK", 2500d, 5, 2026);
        card1.withdrawal(card1.limit + 1);
    }

    @Test(description = "11. Withdraw money from Debit card less than limit, but greater than balance",
            groups = {"regression"}, expectedExceptions = InsufficientFundsException.class, expectedExceptionsMessageRegExp = "Недостаточно средств на счёте. Вы можете снять не более: ")
    public void testBalanceIsNotChangedAfterInsufficientFundsException() {
        Card card1 = new DebitCard("MAKSIM TANK", 100d, 5, 2026);
        double initialBalance = card1.getBalance();
        try {
            card1.withdrawal(card1.balance + 1);
        } catch (InsufficientFundsException e) {
            Assert.assertEquals(card1.getBalance(), initialBalance, "The balance should not change after attempt to withdraw more than current balance");
            throw e; // Повторно выбрасываем исключение для удовлетворения условия expectedExceptions
        }
    }

    @Test(description = "12. Check the message when withdraw money from Debit card less than limit, but greater than balance",
            groups = {"regression"}, expectedExceptions = InsufficientFundsException.class, expectedExceptionsMessageRegExp = "Недостаточно средств на счёте. Вы можете снять не более: ")
    public void testInsufficientFundsException() {
        Card card1 = new DebitCard("MAKSIM TANK", 100d, 5, 2026);
        card1.withdrawal(card1.balance + 1);
    }


    @Test(description = "13. Accept card in Atm: If card is active it is accepted for work",
            groups = {"smoke", "regression"})
    public void cardAcceptedTest() {
        Card card1 = new DebitCard("MAKSIM TANK", 100d, 5, 2026);
        Atm atm = new Atm();
        atm.cardAccept(card1);
        Assert.assertTrue(atm.cardIsReady, "Card is not ready for work");
    }


    @Test(description = "14. Accept card in Atm: If card is expired it is not accepted for work",
            groups = {"regression"})
    public void cardNotAcceptedTest() {
        Card card1 = new DebitCard("MAKSIM TANK", 100d, 5, 2024);
        Atm atm = new Atm();
        atm.cardAccept(card1);
        Assert.assertFalse(atm.cardIsReady, "Error: Not active card is accepted for work");
    }


    @Test(description = "15. Accept card in Atm: If card is expired, the work with it is completed",
            groups = {"regression"})
    public void cardNotAcceptedWorkCompletedTest() {
        Card card1 = new DebitCard("MAKSIM TANK", 100d, 5, 2024);
        Atm atm = new Atm();
        atm.cardAccept(card1);
        Assert.assertTrue(atm.isWorkCompleted, "Error: Not active card is accepted for work");
    }


    @Test(description = "16.Message when card is not active and is not accepted for work", groups = {"regression"}, expectedExceptions = CardIsNotReadyException.class, expectedExceptionsMessageRegExp = "Карта не вставлена")
    public void testNotActiveCardIsInsertedException() {
        Card card1 = new DebitCard("MAKSIM TANK", 100d, 5, 2022);
        Atm atm = new Atm();
        atm.cardAccept(card1);
        atm.getCardIsReady(card1);
    }


    @Test(description = "17. Message when card is active, but is not accepted for work",
            groups = {"regression"}, expectedExceptions = CardIsNotReadyException.class, expectedExceptionsMessageRegExp = "Карта не вставлена")
    public void testActiveCardNotInsertedException() {
        Card card1 = new DebitCard("MAKSIM TANK", 100d, 5, 2025);
        Atm atm = new Atm();
        atm.getCardIsReady(card1);
    }


    @Test(description = "18. Get Info in Atm: Correct info is display for active card", groups = {"smoke", "regression"}, dataProvider = "testInfoData", dataProviderClass = DataProviders.class)
    public void infoActiveCardParamAtmTest(Card card) {
        Atm atm = new Atm();
        atm.cardAccept(card);
        Assert.assertEquals(atm.cardInfo(card),
                "Владелец карты: \t" + card.getOwner() + "\n" + "Номер карты: \t" + card.getNumber() + "\n" + "Срок действия до: \t" + card.getExpireMonth() + "/" + card.getExpireYear() + "\n" + "Баланс: \t" + card.getBalance() + "BYN" + "\n" + "Статус активности карты: \t" + "Активна"
                , "The info message is not correct");
    }


    @Test(description = "19. Get Info in Atm: Correct info is display for expired card", groups = {"regression"})
    public void testInfoNotActiveCardException() {
        Card card = new DebitCard("MAKSIM TANK", 100d, 5, 2022);
        Atm atm = new Atm();
        atm.cardAccept(card);
        Assert.assertEquals(atm.cardInfo(card),
                "Владелец карты: \t" + card.getOwner() + "\n" + "Номер карты: \t" + card.getNumber() + "\n" + "Срок действия до: \t" + card.getExpireMonth() + "/" + card.getExpireYear() + "\n" + "Баланс: \t" + card.getBalance() + "BYN" + "\n" + "Статус активности карты: \t" + "Карта заблокирована"
                , "The info message is not correct");

    }

    @Test(description = "20. Withdraw in Atm: Correct message and final balance for Credit card",
            groups = {"smoke", "regression"}, dataProvider = "testWithdrawCreditData", dataProviderClass = DataProviders.class)
    public void withdrawCreditParamAtmTest(Card card, double withdrawAmount, double balance) {
        Atm atm = new Atm();
        atm.cardAccept(card);
        Assert.assertEquals(atm.withdrawMoney(card, withdrawAmount), "Баланс на карте: " + balance, "The message is not correct after withdrawing");
    }


    @Test(description = "21. Withdraw in Atm: Correct message and final balance for Debit card",
            groups = {"smoke", "regression"}, dataProvider = "testWithdrawDebitData", dataProviderClass = DataProviders.class)
    public void withdrawDebitParamAtmTest(Card card, double withdrawAmount, double balance) {
        Atm atm = new Atm();
        atm.cardAccept(card);
        Assert.assertEquals(atm.withdrawMoney(card, withdrawAmount), "Баланс на карте: " + balance, "The message is not correct after withdrawing");
    }


    @Test(description = "22. Withdraw in Atm: Correct message when card that is not ready for work",
            groups = {"regression"})
    public void testWithdrawFromNotReadyCard() {
        Card card = new DebitCard("MAKSIM TANK", 100d, 5, 2026);
        Atm atm = new Atm();
        Assert.assertEquals(atm.withdrawMoney(card, 50d), "Карта не вставлена", "The message is not correct after withdrawing");
    }

    @Test(description = "23. Withdraw in Atm: Correct message when trying to withdraw greater than limit for Debit card", groups = {"regression"})
    public void withdrawMoreLimitDebitAtmTest() {
        Atm atm = new Atm();
        Card card = new DebitCard("MAKSIM TANK", 2000d, 5, 2026);
        atm.cardAccept(card);
        Assert.assertEquals(atm.withdrawMoney(card, card.limit + 1), "Превышен лимит транзакций по карте", "Message is incorrect when trying to withdraw more than the limit set");
    }

    @Test(description = "23. Withdraw in Atm: Correct message when trying to withdraw greater than limit for Credit card", groups = {"regression"})
    public void withdrawMoreLimitCreditAtmTest() {
        Atm atm = new Atm();
        Card card = new CreditCard("YAKUB KOLAS", 400d, 12, 2025);
        atm.cardAccept(card);
        Assert.assertEquals(atm.withdrawMoney(card, card.limit + 1), "Превышен лимит транзакций по карте", "Message is incorrect when trying to withdraw more than the limit set");
    }


    @Test(description = "24. Withdraw in Atm: Correct message when trying to withdraw greater than balance from Debit card", groups = {"regression"})
    public void withdrawInsufficientFundsDebitParamAtmTest() {
        Atm atm = new Atm();
        Card card = new DebitCard("MAKSIM TANK", 100d, 5, 2026);
        atm.cardAccept(card);
        Assert.assertEquals(atm.withdrawMoney(card, card.balance + 0.1), "Недостаточно средств на счёте. Вы можете снять не более: " + card.balance, "Message is incorrect when trying to withdraw more than balance");
    }



    @Test(description = "25. Adding money in Atm: Correct message and balance for card that is ready for work", groups = {"smoke", "regression"}, dataProvider = "testAddMoneyData", dataProviderClass = DataProviders.class)
    public void AddMoneyParamAtmTest(Card card, double addAmount, double balance) {
        Atm atm = new Atm();
        atm.cardAccept(card);
        Assert.assertEquals(atm.addMoney(card, addAmount), "Баланс на карте: " + balance, "The message is not correct after adding money");
    }

//    @Test(groups = {"smoke", "regression"}, dataProvider = "testAddMoneyDebitData", dataProviderClass = DataProviders.class)
//    public void AddMoneyDebitParamAtmTest(Card card, double addAmount) {
//        Atm atm = new Atm();
//        atm.cardAccept(card);
//        double currentBalance = card.getBalance();
//        Assert.assertEquals(atm.addMoney(card, addAmount), "Баланс на карте: " + (currentBalance + addAmount), "The message is not correct after adding money");
//    }

    @Test(description = "26. Adding money in Atm: Correct message when card that is not ready for work", groups = {"regression"})
    public void cardNotAcceptedToAddMoneyTest() {
        Card card1 = new DebitCard("MAKSIM TANK", 100d, 5, 2024);
        Atm atm = new Atm();
        atm.cardAccept(card1);
        Assert.assertEquals(atm.addMoney(card1, 100d), "Карта не вставлена", "Wrong message when trying to add money to not active card");
    }


    @Test(description = "27.Convert money in Atm: Correct message and balance for card that is ready for work", groups = {"regression"}, dataProvider = "testConvertData", dataProviderClass = DataProviders.class)
    public void convertParamAtmTest(Card card, double exchangeRate, String currencyCode, double convertedBalance) {
        Atm atm = new Atm();
        atm.cardAccept(card);
        Assert.assertEquals(atm.convert(card, exchangeRate, currencyCode), "Баланс на карте: " + convertedBalance + " " + currencyCode, "The converting is not correct");
    }

    @Test(description = "28. Convert money in Atm: Correct message when card that is not ready for work", groups = {"regression"})
    public void cardNotAcceptedToConvertTest() {
        Card card1 = new DebitCard("MAKSIM TANK", 100d, 5, 2024);
        Atm atm = new Atm();
        atm.cardAccept(card1);
        Assert.assertEquals(atm.convert(card1, 0.32d, "USD"), "Карта не вставлена", "Wrong message when trying to convert money using not active card");
    }

    @Test(description = "29. Complete work in Atm: card is not ready for work after completion", groups = {"regression"})
    public void cardWorkCompleteTest() {
        Card card1 = new DebitCard("MAKSIM TANK", 100d, 5, 2026);
        Atm atm = new Atm();
        atm.cardAccept(card1);
        Assert.assertFalse(atm.isWorkCompleted, "Work with card is not completed");
    }

    @Test(description = "30. Work completed in Atm: Check isWorkComplete marker", groups = {"smoke", "regression"})
    public void cardIsNotReadyAfterWorkCompleteTest() {
        Card card1 = new DebitCard("MAKSIM TANK", 100d, 5, 2026);
        Atm atm = new Atm();
        atm.cardAccept(card1);
        atm.cardWorkComplete(card1);
        Assert.assertFalse(atm.cardIsReady, "After work completion, the card is still ready for work");
    }

    @Test(description = "31. Work completed in Atm: check message", groups = {"regression"})
    public void workIsCompletedMessageTest() {
        Card card1 = new DebitCard("MAKSIM TANK", 100d, 5, 2026);
        Atm atm = new Atm();
        atm.cardAccept(card1);
        atm.cardWorkComplete(card1);
        Assert.assertEquals(atm.cardWorkComplete(card1), "Работа с картой завершена", "Wrong message when work with card is completed");
    }
}

