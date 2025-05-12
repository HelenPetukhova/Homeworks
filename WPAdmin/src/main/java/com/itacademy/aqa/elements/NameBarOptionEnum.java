package com.itacademy.aqa.elements;

public enum NameBarOptionEnum {
    EDIT_PROFILE("wp-admin-bar-user-info"),
    LOG_OUT("wp-admin-bar-logout");

    private String value;

    NameBarOptionEnum(String value){
        this.value = value;

    }

    public String getValue(){
        return value;
    }
}
