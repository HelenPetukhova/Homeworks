package com.itacademy.aqa.pageObject.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CookiesPopup {
    WebDriver webDriver;
    By POPUP_ACCEPT_COOKIES_BUTTON_LOCATOR = By.id("submit-button");

    public CookiesPopup(WebDriver webDriver){
        this.webDriver = webDriver;
    }

    public void closeCookiesPopup(){

        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(POPUP_ACCEPT_COOKIES_BUTTON_LOCATOR));
        WebElement popupAcceptCookiesButton = webDriver.findElement(POPUP_ACCEPT_COOKIES_BUTTON_LOCATOR);
        popupAcceptCookiesButton.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(POPUP_ACCEPT_COOKIES_BUTTON_LOCATOR));
    }
}



