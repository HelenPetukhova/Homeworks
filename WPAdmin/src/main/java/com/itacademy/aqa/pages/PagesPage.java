package com.itacademy.aqa.pages;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.elements.*;
import com.itacademy.aqa.enums.BulkActionsEnum;
import com.itacademy.aqa.enums.StatusFilterMenuEnum;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class PagesPage extends BaseAdminPage {

    private final BulkActionsDdl bulkActionsDdl;
    private final StatusFilterMenu statusFilterMenu;
    private final PostsPagesActionsRow postsPagesActionsRow;
    private final WordPressOnAzureDdl wordPressOnAzureDdl;
    private static final By PAGES_PAGE_TITLE_LOCATOR = By.xpath("//h1[contains(text(),'Pages')]");
    private static final By ADD_NEW_PAGE_BUTTON_LOCATOR = By.className("page-title-action");
    private static final By ALL_PAGES_TITLES_LOCATOR = By.xpath("//tr/td[contains(@class, 'page-title')]");
    private static final String CHECK_BOX_TO_SELECT_PAGE_PATTERN_LOCATOR = "//*[contains(text(),'%s')]/../../input";
    private static final By APPLY_BUTTON_LOCATOR = By.id("doaction");
    private static final String PAGE_TITLE_LINK_TEMPLATE_LOCATOR = "//td//a[contains(text(),'%s')]";


    private static final Logger logger = Logger.getLogger(PagesPage.class);

    public PagesPage() {

        super();
        bulkActionsDdl = new BulkActionsDdl();
        statusFilterMenu = new StatusFilterMenu();
        postsPagesActionsRow = new PostsPagesActionsRow();
        wordPressOnAzureDdl = new WordPressOnAzureDdl();
    }

    public PostsPagesActionsRow getActionsRow() {
        return postsPagesActionsRow;
    }

    public WordPressOnAzureDdl getWordPressOnAzureDdl() {
        return wordPressOnAzureDdl;
    }

    @Override
    @Step("Check if 'Pages' page is opened")
    public boolean isPageOpened() {
        try {
            logger.info("Finding the title of 'Pages' page");
            WebElement pagesPageTitle = Browser.waitForVisibilityOfElementLocatedAndFind(PAGES_PAGE_TITLE_LOCATOR);
            return pagesPageTitle.isDisplayed();
        } catch (TimeoutException ex) {
            logger.error("'Pages' page was not opened during expected time", ex);
            return false;
        }
    }

    @Step("Click 'Add New Page' button")
    public void addNewPageButtonClick() {
        logger.info("Clicking 'Add New Page' button");

        WebElement addNewPageButton = Browser.waitForElementToBeClickableAndFind(ADD_NEW_PAGE_BUTTON_LOCATOR);
        addNewPageButton.click();
    }

    @Step("Get all pages titles displayed on 'Pages' page")
    public List<String> getAllPagesTitles() {
        logger.info("Getting titles of all pages in the list on 'Pages' page");
        List<WebElement> allPagesTitlesElements = Browser.waitForPresenceOfAllElementsAndFind(ALL_PAGES_TITLES_LOCATOR);
        List<String> allPagesTitles = new ArrayList<>();

        for (WebElement pageTitleElement : allPagesTitlesElements) {
            String postTitle = pageTitleElement.getText();
            allPagesTitles.add(postTitle);
        }

        return allPagesTitles;
    }

    @Step("Check off check-box near page title '{pageTitle}'")
    public void checkOffPost(String pageTitle) {
        logger.info("Finding a page title and checking off check box near it");
        String xPath = String.format(CHECK_BOX_TO_SELECT_PAGE_PATTERN_LOCATOR, pageTitle);
        By checkBoxToSelectPageLocator = By.xpath(xPath);
        WebElement checkBoxToSelectPage = Browser.waitForElementToBeClickableAndFind(checkBoxToSelectPageLocator);
        checkBoxToSelectPage.click();

    }

    @Step("Clicking 'Apply' button to apply bulk action")
    public void clickApplyButton() {
        logger.info("Clicking 'Apply' button to apply bulk action");
        WebElement applyButton = Browser.waitForElementToBeClickableAndFind(APPLY_BUTTON_LOCATOR);
        applyButton.click();
    }

    @Step("Delete a page '{postTitle}'")
    public void deletePage(String postTitle) {
        logger.info("Deleting a page");
        checkOffPost(postTitle);
        bulkActionsDdl.selectBulkAction(BulkActionsEnum.MOVE_TO_TRASH);
        clickApplyButton();
        statusFilterMenu.clickOnItem(StatusFilterMenuEnum.TRASH);
        checkOffPost(postTitle);
        bulkActionsDdl.selectBulkAction(BulkActionsEnum.DELETE_PERMANENTLY);
        clickApplyButton();
    }

    @Step("Click on page title link on 'Pages' page")
    public void clickPageTitle(String pageTitle) {
        logger.info("Clicking page title link on 'Pages' page");
        String xPath = String.format(PAGE_TITLE_LINK_TEMPLATE_LOCATOR, pageTitle);
        By pageTitleLinkLocator = By.xpath(xPath);
        WebElement postTitleLink = Browser.waitForElementToBeClickableAndFind(pageTitleLinkLocator);
        postTitleLink.click();
    }

}