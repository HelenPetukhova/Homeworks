package com.itacademy.aqa.pages;

import com.itacademy.aqa.elements.LeftMenu;
import com.itacademy.aqa.elements.NameBar;
import org.apache.log4j.Logger;

public class PagesPage {
    LeftMenu leftMenu;
    private NameBar nameBar;

    private static Logger logger = Logger.getLogger(PagesPage.class);

    public PagesPage(){

        leftMenu = new LeftMenu();
        nameBar = new NameBar();

    }

    public LeftMenu getLeftMenu() {
        return leftMenu;
    }

    public NameBar getNameBar() {
        return nameBar;
    }
}

