package com.itacademy.aqa.elements;

public enum LeftMenuEnum {
    DASHBOARD("menu-dashboard"),
    POSTS("menu-posts"),
    MEDIA("menu-media"),
    PAGES("menu-pages"),
    COMMENTS("menu-comments"),
    APPEARANCE("menu-appearance"),
    PLUGINS("menu-plugins"),
    USERS("menu-users"),
    TOOLS("menu-tools"),
    SETTINGS("menu-settings"),
    PERFORMANCE("toplevel_page_w3tc_dashboard"),
    SMUSH("toplevel_page_smush"),
    COLLAPSE_MENU("collapse-menu");

    private String value;

    LeftMenuEnum(String value){
        this.value = value;

    }

    public String getValue(){
        return value;
    }

}
