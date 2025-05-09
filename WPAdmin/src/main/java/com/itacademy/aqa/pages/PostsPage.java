package com.itacademy.aqa.pages;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.elements.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class PostsPage {
    LeftMenu leftMenu;
    StatusFilterMenu statusFilterMenu;
    BulkActionsDdl bulkActionsDdl;
    private static final By ALL_POSTS_PAGE_TITLE_LOCATOR = By.xpath("//*[@class ='wp-heading-inline' ][contains(text(),'Posts')]");
    private static final By ADD_NEW_POST_BUTTON_LOCATOR = By.className("page-title-action");
    private static final By ALL_POSTS_TITLES_LOCATOR = By.xpath("//tr/td[contains(@class, 'page-title')]");
    private static final String CHECK_BOX_TO_SELECT_POST_PATTERN_LOCATOR = "//*[contains(text(),'%s')]/../../input";
    private static final By BULK_ACTIONS_DDL_LOCATOR = By.id("bulk-action-selector-top");
    private static final String BULK_ACTIONS_OPTION_TEMPLATE_LOCATOR = "//option[@value='%s']";
    private static final By APPLY_BUTTON_LOCATOR = By.id("doaction");
    private static final String POST_STATUS_FILTER_LINK_TEMPLATE_LOCATOR = "//li[@class='%s']/a";


    public PostsPage() {

        leftMenu = new LeftMenu();
        statusFilterMenu = new StatusFilterMenu();
        bulkActionsDdl = new BulkActionsDdl();
    }

    public LeftMenu getLeftMenu() {

        return leftMenu;
    }

    public StatusFilterMenu getStatusFilterMenu() {
        return statusFilterMenu;
    }

    public boolean isPageOpened() {
        try {
            WebElement postPageTitle = Browser.waitForVisibilityOfElementLocatedAndFind(ALL_POSTS_PAGE_TITLE_LOCATOR);
            return postPageTitle.isDisplayed();
        } catch (RuntimeException ex){
            return false;
        }
    }


    public void clickAddNewPostButton() {

        WebElement addNewPostButton = Browser.waitForElementToBeClickableAndFind(ADD_NEW_POST_BUTTON_LOCATOR);
        addNewPostButton.click();

    }

    public List<String> getAllPostsTitles() {


        List<WebElement> allPostsTitlesElements = Browser.waitForPresenceOfAllElementsAndFind(ALL_POSTS_TITLES_LOCATOR);
        List<String> allPostsTitles = new ArrayList<>();

        for (WebElement postTitleElement : allPostsTitlesElements) {
            String postTitle = postTitleElement.getText();
            allPostsTitles.add(postTitle);
        }

        return allPostsTitles;
    }

    public void checkOffPost(String postTitle) {
        String xPath = String.format(CHECK_BOX_TO_SELECT_POST_PATTERN_LOCATOR, postTitle);
        By CHECK_BOX_TO_SELECT_POST_LOCATOR = By.xpath(xPath);
        WebElement checkBoxToSelectPost = Browser.waitForElementToBeClickableAndFind(CHECK_BOX_TO_SELECT_POST_LOCATOR);
        checkBoxToSelectPost.click();

    }


    public void clickApplyButton() {
        WebElement applyButton = Browser.waitForElementToBeClickableAndFind(APPLY_BUTTON_LOCATOR);
        applyButton.click();
    }


    public void deletePost(String postTitle) {
        checkOffPost(postTitle);
        bulkActionsDdl.selectBulkAction(BulkActionsEnum.MOVE_TO_TRASH);
        clickApplyButton();
        statusFilterMenu.clickOnItem(StatusFilterMenuEnum.TRASH);
        checkOffPost(postTitle);
        bulkActionsDdl.selectBulkAction(BulkActionsEnum.DELETE_PERMANENTLY);
        clickApplyButton();
    }
}
