package com.itacademy.aqa;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({AtmTestListener.class, AnnotationTransformer.class})
public class AtmAcceptInfoWithdrawTest {


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

}

