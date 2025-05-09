package com.itacademy.aqa.pages;

import com.itacademy.aqa.elements.LeftMenu;

public class PagesPage {
    LeftMenu leftMenu;

    public PagesPage(){

        leftMenu = new LeftMenu();
    }

    public LeftMenu getLeftMenu() {
        return leftMenu;
    }
}
