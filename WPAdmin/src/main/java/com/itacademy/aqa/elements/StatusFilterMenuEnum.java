package com.itacademy.aqa.elements;

public enum StatusFilterMenuEnum {
    ALL("all"),
    MINE("mine"),
    PENDING("moderated"),
    PRIVATE("private"),
    SCHEDULED("future"),
    PUBLISHED("publish"),
    DRAFT("draft"),
    TRASH("trash"),
    APPROVED("approved"),
    SPAM("spam");


    private String value;

    StatusFilterMenuEnum(String value){
        this.value = value;

    }

    public String getValue(){
        return value;
    }
}
