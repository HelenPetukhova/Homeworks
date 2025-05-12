package com.itacademy.aqa.pages;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.elements.LeftMenu;
import com.itacademy.aqa.elements.NameBar;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class DashboardPage extends BaseWPPage {
    private static Logger logger = Logger.getLogger(DashboardPage.class);

    private LeftMenu leftMenu;
    private NameBar nameBar;

    private static final By DASHBOARD_HOME_PAGE_TITLE_LOCATOR = By.xpath("//h1[text()=\"Dashboard\"]");


    public DashboardPage() {
        leftMenu = new LeftMenu();
        nameBar = new NameBar();

    }


    public LeftMenu getLeftMenu() {

        return leftMenu;
    }

    public NameBar getNameBar(){
        return nameBar;
    }

    @Override
    public boolean isPageOpened() {
        WebElement dashboardHomePageTitle = Browser.waitForVisibilityOfElementLocatedAndFind(DASHBOARD_HOME_PAGE_TITLE_LOCATOR);
        return dashboardHomePageTitle!= null && dashboardHomePageTitle.isDisplayed();

    }
}
