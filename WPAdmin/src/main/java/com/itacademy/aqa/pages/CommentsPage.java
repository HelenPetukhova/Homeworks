package com.itacademy.aqa.pages;

import com.itacademy.aqa.elements.LeftMenu;

public class CommentsPage {

    private LeftMenu leftMenu;

    public CommentsPage(){
        leftMenu = new LeftMenu();
    }

    public LeftMenu getLeftMenu() {
        return leftMenu;
    }
}
