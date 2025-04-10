package com.itacademy.aqa.pageObject.pages;

import com.itacademy.aqa.pageObject.elements.CookiesPopup;
import com.itacademy.aqa.pageObject.elements.SecondaryMainPageMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {

    private SecondaryMainPageMenu secondaryMainPageMenu;
    private WebDriver webDriver;
    private static final By MAIN_PAGE_LOCATOR = By.className("widget-item");
    private CookiesPopup cookiesPopup;


    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        secondaryMainPageMenu = new SecondaryMainPageMenu(webDriver);
        cookiesPopup = new CookiesPopup(webDriver);
    }


    public SecondaryMainPageMenu getSecondaryMainPageMenu() {
        return secondaryMainPageMenu;
    }

    public CookiesPopup getCookiesPopup() {
        return cookiesPopup;
    }

    public boolean isPageOpened() {
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(MAIN_PAGE_LOCATOR));
            return webDriver.findElement(MAIN_PAGE_LOCATOR).isDisplayed();
        } catch (NotFoundException ex) {
            return false;
        }
    }


}
