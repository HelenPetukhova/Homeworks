package com.itacademy.aqa;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Random random = new Random();

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("in1.txt"))) {
            for (int i = 0; i < 1000; i++) {
                int n1 = random.nextInt(10000) + 1;
                String stringN1 = String.valueOf(n1);
                bufferedWriter.write(stringN1);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("in2.txt"))) {
            for (int i = 0; i < 1000; i++) {
                int n1 = random.nextInt(10000) + 1;
                String stringN1 = String.valueOf(n1);
                bufferedWriter.write(stringN1);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("in1.txt"))) {

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Please create a file first");
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("in2.txt"))) {

                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    list.add(line);
                    System.out.println(line);

                }
        } catch (FileNotFoundException e) {
            System.out.println("Please create a file first");
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Integer> listInt = new ArrayList<>();
        for(String stringNumber : list){
            listInt.add(Integer.valueOf(stringNumber));

        }
        Collections.sort(listInt);
        System.out.println(listInt);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("out.txt"))) {
            for (int intNum : listInt){
                bufferedWriter.write(String.valueOf(intNum));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
