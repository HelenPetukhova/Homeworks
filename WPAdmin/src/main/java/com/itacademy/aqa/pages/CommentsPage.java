package com.itacademy.aqa.pages;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.elements.*;
import com.itacademy.aqa.enums.BulkActionsEnum;
import com.itacademy.aqa.enums.StatusFilterMenuEnum;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CommentsPage extends BaseAdminPage {

    private static Logger logger = Logger.getLogger(CommentsPage.class);

    private BulkActionsDdl bulkActionsDdl;
    private StatusFilterMenu statusFilterMenu;
    private CommentsActionsRow commentsActionsRow;
    private static final By COMMENTS_PAGE_TITLE_LOCATOR = By.xpath("//*[@class ='wp-heading-inline' ][contains(text(),'Comments')]");
    private static final By APPLY_BUTTON_LOCATOR = By.id("doaction");
    private static final By COMMENTS_TEXTS_LOCATOR = By.xpath("//td[contains(@class, 'comment')]/p");
    private static final String CHECK_BOX_TO_SELECT_COMMENT_PATTERN_LOCATOR = "//*[contains(text(),'%s')]/../../th/input";


    public CommentsPage() {
        super();
        bulkActionsDdl = new BulkActionsDdl();
        statusFilterMenu = new StatusFilterMenu();
        commentsActionsRow = new CommentsActionsRow();
    }


    public CommentsActionsRow getCommentsActionsRow() {
        return commentsActionsRow;
    }

    public StatusFilterMenu getStatusFilterMenu() {
        return statusFilterMenu;
    }


    public BulkActionsDdl getBulkActionsDdl() {
        return bulkActionsDdl;
    }

    @Override
    public boolean isPageOpened() {
        try {
            WebElement commentsPageTitle = Browser.waitForVisibilityOfElementLocatedAndFind(COMMENTS_PAGE_TITLE_LOCATOR);
            logger.info("Found 'Comments' page title");
            return commentsPageTitle.isDisplayed();
        } catch (RuntimeException ex) {
            logger.error("'Comments' page is not opened");
            return false;
        }
    }

    public String takeRowColor(String commentText) {
        logger.info("Taking background color of the row with comment: " + commentText);
        String rowWithColorTemplate = String.format("//tbody//p[contains(text(),'%s')]/..", commentText);
        By rowWithColorLocator = By.xpath(rowWithColorTemplate);
        WebElement rowWithColor = Browser.waitForElementToBeClickableAndFind(rowWithColorLocator);
        return rowWithColor.getCssValue("background-color");
    }

    public String takeLeftBorderColor(String commentText) {
        logger.info("Taking left border color of the row with comment: " + commentText);
        String rowWithColorTemplate = String.format("//tbody//p[contains(text(),'%s')]/../..//th[@class='check-column']", commentText);
        By rowWithColorLocator = By.xpath(rowWithColorTemplate);
        WebElement rowWithColor = Browser.waitForElementToBeClickableAndFind(rowWithColorLocator);
        return rowWithColor.getCssValue("border-left-color");
    }

    public List<String> getAllCommentsTitles() {
        logger.info("Getting all comments on 'Comments' page");
        List<WebElement> allCommentsTextsElements = Browser.waitForPresenceOfAllElementsAndFind(COMMENTS_TEXTS_LOCATOR);
        List<String> allCommentsTexts = new ArrayList<>();

        for (WebElement commentTextElement : allCommentsTextsElements) {
            String commentText = commentTextElement.getText();
            allCommentsTexts.add(commentText);
        }

        return allCommentsTexts;
    }


    public void deleteComment(String commentText) {
        logger.info("Deleting comment");
        checkOffComment(commentText);

        bulkActionsDdl.selectBulkAction(BulkActionsEnum.MOVE_TO_TRASH);
        clickApplyButton();
        statusFilterMenu.clickOnItem(StatusFilterMenuEnum.TRASH);
        checkOffComment(commentText);
        bulkActionsDdl.selectBulkAction(BulkActionsEnum.DELETE_PERMANENTLY);
        clickApplyButton();
    }

    public void checkOffComment(String commentText) {
        logger.info("Finding comment commentText" +  commentText + " and checking it off");
        String xPath = String.format(CHECK_BOX_TO_SELECT_COMMENT_PATTERN_LOCATOR, commentText);
        By checkBoxToSelectCommentLocator = By.xpath(xPath);
        WebElement checkBoxToSelectComment = Browser.waitForElementToBeClickableAndFind(checkBoxToSelectCommentLocator);
        checkBoxToSelectComment.click();

    }


    public void clickApplyButton() {
        logger.info("Clicking 'Apply' button");
        WebElement applyButton = Browser.waitForElementToBeClickableAndFind(APPLY_BUTTON_LOCATOR);
        applyButton.click();
    }

}
