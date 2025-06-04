package com.itacademy.aqa.pages;

import com.itacademy.aqa.config.Browser;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;


public class NewPostPage extends BaseEditorPage {

    private static final By NEW_POST_PAGE_IFRAME_LOCATOR = By.xpath("//iframe[@name=\"editor-canvas\"]");
    private static final By NEW_POST_TITLE_INPUT_FIELD_LOCATOR = By.cssSelector("h1.editor-post-title__input");
    private static final By NEW_POST_TEXT_INPUT_FIELD_INITIAL_LOCATOR = By.cssSelector("div.block-editor-default-block-appender");
    private static final By NEW_POST_TEXT_INPUT_FIELD_FINAL_LOCATOR = By.cssSelector("p.block-editor-rich-text__editable[contenteditable='true']");
    private static final By NEW_POST_SAVE_DRAFT_BUTTON_LOCATOR = By.xpath("//button[contains(@class, 'editor-post-save-draft')][text()='Save draft']");
    private static final By NEW_POST_SAVED_BUTTON_LOCATOR = By.xpath("//button[contains(@class, 'is-saved')][text()='Saved']");
    private static final By VIEW_POSTS_BUTTON = By.xpath("//a[contains(@class, 'edit-post-fullscreen-mode-close')]");
    private static final By WELCOME_POPUP_CLOSE_BUTTON_LOCATOR = By.xpath("//*[@class='components-modal__header']//button");
    private static final By SUBMIT_FOR_REVIEW_BUTTON_1_LOCATOR = By.xpath("//*[@class='editor-header__settings']//*[contains(text(),'Submit for Review')]");
    private static final By SUBMIT_FOR_REVIEW_BUTTON_2_LOCATOR = By.xpath("//*[@class='editor-post-publish-panel']//*[contains(text(),'Submit for Review')]");
    private static final By POST_TAB_LOCATOR = By.xpath("//*[@id='tabs-0-edit-post/document'][text()='Post']");
    private static final By POST_STATUS_IN_SETTINGS_PANEL_LOCATOR = By.xpath("//*[contains(@class,'editor-post-status')]");
    private static final By PUBLISH_BUTTON_1_LOCATOR = By.xpath("//*[@class='editor-header__settings']//*[contains(text(),'Publish')]");
    private static final By PUBLISH_BUTTON_2_LOCATOR = By.xpath("//*[@class='editor-post-publish-panel__header']//*[contains(text(),'Publish')]");
    private static final By VIEW_POST_BUTTON_LOCATOR = By.xpath("//*[@class='post-publish-panel__postpublish-buttons']/a[.='View Post']");
    private static final By POST_ADDRESS_INPUT_FILED_LOCATOR = By.className("components-text-control__input");

    private static Logger logger = Logger.getLogger(NewPostPage.class);

    public NewPostPage() {
        super();
    }


    @Override
    public boolean isPageOpened() {
        try {logger.info("Finding 'Post' tab on page");
            return Browser.waitForElementToBeClickableAndFind(POST_TAB_LOCATOR).isDisplayed();
        } catch (NotFoundException ex) {
            logger.error("Post Editor page is not opened");
            return false;
        }
    }

//    public void addTitleAndText(String postTitle) {
//        WebElement frame = Browser.waitForVisibilityOfElementLocatedAndFind(NEW_POST_PAGE_IFRAME_LOCATOR); //можно упростить до //*[@name="editor-canvas"], //iframe тоже уникальный
//        Browser.getWebDriver().switchTo().frame(frame);
//        WebElement newPostTitleInputField = Browser.waitForElementToBeClickableAndFind(NEW_POST_TITLE_INPUT_FIELD_LOCATOR);
//        newPostTitleInputField.sendKeys(postTitle);
//
//        WebElement newPostTextInputField = Browser.waitForElementToBeClickableAndFind(NEW_POST_TEXT_INPUT_FIELD_INITIAL_LOCATOR);
//        newPostTextInputField.click();
//        WebElement newPostTextInputField2 = Browser.waitForElementToBeClickableAndFind(NEW_POST_TEXT_INPUT_FIELD_FINAL_LOCATOR);
//        newPostTextInputField2.click();
//        newPostTextInputField2.sendKeys("KL NEW POST Text TEST");
//
//        Browser.getWebDriver().switchTo().defaultContent();
//    }

