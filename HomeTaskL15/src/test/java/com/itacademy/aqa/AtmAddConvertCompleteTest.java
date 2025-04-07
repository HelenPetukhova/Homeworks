package com.itacademy.aqa;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({AtmTestListener.class, AnnotationTransformer.class})
public class AtmAddConvertCompleteTest {


    @Test(description = "25. Adding money in Atm: Correct message and balance for card that is ready for work", groups = {"smoke", "regression"}, dataProvider = "testAddMoneyData", dataProviderClass = DataProviders.class)
    public void AddMoneyParamAtmTest(Card card, double addAmount, double balance) {
        Atm atm = new Atm();
        atm.cardAccept(card);
        Assert.assertEquals(atm.addMoney(card, addAmount), "Баланс на карте: " + balance, "The message is not correct after adding money");
    }


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

