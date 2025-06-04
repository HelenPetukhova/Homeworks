package com.itacademy.aqa.pages;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.elements.WordPressOnAzureDdl;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class WordPressPostPagePage extends BaseWPPage {
    private static final By POST_TITLE_LOCATOR = By.xpath("//*[contains(@class,'wp-block-post-title')]");
    public static final By WORDPRESS_ON_AZURE_LINK_LOCATOR = By.className("wp-block-site-title");
    private static final By PAGES_LINKS_LOCATOR = By.xpath("//*[contains(@class, 'wp-block-pages-list__item__link')]");
    private static final By COMMENTS_SECTION_TITLE_LOCATOR = By.xpath("//*[@class='wp-block-heading'][.='Comments']");
    private static final By COMMENT_FIELD_LOCATOR = By.id("comment");
    private static final By NAME_FIELD_LOCATOR = By.id("author");
    private static final By EMAIL_FIELD_LOCATOR = By.id("email");
    private static final By WEBSITE_FIELD_LOCATOR = By.id("url");
    private static final By SAVE_FOR_NEXT_TIME_CHECK_BOX_LOCATOR = By.id("wp-comment-cookies-consent");
    private static final By POST_COMMENT_BUTTON_LOCATOR = By.id("submit");
    private static final By COMMENT_TEXT_LOCATOR = By.xpath("//*[@class='wp-block-comment-content']");


    private static Logger logger = Logger.getLogger(WordPressPostPagePage.class);

    public WordPressPostPagePage() {

    }


    @Override
    public boolean isPageOpened() {
        try {
            logger.info("Finding post title");
            return Browser.waitForVisibilityOfElementLocatedAndFind(POST_TITLE_LOCATOR).isDisplayed();
        } catch (NotFoundException ex) {
            logger.info("Word Press Post is not opened");
            return false;
        }
    }

    public String getPostTitle() {
        logger.info("Getting a post title");
        WebElement postTitle = Browser.waitForVisibilityOfElementLocatedAndFind(POST_TITLE_LOCATOR);
        return postTitle.getText().trim();
    }

    public void clickWordPressOnAzureLink() {
        logger.info("Clicking on 'WordPress On Azure' link");
        WebElement wordPressOnAzureLink = Browser.waitForElementToBeClickableAndFind(WORDPRESS_ON_AZURE_LINK_LOCATOR);
        wordPressOnAzureLink.click();

    }


    public List<String> getPagesLinks() {
        logger.info("Getting all pages links on WordPress page");
        List<WebElement> pagesLinks = Browser.waitForPresenceOfAllElementsAndFind(PAGES_LINKS_LOCATOR);
        List<String> pagesTitles = new ArrayList<>();

        for (WebElement pageLink : pagesLinks) {
            pagesTitles.add(pageLink.getText().trim());
        }
        return pagesTitles;
    }

    public boolean commentsSectionDisplayed() {
        try {
            logger.info("Finding 'Comments' section");
            return Browser.waitForVisibilityOfElementLocatedAndFind(COMMENTS_SECTION_TITLE_LOCATOR).isDisplayed();
        } catch (NotFoundException ex) {
            logger.error("'Comments' section is not displayed");
            return false;
        }
    }

    public boolean commentFieldDisplayed() {
        try {
            logger.info("Finding 'Comment' field");
            return Browser.waitForElementToBeClickableAndFind(COMMENT_FIELD_LOCATOR).isDisplayed();
        } catch (NotFoundException ex) {
            logger.error("'Comment' field is not available");
            return false;
        }
    }

    public boolean nameFieldDisplayed() {
        try {
            logger.info("Finding 'Name' field");
            return Browser.waitForElementToBeClickableAndFind(NAME_FIELD_LOCATOR).isDisplayed();
        } catch (NotFoundException ex) {
            logger.error("'Name' field is not available");

            return false;
        }
    }

    public boolean emailFieldDisplayed() {
        try {
            logger.info("Finding 'Email' field");
            return Browser.waitForElementToBeClickableAndFind(EMAIL_FIELD_LOCATOR).isDisplayed();
        } catch (NotFoundException ex) {
            logger.error("'Email' field is not available");
            return false;
        }
    }

    public boolean websiteFieldDisplayed() {
        try {
            logger.info("Finding 'Website' field");
            return Browser.waitForElementToBeClickableAndFind(WEBSITE_FIELD_LOCATOR).isDisplayed();
        } catch (NotFoundException ex) {
            logger.error("'Website' field is not available");
            return false;
        }
    }

    public boolean saveForNextTimeCheckBoxDisplayed() {
        try {
            return Browser.waitForElementToBeClickableAndFind(SAVE_FOR_NEXT_TIME_CHECK_BOX_LOCATOR).isDisplayed();
        } catch (NotFoundException ex) {
            return false;
        }
    }

    public void fillCommentField(String commentText) {
        logger.info("Filling 'Comment' field");
        WebElement commentField = Browser.waitForElementToBeClickableAndFind(COMMENT_FIELD_LOCATOR);
        commentField.clear();
        commentField.sendKeys(commentText);
    }

    public void fillNameField(String commentatorName) {
        logger.info("Filling 'Name' field");

        WebElement nameField = Browser.waitForElementToBeClickableAndFind(NAME_FIELD_LOCATOR);
        nameField.clear();
        nameField.sendKeys(commentatorName);
    }

    public void fillEmailField(String email) {
        logger.info("Filling 'Email' field");

        WebElement fillEmailField = Browser.waitForElementToBeClickableAndFind(EMAIL_FIELD_LOCATOR);
        fillEmailField.clear();
        fillEmailField.sendKeys(email);
    }

    public void fillWebsiteField(String website) {
        logger.info("Filling 'Website' field");

        WebElement fillEmailField = Browser.waitForElementToBeClickableAndFind(WEBSITE_FIELD_LOCATOR);
        fillEmailField.clear();
        fillEmailField.sendKeys(website);
    }

    public void clickPostCommentButton() {
        logger.info("Clicking 'Post Comment' button");
        WebElement postCommentButton = Browser.waitForElementToBeClickableAndFind(POST_COMMENT_BUTTON_LOCATOR);
        postCommentButton.click();
    }

    public void addCommentToPost(String commentText, String commentatorName, String email) {
        logger.info("Adding a comment to published post");
        fillCommentField(commentText);
        fillNameField(commentatorName);
        fillEmailField(email);
        clickPostCommentButton();
    }

    public List<String> getCommentsText() {
        logger.info("Getting comments text");
        List<WebElement> commentsTextElements = Browser.waitForPresenceOfAllElementsAndFind(COMMENT_TEXT_LOCATOR);
        List<String> commentsTexts = new ArrayList<>();

        for (WebElement commentTextElement : commentsTextElements) {
            commentsTexts.add(commentTextElement.getText().trim());
        }
        return commentsTexts;
    }

    public String getCommentText() {
        logger.info("Getting comment's text");
        WebElement commentTextElement = Browser.waitForVisibilityOfElementLocatedAndFind(COMMENT_TEXT_LOCATOR);
        return commentTextElement.getText().trim();
    }


    public boolean isCommentDisplayed(String commentText) {
        try {
            String commentWithSpecifiedText = String.format("//*[@class='wp-block-comment-content']//*[contains(text(),'%s')]", commentText);
            By commentWithSpecifiedTextLocator = By.xpath(commentWithSpecifiedText);
            WebElement commentWithSpecifiedTextElement = Browser.waitForVisibilityOfElementLocatedAndFind(commentWithSpecifiedTextLocator);
            logger.info("Finding comment: " + commentText);
            return commentWithSpecifiedTextElement.isDisplayed();
        } catch (RuntimeException ex) {
            logger.error("The comment is not found: " + commentText);
            return false;
        }
    }

    public List<String> getCommentAuthorByCommentText(String commentText) {
        logger.info("Getting comment's author by comment text");
        String commentAuthorsByCommentTextTemplateLocator = String.format("//*[@class='wp-block-comment-content']/p[text()='%s']/../..//*[@class='wp-block-comment-author-name']", commentText);
        By commentAuthorsByCommentTextLocator = By.xpath(commentAuthorsByCommentTextTemplateLocator);
        List<WebElement> commentTextAuthorsElements = Browser.waitForPresenceOfAllElementsAndFind(commentAuthorsByCommentTextLocator);
        List<String> commentTextAuthors = new ArrayList<>();

        for (WebElement commentTextAuthorElement : commentTextAuthorsElements) {
            String commentTextAuthor = commentTextAuthorElement.getText().trim();
            commentTextAuthors.add(commentTextAuthor);
        }

        return commentTextAuthors;

    }
}