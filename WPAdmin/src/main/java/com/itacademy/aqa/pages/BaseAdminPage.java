package com.itacademy.aqa.pages;

import com.itacademy.aqa.elements.LeftMenu;
import com.itacademy.aqa.elements.NameBar;

public abstract class BaseAdminPage  extends BaseWPPage{
    private LeftMenu leftMenu;
    private NameBar nameBar;


    protected BaseAdminPage() {
        super();
        this.leftMenu = new LeftMenu();
        this.nameBar = new NameBar();

    }

    public LeftMenu getLeftMenu() {
        return leftMenu;
    }

    public NameBar getNameBar() {
        return nameBar;
    }
}
