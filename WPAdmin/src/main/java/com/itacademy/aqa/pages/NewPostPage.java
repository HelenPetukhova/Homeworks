package com.itacademy.aqa.pages;

import com.itacademy.aqa.config.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;


public class NewPostPage extends BaseWPPage {


    private static final By POST_TAB_LOCATOR = By.id("tabs-0-edit-post/document");
    private static final By NEW_POST_PAGE_IFRAME_LOCATOR = By.xpath("//iframe[@name=\"editor-canvas\"]");
    private static final By NEW_POST_TITLE_INPUT_FIELD_LOCATOR = By.cssSelector("h1.editor-post-title__input");
    private static final By NEW_POST_TEXT_INPUT_FIELD_INITIAL_LOCATOR = By.cssSelector("div.block-editor-default-block-appender");
    private static final By NEW_POST_TEXT_INPUT_FIELD_FINAL_LOCATOR = By.cssSelector("p.block-editor-rich-text__editable[contenteditable='true']");
    private static final By NEW_POST_SAVE_DRAFT_BUTTON_LOCATOR = By.xpath("//button[contains(@class, 'editor-post-save-draft')][text()='Save draft']");
    private static final By NEW_POST_SAVED_BUTTON_LOCATOR = By.xpath("//button[contains(@class, 'is-saved')][text()='Saved']");
    private static final By VIEW_POST_BUTTON = By.xpath("//a[contains(@class, 'components-button')]");
   // private static final By WELCOME_TO_BLOCK_EDITOR_POPUP_LOCATOR = By.className("components-guide__container");
    private static final By WELCOME_POPUP_CLOSE_BUTTON_LOCATOR = By.xpath("//*[@class='components-modal__header']//button");

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

    public void addTitleAndText() {
        WebElement frame = Browser.waitForVisibilityOfElementLocatedAndFind(NEW_POST_PAGE_IFRAME_LOCATOR); //можно упростить до //*[@name="editor-canvas"], //iframe тоже уникальный
        Browser.getWebDriver().switchTo().frame(frame);
        WebElement newPostTitleInputField = Browser.waitForElementToBeClickableAndFind(NEW_POST_TITLE_INPUT_FIELD_LOCATOR);
        newPostTitleInputField.sendKeys("KL NEW POST TITLE TEST");

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

    public void viewPostButtonClick() {
        WebElement viewPostButton = Browser.waitForElementToBeClickableAndFind(VIEW_POST_BUTTON);
        viewPostButton.click();
    }

    public void closeWelcomeToEditorPopUp() {
        try {
          //  WebElement welcomeToEditorPopUp = Browser.waitForVisibilityOfElementLocatedAndFind(WELCOME_TO_BLOCK_EDITOR_POPUP_LOCATOR);
            WebElement welcomePopUpCloseButton = Browser.waitForElementToBeClickableAndFind(WELCOME_POPUP_CLOSE_BUTTON_LOCATOR);
            welcomePopUpCloseButton.click();
            System.out.println("'Welcome to Block Editor' pop-up was closed");
        } catch (RuntimeException ex) {
            System.out.println("'Welcome to Block Editor' pop-up wasn't appear");
        }
    }
}
