package com.itacademy.aqa;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
//        int numerator;
//        int denominator;

        Fraction fraction = new Fraction(1,2);
        Fraction fraction2 = new Fraction(3,4);

        fraction.show();
        fraction2.show();

        fraction.add(fraction2);

        fraction.multiply(2.2d);

        fraction.divide(2.1d);


    }
}
