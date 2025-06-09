package com.itacademy.aqa.pages;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.elements.*;
import com.itacademy.aqa.enums.BulkActionsEnum;
import com.itacademy.aqa.enums.StatusFilterMenuEnum;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class PostsPage extends BaseAdminPage {

    private final StatusFilterMenu statusFilterMenu;
    private final BulkActionsDdl bulkActionsDdl;
    private final PostsPagesActionsRow postsPagesActionsRow;
    private static final By ALL_POSTS_PAGE_TITLE_LOCATOR = By.xpath("//*[@class ='wp-heading-inline' ][contains(text(),'Posts')]");
    private static final By ADD_NEW_POST_BUTTON_LOCATOR = By.className("page-title-action");
    private static final By ALL_POSTS_TITLES_LOCATOR = By.xpath("//tr/td[contains(@class, 'page-title')]");
    private static final String CHECK_BOX_TO_SELECT_POST_PATTERN_LOCATOR = "//*[contains(text(),'%s')]/../../input";
    private static final By APPLY_BUTTON_LOCATOR = By.id("doaction");
    private static final String POST_TITLE_LINK_TEMPLATE_LOCATOR = "//td//a[contains(text(),'%s')]";

    private static final Logger logger = Logger.getLogger(PostsPage.class);

    public PostsPage() {
        super();
        statusFilterMenu = new StatusFilterMenu();
        bulkActionsDdl = new BulkActionsDdl();
        postsPagesActionsRow = new PostsPagesActionsRow();
    }


    public StatusFilterMenu getStatusFilterMenu() {
        return statusFilterMenu;
    }


    public BulkActionsDdl getBulkActionsDdl() {
        return bulkActionsDdl;
    }


    @Step("Check that 'Posts' page is opened")
    public boolean isPageOpened() {
        try {
            WebElement postPageTitle = Browser.waitForVisibilityOfElementLocatedAndFind(ALL_POSTS_PAGE_TITLE_LOCATOR);
            logger.info("'Posts' page is opened");
            return postPageTitle.isDisplayed();
        } catch (RuntimeException ex) {
            logger.error("'Posts' page is not opened");
            return false;
        }
    }

    @Step("Click 'Add New Post' button")
    public void clickAddNewPostButton() {
        logger.info("Clicking 'Add New Post' button");
        WebElement addNewPostButton = Browser.waitForElementToBeClickableAndFind(ADD_NEW_POST_BUTTON_LOCATOR);
        addNewPostButton.click();

    }

    @Step("Get all posts titles displayed in the list on 'Posts' page")
    public List<String> getAllPostsTitles() {
        logger.info("Getting all the posts titles from the list on 'Posts' page");

        List<WebElement> allPostsTitlesElements = Browser.waitForPresenceOfAllElementsAndFind(ALL_POSTS_TITLES_LOCATOR);
        List<String> allPostsTitles = new ArrayList<>();

        for (WebElement postTitleElement : allPostsTitlesElements) {
            String postTitle = postTitleElement.getText();
            allPostsTitles.add(postTitle);
        }

        return allPostsTitles;
    }

    @Step("Check off post with title: '{postTitle}'")
    public void checkOffPost(String postTitle) {
        logger.info("Finding a post " + postTitle + " and check it off");
        String xPath = String.format(CHECK_BOX_TO_SELECT_POST_PATTERN_LOCATOR, postTitle);
        By checkBoxToSelectPostLocator = By.xpath(xPath);
        WebElement checkBoxToSelectPost = Browser.waitForElementToBeClickableAndFind(checkBoxToSelectPostLocator);
        checkBoxToSelectPost.click();

    }


    public void clickApplyButton() {
        WebElement applyButton = Browser.waitForElementToBeClickableAndFind(APPLY_BUTTON_LOCATOR);
        applyButton.click();
    }

    @Step("Delete post: '{postTitle}'")
    public void deletePost(String postTitle) {
        logger.info("Deleting a post: " + postTitle);
        checkOffPost(postTitle);
        bulkActionsDdl.selectBulkAction(BulkActionsEnum.MOVE_TO_TRASH);
        clickApplyButton();
        statusFilterMenu.clickOnItem(StatusFilterMenuEnum.TRASH);
        checkOffPost(postTitle);
        bulkActionsDdl.selectBulkAction(BulkActionsEnum.DELETE_PERMANENTLY);
        clickApplyButton();
    }

    @Step("Click post title to open it")
    public void clickPostTitle(String postTitle) {
        String xPath = String.format(POST_TITLE_LINK_TEMPLATE_LOCATOR, postTitle);
        By postTitleLinkLocator = By.xpath(xPath);
        WebElement postTitleLink = Browser.waitForElementToBeClickableAndFind(postTitleLinkLocator);
        postTitleLink.click();
    }

    public PostsPagesActionsRow getActionsRow() {
        return postsPagesActionsRow;
    }


}