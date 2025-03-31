package com.itacademy.aqa.pageObject.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SecondaryMainPageMenu {
    WebDriver webDriver;

    public SecondaryMainPageMenu(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    private static final String ITEM_PATTERN = "//*[@class='project-navigation__sign'][.='%s']";

    public void clickOnItem(SecondaryMainPageMenuEnum secondaryMainPageMenuEnum) {

        String xPath = String.format(ITEM_PATTERN, secondaryMainPageMenuEnum.getValue());
        By secondaryMenuItemLocator = By.xpath(xPath);

        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(secondaryMenuItemLocator));

        WebElement secondaryMenuItem = webDriver.findElement(secondaryMenuItemLocator);  //  переменная - веб-элемент, найденый по локатору - Телевизоры линк
        secondaryMenuItem.click();
    }
}
