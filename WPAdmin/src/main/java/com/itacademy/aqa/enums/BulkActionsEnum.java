package com.itacademy.aqa.enums;

public enum BulkActionsEnum {
    BULK_ACTIONS("-1"),
    EDIT("edit"),
    MOVE_TO_TRASH("trash"),
    UNAPPROVE("unapprove"),
    APPROVE("approve"),
    MARK_AS_SPAM("spam"),
    DELETE("delete"),
    SEND_PASSWORD_RESET("resetpassword"),
    RESTORE("untrash"),
    DELETE_PERMANENTLY("delete");

    private String value;

    BulkActionsEnum(String value){
        this.value = value;

    }

    public String getValue(){
        return value;
    }
}
