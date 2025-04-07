package com.itacademy.aqa;


public class Fraction {
    public int numerator;
    public int denominator;
    String strNumber;

    public Fraction() {
    }



    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public void show() {
        System.out.println(numerator + "/" + denominator);
    }


    public String add(Fraction fraction) {
        this.numerator = this.numerator * fraction.denominator + fraction.numerator * this.denominator;
        this.denominator = this.denominator * fraction.denominator;
        this.simplify();
        return strNumber;
    }

    public String simplify() {
        int minnumden = 1;


        if (this.numerator < this.denominator) {
            minnumden = this.numerator;
        } else if (this.numerator > this.denominator) {
            minnumden = this.denominator;
        }

        for (int i = minnumden; i >= 2; i--) {
            if (this.numerator % i == 0 && this.denominator % i == 0) {
                this.numerator /= i;
                this.denominator /= i;
            }
        }
        if (this.numerator == this.denominator) {
            return strNumber = "1";
        } else if (this.denominator == 1) {
            return strNumber = "" + this.numerator;
        } else {
            return strNumber = this.numerator + "/" + this.denominator;
        }
    }


    public String multiply(double number) {

        int counter = 0;
        while (number % 1 != 0) {
            number *= 10;
            counter++;
        }
        int numNumerator = (int) (number);
        int numDenominator = (int) Math.pow(10, counter);

        this.numerator = this.numerator * numNumerator;
        this.denominator = this.denominator * numDenominator;

        this.simplify();
        return strNumber;
//        System.out.println(number);
//        System.out.println(this.numerator);
//        System.out.println(this.denominator);

    }

    public String divide(double number) {

        int counter = 0;
        while (number % 1 != 0) {
            number *= 10;
            counter++;
        }
        int numNumerator = (int) (number);
        int numDenominator = (int) Math.pow(10, counter);

        this.numerator = this.numerator * numDenominator;
        this.denominator = this.denominator * numNumerator;

        this.simplify();
        return strNumber;
//        System.out.println(number);
//        System.out.println(this.numerator);
//        System.out.println(this.denominator);

    }

}



