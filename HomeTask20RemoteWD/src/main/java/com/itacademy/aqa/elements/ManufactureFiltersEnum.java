package com.itacademy.aqa.elements;

public enum ManufactureFiltersEnum {

    LG("LG"),
    XIAOMI("Xiaomi"),
    TCL("TCL"),
    HISENSE("Hisense"),
    YANDEX("Яндекс");


    private String value;

    ManufactureFiltersEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}

