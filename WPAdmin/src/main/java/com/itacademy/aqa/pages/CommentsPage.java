package com.itacademy.aqa.pages;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.elements.LeftMenu;
import com.itacademy.aqa.elements.NameBar;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CommentsPage {
    private static Logger logger = Logger.getLogger(CommentsPage.class);

    private LeftMenu leftMenu;
    private NameBar nameBar;

    private static final By COMMENTS_PAGE_TITLE_LOCATOR = By.xpath("//*[@class ='wp-heading-inline' ][contains(text(),'Comments')]");

    public CommentsPage() {
        leftMenu = new LeftMenu();
        nameBar = new NameBar();
    }

    public LeftMenu getLeftMenu() {
        return leftMenu;
    }

    public NameBar getNameBar() {
        return nameBar;
    }

    public boolean isPageOpened() {
        try {
            WebElement commentsPageTitle = Browser.waitForVisibilityOfElementLocatedAndFind(COMMENTS_PAGE_TITLE_LOCATOR);
            return commentsPageTitle.isDisplayed();
        } catch (RuntimeException ex){
            return false;
        }
    }

    public String takeRowColor(){
        By ROW_WITH_COLOR_LOCATOR = By.xpath("//tbody//p[contains(text(),'Comment Test12')]/..");
        WebElement rowWithColor = Browser.waitForElementToBeClickableAndFind(ROW_WITH_COLOR_LOCATOR);
        return rowWithColor.getCssValue("background-color");
    }

}
