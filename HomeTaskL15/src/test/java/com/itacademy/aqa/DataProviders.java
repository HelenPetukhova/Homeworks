package com.itacademy.aqa;

import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider(name = "testAddMoneyCreditData")
    public static Object[][] dataAddMoneyCreditProvider() {
        return new Object[][]{
                {new CreditCard("YAKUB KOLAS", 0d, 12, 2025), 1400.52d},
                {new CreditCard("MAKSIM BAHDANOVICH", -100d, 10, 2026), 10d},
                {new CreditCard("NIL GILEVICH", 100.3d, 10, 2025), 0d}
        };
    }

    @DataProvider(name = "testAddMoneyDebitData")
    public static Object[][] dataAddMoneyDebitProvider() {
        return new Object[][]{
                {new DebitCard("YANKA BRYL", 0d, 12, 2025), 111.01d},
                {new DebitCard("MAKSIM TANK", 1000d, 10, 2026), 10d},
                {new DebitCard("ZMITROK BIADULIA", 202.3d, 10, 2025), 0d}
        };
    }

    @DataProvider(name = "testConvertData")
    public static Object[][] dataConvertProvider() {
        return new Object[][]{
                {new CreditCard("YAKUB KOLAS", 0d, 12, 2025), 0.3d},
                {new CreditCard("MAKSIM BAHDANOVICH", -100d, 10, 2026), 2.0d},
                {new DebitCard("NIL GILEVICH", 100.3d, 10, 2025), 0.25d}
        };
    }

    @DataProvider(name = "testWithdrawCreditData")
    public static Object[][] dataWithdrawCreditProvider() {
        Card card = new Card();
        return new Object[][]{
                {new CreditCard("YAKUB KOLAS", 0d, 12, 2025), 400.52d},
                {new CreditCard("MAKSIM BAHDANOVICH", -100d, 10, 2026), card.limit},
                {new CreditCard("NIL GILEVICH", 100.3d, 10, 2025), 0d},
                {new CreditCard("PIATRUS BROUKA", 500d, 11, 2026), card.limit - 0.1}
        };
    }

    @DataProvider(name = "testWithdrawDebitData")
    public static Object[][] dataWithdrawDebitProvider() {
        Card card = new Card();
        return new Object[][]{
                {new DebitCard("YANKA BRYL", 200d, 12, 2025), 200d}, //withdraw = balance
                {new DebitCard("MAKSIM TANK", 150.2d, 10, 2026), 150.1d}, //withdraw = balance-0.1
                {new DebitCard("ZMITROK BIADULIA", 2000.3d, 10, 2025), card.limit}, // withdraw = limit
                {new DebitCard("NIL GILEVICH", 1000.3d, 10, 2025), card.limit - 0.1}
        };
    }


    @DataProvider(name = "testInfoData")
    public static Object[][] dataInfoCreditProvider() {

        return new Object[][]{
                {new CreditCard("YAKUB KOLAS", 0d, 12, 2030)},
                {new CreditCard("MAKSIM BAHDANOVICH", -100d, 10, 2026)},
                {new CreditCard("NIL GILEVICH", 10078.325d, 2, 2026)},
                {new DebitCard("MAKSIM TANK", 0d, 10, 2026)},
                {new DebitCard("ZMITROK BIADULIA", 2000.31d, 10, 2025)},
                {new DebitCard("NIL GILEVICH", 2.23d, 1, 2031)}
        };
    }

    @DataProvider(name = "testConvertAtmData")
    public static Object[][] dataConvertAtmProvider() {
        return new Object[][]{
                {new CreditCard("YAKUB KOLAS", 0d, 12, 2025), 0.32d, "USD"},
                {new CreditCard("MAKSIM BAHDANOVICH", -100d, 10, 2026), 27.6d, "RUB"},
                {new DebitCard("NIL GILEVICH", 1008.3d, 10, 2025), 0.2944d, "EUR"}
        };
    }
}
