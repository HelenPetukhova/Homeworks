package com.itacademy.aqa.pages;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.elements.LeftMenu;
import com.itacademy.aqa.elements.NameBar;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;


public class DashboardPage extends BaseAdminPage {
    private static Logger logger = Logger.getLogger(DashboardPage.class);

    private static final By DASHBOARD_HOME_PAGE_TITLE_LOCATOR = By.xpath("//h1[text()='Dashboard']");   // если без h1 : //*[@class='wrap']//*[(text()='Dashboard')]


    public DashboardPage() {
        super();
    }


    @Override
    public boolean isPageOpened() {
        try {
            WebElement dashboardHomePageTitle = Browser.waitForVisibilityOfElementLocatedAndFind(DASHBOARD_HOME_PAGE_TITLE_LOCATOR);
            return dashboardHomePageTitle.isDisplayed();
        } catch (TimeoutException ex) {
            logger.error("Dashboard page was not opened during expected time", ex);
            return false;
        }
    }
}
