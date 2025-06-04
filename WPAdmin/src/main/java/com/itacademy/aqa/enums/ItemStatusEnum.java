package com.itacademy.aqa.enums;

public enum ItemStatusEnum {
    DRAFT("Draft"),
    PENDING("Pending"),
    PRIVATE("Private"),
    SCHEDULED("Scheduled"),
    PUBLISHED("Published");


    private String value;

    ItemStatusEnum(String value){
        this.value = value;

    }

    public String getValue(){
        return value;
    }

    }
