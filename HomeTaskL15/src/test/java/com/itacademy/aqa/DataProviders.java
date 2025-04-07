package com.itacademy.aqa;

import org.testng.annotations.DataProvider;

import java.time.LocalDate;


public class DataProviders {
    static LocalDate currentDate = LocalDate.now();
    static LocalDate previousMonth = currentDate.plusMonths(1);


    @DataProvider(name = "testActiveCardData")
    public static Object[][] dataActiveCardProvider() {
        return new Object[][]{
                {new CreditCard("YAKUB KOLAS", -100d, currentDate.getMonthValue(), currentDate.getYear())},
                {new DebitCard("YANKA BRYL", 0d, 12, currentDate.getYear())},
                {new CreditCard("NIL GILEVICH", 100.3d, currentDate.getMonthValue() - 1, currentDate.getYear() + 1)},
                {new DebitCard("MAKSIM BAHDANOVICH", 2500.6d, 1, 2101)}
        };
    }

    @DataProvider(name = "testExpiredCardData")
    public static Object[][] dataExpiredCardProvider() {
        return new Object[][]{
                {new CreditCard("YAKUB KOLAS", -100d, currentDate.getMonthValue()-1, previousMonth.getYear())},
                {new DebitCard("YANKA BRYL", 540d, 12, currentDate.getYear()-1)},
                {new CreditCard("NIL GILEVICH", 0d, 1, 1999)}
        };
    }

//    @DataProvider(name = "testAddMoneyCreditData")
//    public static Object[][] dataAddMoneyCreditProvider() {
//        return new Object[][]{
//                {new CreditCard("YAKUB KOLAS", 0d, 12, 2025), 1400.52d},
//                {new CreditCard("MAKSIM BAHDANOVICH", -100d, 10, 2026), 10d},
//                {new CreditCard("NIL GILEVICH", 100.3d, 10, 2025), 0d}
//        };
//    }
//
//    @DataProvider(name = "testAddMoneyDebitData")
//    public static Object[][] dataAddMoneyDebitProvider() {
//        return new Object[][]{
//                {new DebitCard("YANKA BRYL", 0d, 12, 2025), 111.01d},
//                {new DebitCard("MAKSIM TANK", 1000d, 10, 2026), 10d},
//                {new DebitCard("ZMITROK BIADULIA", 202.3d, 10, 2025), 0d}
//        };
//    }

    @DataProvider(name = "testAddMoneyData")
    public static Object[][] dataAddMoneyProvider() {
        return new Object[][]{
                {new CreditCard("MAKSIM BAHDANOVICH", -500.6d, 10, 2026), 100.6d, -400d},
                {new CreditCard("NIL GILEVICH", -100.3d, 10, 2025), 300d, 199.7d},
                {new DebitCard("YANKA BRYL", 0d, 12, 2025), 111.01d, 111.01d},
                {new DebitCard("ZMITROK BIADULIA", 202.3d, 5, 2027), 0d, 202.3d}
        };
    }


    @DataProvider(name = "testWithdrawCreditData")
    public static Object[][] dataWithdrawCreditProvider() {
        Card card = new Card();
        return new Object[][]{
                {new CreditCard("YAKUB KOLAS", 0d, 12, 2025), 400.52d, -400.52d},
                {new CreditCard("MAKSIM BAHDANOVICH", -100d, 10, 2026), card.limit, -100-card.limit},
                {new CreditCard("NIL GILEVICH", -100.3d, 10, 2025), 0d, -100.3d},
                {new CreditCard("PIATRUS BROUKA", 500d, 11, 2026), card.limit - 0.1, 500-(card.limit - 0.1)}
        };
    }

    @DataProvider(name = "testWithdrawMoreThanLimitCreditData")
    public static Object[][] dataWithdrawMoreThanLimitCreditProvider() {
        Card card = new Card();
        return new Object[][]{
                {new CreditCard("YAKUB KOLAS", 3700d, 12, 2025), card.limit+0.1},
                {new CreditCard("MAKSIM BAHDANOVICH", -100.3d, 10, 2026), card.limit+20.5d},
                {new CreditCard("PIATRUS BROUKA", 20d, 11, 2026), card.limit+50d}
        };
    }


    @DataProvider(name = "testConvertData")
    public static Object[][] dataConvertProvider() {
        return new Object[][]{
                {new DebitCard("YAKUB KOLAS", 100d, 12, 2025), 0.32d, "USD", 32d},
                {new CreditCard("MAKSIM BAHDANOVICH", -100d, 10, 2026), 27.6d, "RUB", -2760d},
                {new DebitCard("NIL GILEVICH", 0d, 10, 2025), 0.2944d, "EUR", 0d}
        };
    }


    @DataProvider(name = "testWithdrawDebitData")
    public static Object[][] dataWithdrawDebitProvider() {
        Card card = new Card();
        return new Object[][]{
                {new DebitCard("YANKA BRYL", 200d, 12, 2025), 200d, 0d}, //withdraw = balance
                {new DebitCard("MAKSIM TANK", 150d, 10, 2026), 149d, 1d}, //withdraw = balance-1
                {new DebitCard("ZMITROK BIADULIA", 2000.3d, 10, 2025), card.limit, 2000.3d-card.limit}, // withdraw = limit
                {new DebitCard("NIL GILEVICH", 1000.3d, 10, 2025), 0d, 1000.3d}//withdraw =0
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
