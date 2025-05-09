package com.itacademy.aqa.elements;

import com.itacademy.aqa.config.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class LeftMenu {

    private static final By LEFT_MENU_ITEMS_LOCATOR = By.xpath("//*[@id=\"adminmenu\"]/li//div[@class=\"wp-menu-name\"]");

    public LeftMenu() {
    }


    public void clickOnItem(LeftMenuEnum leftMenuEnum) {

        By LEFT_MENU_LOCATOR = By.id(leftMenuEnum.getValue());

        WebElement leftMenu = Browser.waitForElementToBeClickableAndFind(LEFT_MENU_LOCATOR);

        leftMenu.click();

    }

    public List<String> getLeftMenuItemsTitles() {


        List<WebElement> menuItemsWebElements = Browser.waitForPresenceOfAllElementsAndFind(LEFT_MENU_ITEMS_LOCATOR);
        List<String> menuItemsTitles = new ArrayList<>();

        for (WebElement menuItemsWebElement : menuItemsWebElements){
            String fullElementText = menuItemsWebElement.getText().trim();
            String onlyMenuItemsText = fullElementText.replaceAll("\\d|\\n|\\s", "");
            menuItemsTitles.add(onlyMenuItemsText);
        }

        return menuItemsTitles;
    }






}
