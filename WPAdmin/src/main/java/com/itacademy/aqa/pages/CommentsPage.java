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
import java.util.Collections;
import java.util.List;

public class CommentsPage extends BaseAdminPage {

    private static final Logger logger = Logger.getLogger(CommentsPage.class);

    private final BulkActionsDdl bulkActionsDdl;
    private final StatusFilterMenu statusFilterMenu;
    private final CommentsActionsRow commentsActionsRow;
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
    @Step("Check that 'Comments' page is opened ")
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

    @Step("Take background color of the row with comment '{commentText}'")
    public String takeRowColor(String commentText) {
        logger.info("Taking background color of the row with comment: " + commentText);
        String commentCellTemplate = String.format("//tbody//p[contains(text(),'%s')]/..", commentText);
        By commentCellLocator = By.xpath(commentCellTemplate);
        WebElement commentCell = Browser.waitForElementToBeClickableAndFind(commentCellLocator);
        return commentCell.getCssValue("background-color");
    }

    @Step("Take left border color of the row with comment '{commentText}'")
    public String takeLeftBorderColor(String commentText) {
        logger.info("Taking left border color of the row with comment: " + commentText);
        String checkboxCommentCellTemplate = String.format("//tbody//p[contains(text(),'%s')]/../..//th[@class='check-column']", commentText);
        By checkboxCommentCellLocator = By.xpath(checkboxCommentCellTemplate);
        WebElement checkboxCommentCell = Browser.waitForElementToBeClickableAndFind(checkboxCommentCellLocator);
        return checkboxCommentCell.getCssValue("border-left-color");
    }

    @Step("Get all comments on 'Comments' page")
    public List<String> getAllCommentsTitles() {
        logger.info("Getting all comments on 'Comments' page");
        List<WebElement> allCommentsTextsElements = Browser.waitForPresenceOfAllElementsAndFind(COMMENTS_TEXTS_LOCATOR);
        List<String> allCommentsTexts = new ArrayList<>();

        for (WebElement commentTextElement : allCommentsTextsElements) {
            String commentText = commentTextElement.getText();
            allCommentsTexts.add(commentText);
        }
        if (allCommentsTextsElements.isEmpty()) {
            logger.warn("No comments found on the page");
            return Collections.emptyList();
        }else {
            return allCommentsTexts;
        }
    }

    @Step("Delete comment")
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

    @Step("Check off the comment '{commentText}'")
    public void checkOffComment(String commentText) {
        logger.info("Finding comment " + commentText + " and checking it off");
        String xPath = String.format(CHECK_BOX_TO_SELECT_COMMENT_PATTERN_LOCATOR, commentText);
        By checkBoxToSelectCommentLocator = By.xpath(xPath);
        WebElement checkBoxToSelectComment = Browser.waitForElementToBeClickableAndFind(checkBoxToSelectCommentLocator);
        checkBoxToSelectComment.click();

    }

    @Step("Click 'Apply' button")
    public void clickApplyButton() {
        logger.info("Clicking 'Apply' button");
        WebElement applyButton = Browser.waitForElementToBeClickableAndFind(APPLY_BUTTON_LOCATOR);
        applyButton.click();
    }
}