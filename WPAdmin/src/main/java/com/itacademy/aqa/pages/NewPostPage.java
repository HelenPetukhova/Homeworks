package com.itacademy.aqa.pages;

import com.itacademy.aqa.config.Browser;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;


public class NewPostPage extends BaseWPPage {


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
    private static final By PUBLISH_BUTTON_2_LOCATOR = By.xpath("//*[@class='editor-post-publish-panel']//*[contains(text(),'Publish')]");
    private static final By VIEW_POST_BUTTON_LOCATOR = By.xpath("//*[@class='post-publish-panel__postpublish-buttons']/a[.='View Post']");
    private static final By POST_STATUS_LINK_LOCATOR = By.xpath("//*[@class='editor-post-panel__row-label'][.='Status']/following-sibling::div//button");
    private static Logger logger = Logger.getLogger(NewPostPage.class);

    public NewPostPage() {
    }


    @Override
    public boolean isPageOpened() {
        try {
            return Browser.waitForElementToBeClickableAndFind(POST_TAB_LOCATOR).isDisplayed();
        } catch (NotFoundException ex) {
            return false;
        }
    }

    public void addTitleAndText(String postTitle) {
        WebElement frame = Browser.waitForVisibilityOfElementLocatedAndFind(NEW_POST_PAGE_IFRAME_LOCATOR); //можно упростить до //*[@name="editor-canvas"], //iframe тоже уникальный
        Browser.getWebDriver().switchTo().frame(frame);
        WebElement newPostTitleInputField = Browser.waitForElementToBeClickableAndFind(NEW_POST_TITLE_INPUT_FIELD_LOCATOR);
        newPostTitleInputField.sendKeys(postTitle);

        WebElement newPostTextInputField = Browser.waitForElementToBeClickableAndFind(NEW_POST_TEXT_INPUT_FIELD_INITIAL_LOCATOR);
        newPostTextInputField.click();
        WebElement newPostTextInputField2 = Browser.waitForElementToBeClickableAndFind(NEW_POST_TEXT_INPUT_FIELD_FINAL_LOCATOR);
        newPostTextInputField2.click();
        newPostTextInputField2.sendKeys("KL NEW POST Text TEST");

        Browser.getWebDriver().switchTo().defaultContent();
    }

    public void saveDraft() {
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
        WebElement viewPostsButton = Browser.waitForElementToBeClickableAndFind(VIEW_POSTS_BUTTON);
        viewPostsButton.click();
    }

    public void closeWelcomeToEditorPopUp() {
        try {
            //  WebElement welcomeToEditorPopUp = Browser.waitForVisibilityOfElementLocatedAndFind(WELCOME_TO_BLOCK_EDITOR_POPUP_LOCATOR);
            WebElement welcomePopUpCloseButton = Browser.waitForElementToBeClickableAndFind(WELCOME_POPUP_CLOSE_BUTTON_LOCATOR);
            welcomePopUpCloseButton.click();
            System.out.println("'Welcome to Block Editor' pop-up was closed");
        } catch (RuntimeException ex) {
            System.out.println("'Welcome to Block Editor' pop-up wasn't appear");
            logger.warn("'Welcome to Block Editor' pop-up wasn't appear");
        }
    }

    public void submitPostForReview() {
        try {
            WebElement SubmitForReviewButton1 = Browser.waitForElementToBeClickableAndFind(SUBMIT_FOR_REVIEW_BUTTON_1_LOCATOR);
            SubmitForReviewButton1.click();
            WebElement SubmitForReviewButton2 = Browser.waitForElementToBeClickableAndFind(SUBMIT_FOR_REVIEW_BUTTON_2_LOCATOR);
            SubmitForReviewButton2.click();
            getPostStatusInSettings();

        } catch (RuntimeException ex) {
            System.out.println("'Submit For Review' button is not displayed");
            logger.error("'Submit For Review' button is not found");
        }
    }

    public String getPostStatusInSettings(){
        WebElement postTabInSettings = Browser.waitForElementToBeClickableAndFind(POST_TAB_LOCATOR);
        postTabInSettings.click();
        WebElement postStatus = Browser.waitForVisibilityOfElementLocatedAndFind(POST_STATUS_IN_SETTINGS_PANEL_LOCATOR);
        return postStatus.getText();
    }

    public void publishPost() {
        try {
            WebElement publishButton1 = Browser.waitForElementToBeClickableAndFind(PUBLISH_BUTTON_1_LOCATOR);
            publishButton1.click();
            WebElement publishButton2 = Browser.waitForElementToBeClickableAndFind(PUBLISH_BUTTON_2_LOCATOR);
            publishButton2.click();
            WebElement viewPostButton = Browser.waitForElementToBeClickableAndFind(VIEW_POST_BUTTON_LOCATOR);
            viewPostButton.isDisplayed();
        } catch (RuntimeException ex) {
            System.out.println("'Publish' button is not displayed");
            logger.error("'Publish' button is not found");
        }
    }

    public void viewPostButtonCLick(){
        try {
            WebElement viewPostButton = Browser.waitForElementToBeClickableAndFind(VIEW_POST_BUTTON_LOCATOR);
            viewPostButton.click();
        } catch (RuntimeException ex) {
            System.out.println("'View Post' button is not displayed");
            logger.error("'View Post' button is not found");
        }
    }

}
