package com.itacademy.aqa.elements;

public enum ActionsEnum {
    EDIT("edit"),
    QUICK_EDIT("inline"),
    TRASH("trash"),
    VIEW("view"),
    PURGE_FROM_CACHE("w3tc_flush_post"),
    RESTORE("untrash"),
    DELETE_PERMANENTLY("delete");




    private String value;

    ActionsEnum(String value){
        this.value = value;

    }


    public String getValue(){
        return value;
    }

}
