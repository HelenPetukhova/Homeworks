package com.itacademy.aqa.pages;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.elements.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class PagesPage extends BaseWPPage {
    private LeftMenu leftMenu;
    private NameBar nameBar;
    private BulkActionsDdl bulkActionsDdl;
    private StatusFilterMenu statusFilterMenu;

    private static final By PAGES_PAGE_TITLE_LOCATOR = By.xpath("//h1[contains(text(),'Pages')]");
    private static final By ADD_NEW_PAGE_BUTTON_LOCATOR = By.className("page-title-action");
    private static final By ALL_PAGES_TITLES_LOCATOR = By.xpath("//tr/td[contains(@class, 'page-title')]");
    private static final String CHECK_BOX_TO_SELECT_PAGE_PATTERN_LOCATOR = "//*[contains(text(),'%s')]/../../input";
    private static final By APPLY_BUTTON_LOCATOR = By.id("doaction");
    private static final String PAGE_TITLE_LINK_TEMPLATE_LOCATOR = "//td//a[contains(text(),'%s')]";


    private static Logger logger = Logger.getLogger(PagesPage.class);

    public PagesPage() {

        leftMenu = new LeftMenu();
        nameBar = new NameBar();
        bulkActionsDdl = new BulkActionsDdl();
        statusFilterMenu = new StatusFilterMenu();

    }


    public LeftMenu getLeftMenu() {
        return leftMenu;
    }


    public NameBar getNameBar() {
        return nameBar;
    }


    @Override
    public boolean isPageOpened() {
        try {
            WebElement pagesPageTitle = Browser.waitForVisibilityOfElementLocatedAndFind(PAGES_PAGE_TITLE_LOCATOR);
            return pagesPageTitle.isDisplayed();
        } catch (TimeoutException ex) {
            logger.error("Dashboard page was not opened during expected time", ex);
            return false;
        }
    }

    public void addNewPageButtonClick() {

        WebElement addNewPageButton = Browser.waitForElementToBeClickableAndFind(ADD_NEW_PAGE_BUTTON_LOCATOR);
        addNewPageButton.click();
    }


    public List<String> getAllPagesTitles() {


        List<WebElement> allPagesTitlesElements = Browser.waitForPresenceOfAllElementsAndFind(ALL_PAGES_TITLES_LOCATOR);
        List<String> allPagesTitles = new ArrayList<>();

        for (WebElement pageTitleElement : allPagesTitlesElements) {
            String postTitle = pageTitleElement.getText();
            allPagesTitles.add(postTitle);
        }

        return allPagesTitles;
    }

    public void checkOffPost(String pageTitle) {
        String xPath = String.format(CHECK_BOX_TO_SELECT_PAGE_PATTERN_LOCATOR, pageTitle);
        By CHECK_BOX_TO_SELECT_PAGE_LOCATOR = By.xpath(xPath);
        WebElement checkBoxToSelectPage = Browser.waitForElementToBeClickableAndFind(CHECK_BOX_TO_SELECT_PAGE_LOCATOR);
        checkBoxToSelectPage.click();

    }


    public void clickApplyButton() {
        WebElement applyButton = Browser.waitForElementToBeClickableAndFind(APPLY_BUTTON_LOCATOR);
        applyButton.click();
    }


    public void deletePage(String postTitle) {
        checkOffPost(postTitle);
        bulkActionsDdl.selectBulkAction(BulkActionsEnum.MOVE_TO_TRASH);
        clickApplyButton();
        statusFilterMenu.clickOnItem(StatusFilterMenuEnum.TRASH);
        checkOffPost(postTitle);
        bulkActionsDdl.selectBulkAction(BulkActionsEnum.DELETE_PERMANENTLY);
        clickApplyButton();
    }

    public void clickPageTitle(String pageTitle) {
        String xPath = String.format(PAGE_TITLE_LINK_TEMPLATE_LOCATOR, pageTitle);
        By PAGE_TITLE_LINK_LOCATOR = By.xpath(xPath);
        WebElement postTitleLink = Browser.waitForElementToBeClickableAndFind(PAGE_TITLE_LINK_LOCATOR);
        postTitleLink.click();
    }
}

