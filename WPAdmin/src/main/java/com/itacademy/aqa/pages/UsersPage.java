package com.itacademy.aqa.pages;

import com.itacademy.aqa.elements.LeftMenu;

public class UsersPage {
    LeftMenu leftMenu;

    public UsersPage(){
        leftMenu = new LeftMenu();
    }

    public LeftMenu getLeftMenu() {
        return leftMenu;
    }
}
