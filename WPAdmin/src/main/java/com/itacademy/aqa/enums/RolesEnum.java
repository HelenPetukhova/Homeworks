package com.itacademy.aqa.enums;

public enum RolesEnum {

    ADMINISTRATOR("administrator"),
    EDITOR("editor"),
    AUTHOR("author"),
    CONTRIBUTOR("contributor"),
    SUBSCRIBER("subscriber");


    private String value;

    RolesEnum(String value){
        this.value = value;

    }


    public String getValue(){
        return value;
    }
}
