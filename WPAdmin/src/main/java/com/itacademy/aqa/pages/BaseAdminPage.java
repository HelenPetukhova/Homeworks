package com.itacademy.aqa.pages;

import com.itacademy.aqa.elements.LeftMenu;
import com.itacademy.aqa.elements.NameBar;


/**
 * Abstract base page for admin part of WP. Has left menu and name bar
 */

public abstract class BaseAdminPage  extends BaseWPPage{
    private final LeftMenu leftMenu;
    private final NameBar nameBar;


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
