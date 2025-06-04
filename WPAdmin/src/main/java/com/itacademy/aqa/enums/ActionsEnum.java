package com.itacademy.aqa.enums;

public enum ActionsEnum {
    EDIT("edit"),
    QUICK_EDIT_POST_PAGE("inline"),
    TRASH("trash"),
    VIEW("view"),
    PURGE_FROM_CACHE("w3tc_flush_post"),
    RESTORE("untrash"),
    DELETE_PERMANENTLY("delete"),
    APPROVE("approve"),
    UNAPPROVE("unapprove"),
    REPLY("reply"),
    QUICK_EDIT_COMMENT("quickedit"),
    SPAM("spam"),
    DELETE("delete"),
    SEND_PASSWORD_RESET("resetpassword");





    private String value;

    ActionsEnum(String value){
        this.value = value;

    }


    public String getValue(){
        return value;
    }

}
