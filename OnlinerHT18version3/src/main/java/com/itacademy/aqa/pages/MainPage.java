package com.itacademy.aqa.pages;

import com.itacademy.aqa.elements.CookiesPopup;
import com.itacademy.aqa.elements.SecondaryMainPageMenu;
import com.itacademy.aqa.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;


public class MainPage extends BaseOnlinerPage{

    private SecondaryMainPageMenu secondaryMainPageMenu;
    private static final By MAIN_PAGE_LOCATOR = By.className("widget-item");
    private CookiesPopup cookiesPopup;


    public MainPage() {
        super();
        secondaryMainPageMenu = new SecondaryMainPageMenu();
        cookiesPopup = new CookiesPopup();
    }


    public SecondaryMainPageMenu getSecondaryMainPageMenu() {
        return secondaryMainPageMenu;
    }

    public CookiesPopup getCookiesPopup() {
        return cookiesPopup;
    }


 @Override
    public boolean isPageOpened() {
        try {
            return Browser.waitForElementToBeVisible(MAIN_PAGE_LOCATOR).isDisplayed();
        } catch (NotFoundException ex) {
            return false;
        }
    }


}