    public void addTitleAndText(String postTitle) {
        logger.info("Adding title and text for a post");
        addTitleAndText(postTitle, "POST", NEW_POST_PAGE_IFRAME_LOCATOR, NEW_POST_TITLE_INPUT_FIELD_LOCATOR, NEW_POST_TEXT_INPUT_FIELD_INITIAL_LOCATOR, NEW_POST_TEXT_INPUT_FIELD_FINAL_LOCATOR);
    }


    public void saveDraft() {
        logger.info("Saving draft of a post");
        WebElement saveDraftButton = Browser.waitForElementToBeClickableAndFind(NEW_POST_SAVE_DRAFT_BUTTON_LOCATOR);
        saveDraftButton.click();
    }

    public boolean isSavedButtonDisplayed() {
        try {
            return Browser.waitForElementToBeClickableAndFind(NEW_POST_SAVED_BUTTON_LOCATOR).isDisplayed();
        } catch (NotFoundException ex) {
            return false;
        }
    }

    public void viewPostsButtonClick() {
        logger.info("Clicking 'View Posts' button");
        WebElement viewPostsButton = Browser.waitForElementToBeClickableAndFind(VIEW_POSTS_BUTTON);
        viewPostsButton.click();
    }

    public void closeWelcomeToEditorPopUp() {
        try {
            //  WebElement welcomeToEditorPopUp = Browser.waitForVisibilityOfElementLocatedAndFind(WELCOME_TO_BLOCK_EDITOR_POPUP_LOCATOR);
            WebElement welcomePopUpCloseButton = Browser.waitForElementToBeClickableAndFind(WELCOME_POPUP_CLOSE_BUTTON_LOCATOR);
            welcomePopUpCloseButton.click();
            System.out.println("'Welcome to Block Editor' pop-up was closed");
            logger.info("'Welcome to Block Editor' pop-up was closed");
        } catch (RuntimeException ex) {
            System.out.println("'Welcome to Block Editor' pop-up wasn't appear");
            logger.warn("'Welcome to Block Editor' pop-up wasn't appear");
        }
    }

    public void submitPostForReview() {
        try {
            logger.info("Submitting a post");
            WebElement submitForReviewButton1 = Browser.waitForElementToBeClickableAndFind(SUBMIT_FOR_REVIEW_BUTTON_1_LOCATOR);
            submitForReviewButton1.click();
            WebElement submitForReviewButton2 = Browser.waitForElementToBeClickableAndFind(SUBMIT_FOR_REVIEW_BUTTON_2_LOCATOR);
            submitForReviewButton2.click();
            getPostStatusInSettings();

        } catch (RuntimeException ex) {
            System.out.println("'Submit For Review' button is not displayed");
            logger.error("'Submit For Review' button is not found");
        }
    }

    public String getPostStatusInSettings() {
        logger.info("Getting post status in 'Post' tab of Setting's panel");
        WebElement postTabInSettings = Browser.waitForElementToBeClickableAndFind(POST_TAB_LOCATOR);
        postTabInSettings.click();
        WebElement postStatus = Browser.waitForVisibilityOfElementLocatedAndFind(POST_STATUS_IN_SETTINGS_PANEL_LOCATOR);
        return postStatus.getText();
    }

    public void publishPost() {
        try {
            logger.info("Post publishing");
            WebElement publishButton1 = Browser.waitForElementToBeClickableAndFind(PUBLISH_BUTTON_1_LOCATOR);
            publishButton1.click();
            logger.info("Clicked 'Publish' button");
            WebElement publishButton2 = Browser.waitForElementToBeClickableAndFind(PUBLISH_BUTTON_2_LOCATOR);
            publishButton2.click();
            logger.info("Confirmed by clicking second 'Publish' button");
            WebElement viewPostButton = Browser.waitForElementToBeClickableAndFind(VIEW_POST_BUTTON_LOCATOR);
            viewPostButton.isDisplayed();
            logger.info("'View Post' button is displayed after publishing");
        } catch (RuntimeException ex) {
            System.out.println("'Publish' button is not displayed");
            logger.error("'Publish' button is not found");
        }
    }

    public void viewPostButtonCLick() {
        try {
            logger.info("Clicking 'View Post' button");
            WebElement viewPostButton = Browser.waitForElementToBeClickableAndFind(VIEW_POST_BUTTON_LOCATOR);
            viewPostButton.click();
        } catch (RuntimeException ex) {
            System.out.println("'View Post' button is not displayed");
            logger.error("'View Post' button is not found");
        }
    }

    public String takeNewPageUrl() {
        WebElement pageAddressField = Browser.waitForVisibilityOfElementLocatedAndFind(POST_ADDRESS_INPUT_FILED_LOCATOR);
        return pageAddressField.getAttribute("value");
    }

}
