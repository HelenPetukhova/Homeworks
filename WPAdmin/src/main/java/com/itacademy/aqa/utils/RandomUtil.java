package com.itacademy.aqa.utils;

import com.itacademy.aqa.enums.RolesEnum;

import java.util.Random;

public class RandomUtil {

    public static String generateRandomString(int stringLength){
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();

        for(int i=0; i<stringLength; i++){
            stringBuilder.append(chars.charAt(random.nextInt(chars.length())));
        }
        return stringBuilder.toString();
    }

    public static String generateEmail(int stringLength){
        return "kl_" + generateRandomString(stringLength) + "@test.com";
    }

    public static String generateLettersString(int stringLength){
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();

        for(int i=0; i<stringLength; i++){
            stringBuilder.append(chars.charAt(random.nextInt(chars.length())));
        }
        return stringBuilder.toString();
    }


    public static RolesEnum generateRole() {
        RolesEnum[] roles = RolesEnum.values();
        Random random = new Random();
        return roles[random.nextInt(roles.length)];
    }



}
