package com.itacademy.aqa.pages;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.elements.LeftMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class DashboardPage extends BaseWPPage {

    private LeftMenu leftMenu;

    private static final By DASHBOARD_HOME_PAGE_TITLE_LOCATOR = By.xpath("//h1[text()=\"Dashboard\"]");


    public DashboardPage() {
        leftMenu = new LeftMenu();
    }


    public LeftMenu getLeftMenu() {

        return leftMenu;
    }


    @Override
    public boolean isPageOpened() {
        WebElement dashboardHomePageTitle = Browser.waitForVisibilityOfElementLocatedAndFind(DASHBOARD_HOME_PAGE_TITLE_LOCATOR);
        return dashboardHomePageTitle!= null && dashboardHomePageTitle.isDisplayed();

    }
}
