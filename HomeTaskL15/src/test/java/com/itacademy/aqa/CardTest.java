package com.itacademy.aqa;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({AtmTestListener.class, AnnotationTransformer.class})
public class CardTest {



    @Test(description = "1.Card that has expiration date in future ", groups = {"smoke", "regression"}, dataProvider = "testActiveCardData", dataProviderClass = DataProviders.class)
    public void cardIsActiveTest(Card card) {
        Assert.assertTrue(card.isActive(), "Card is not active, but should be active");
    }




    @Test(description = "2.For expired card the message \"Карта заблокирована\" is thrown",
            groups = {"regression"}, dataProvider = "testExpiredCardData", dataProviderClass = DataProviders.class,
            expectedExceptions = NotActiveCard.class, expectedExceptionsMessageRegExp = "Карта заблокирована")
    public void testNotActiveCardException(Card card) {
        card.isActive();

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

}

