package com.itacademy.aqa.elements;

import com.itacademy.aqa.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class CookiesPopup {

    By POPUP_ACCEPT_COOKIES_BUTTON_LOCATOR = By.id("submit-button");

    public CookiesPopup(){

    }

    public void closeCookiesPopup(){
        WebElement popupAcceptCookiesButton = Browser.waitForElementToBeClickable(POPUP_ACCEPT_COOKIES_BUTTON_LOCATOR);

        popupAcceptCookiesButton.click();

        Browser.waitForElementToBeInvisible(POPUP_ACCEPT_COOKIES_BUTTON_LOCATOR);
    }
}



