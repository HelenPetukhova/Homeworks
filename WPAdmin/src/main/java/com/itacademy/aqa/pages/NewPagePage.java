package com.itacademy.aqa.pages;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.elements.ItemStatusEnum;
import com.itacademy.aqa.elements.StatusPopUp;
import org.apache.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;


public class NewPagePage extends BaseWPPage {

    private StatusPopUp statusPopUp;
    private static final By NEW_PAGE_PAGE_IFRAME_LOCATOR = By.xpath("//iframe[@name=\"editor-canvas\"]"); //same as in new post page
    private static final By NEW_PAGE_TITLE_INPUT_FIELD_LOCATOR = By.cssSelector("h1.editor-post-title__input");  //same as in new post page
    private static final By NEW_PAGE_TEXT_INPUT_FIELD_INITIAL_LOCATOR = By.cssSelector("div.block-editor-default-block-appender");
    private static final By NEW_PAGE_TEXT_INPUT_FIELD_FINAL_LOCATOR = By.cssSelector("p.block-editor-rich-text__editable[contenteditable='true']");
    private static final By NEW_PAGE_SAVE_DRAFT_BUTTON_LOCATOR = By.xpath("//button[contains(@class, 'editor-post-save-draft')][text()='Save draft']");
    private static final By NEW_PAGE_SAVED_BUTTON_LOCATOR = By.xpath("//button[contains(@class, 'is-saved')][text()='Saved']");
    private static final By VIEW_PAGES_BUTTON = By.xpath("//a[contains(@class, 'edit-post-fullscreen-mode-close')]");
    private static final By CHOOSE_PATTERN_POPUP_CLOSE_BUTTON_LOCATOR = By.xpath("//*[@class='components-modal__header']//button"); //same as in new post page
    private static final By PAGE_TAB_LOCATOR = By.xpath("//*[@id='tabs-0-edit-post/document'][text()='Page']");
    private static final By PAGE_STATUS_LINK_LOCATOR = By.xpath("//*[@class='editor-post-panel__row-label'][.='Status']/following-sibling::div//button");

    private static final By SAVE_BUTTON_LOCATOR = By.xpath("//*[@class='editor-header__settings']//button[.='Save']");


    private static Logger logger = Logger.getLogger(NewPagePage.class);

    public NewPagePage() {
        statusPopUp = new StatusPopUp();
    }


    @Override
    public boolean isPageOpened() {
        try {
            return Browser.waitForElementToBeClickableAndFind(PAGE_TAB_LOCATOR).isDisplayed();
        } catch (NotFoundException ex) {
            return false;
        }
    }

    public void closeChoosePatternPopUp() {
        try {
            WebElement closeButton = Browser.waitForElementToBeClickableAndFind(CHOOSE_PATTERN_POPUP_CLOSE_BUTTON_LOCATOR);
            closeButton.click();
            System.out.println("'Choose a pattern' pop-up was closed");
        } catch (RuntimeException ex) {
            System.out.println("'Choose a pattern' pop-up wasn't appear");
            logger.warn("'Choose a pattern' pop-up wasn't appear");
        }
    }

    public void addTitleAndText(String pageTitle) {
        WebElement frame = Browser.waitForVisibilityOfElementLocatedAndFind(NEW_PAGE_PAGE_IFRAME_LOCATOR); //можно упростить до //*[@name="editor-canvas"], //iframe тоже уникальный
        Browser.getWebDriver().switchTo().frame(frame);
        WebElement newPageTitleInputField = Browser.waitForElementToBeClickableAndFind(NEW_PAGE_TITLE_INPUT_FIELD_LOCATOR);
        newPageTitleInputField.sendKeys(pageTitle);

        WebElement newPageTextInputField = Browser.waitForElementToBeClickableAndFind(NEW_PAGE_TEXT_INPUT_FIELD_INITIAL_LOCATOR);
        newPageTextInputField.click();
        WebElement newPostTextInputField2 = Browser.waitForElementToBeClickableAndFind(NEW_PAGE_TEXT_INPUT_FIELD_FINAL_LOCATOR);
        newPostTextInputField2.click();
        newPostTextInputField2.sendKeys("KL NEW PAGE Text TEST");

        Browser.getWebDriver().switchTo().defaultContent();
    }

    public void saveDraft() {
        WebElement saveDraftButton = Browser.waitForElementToBeClickableAndFind(NEW_PAGE_SAVE_DRAFT_BUTTON_LOCATOR);
        saveDraftButton.click();
    }

    public boolean isSavedButtonDisplayed() {
        try {
            return Browser.waitForElementToBeClickableAndFind(NEW_PAGE_SAVED_BUTTON_LOCATOR).isDisplayed();
        } catch (NotFoundException ex) {
            return false;
        }
    }

    public void viewPagesButtonClick() {
        WebElement viewPostsButton = Browser.waitForElementToBeClickableAndFind(VIEW_PAGES_BUTTON);
        viewPostsButton.click();
    }

    public String getPageStatus() {
        WebElement pageStatusLink = Browser.waitForElementToBeClickableAndFind(PAGE_STATUS_LINK_LOCATOR);
        return pageStatusLink.getText().trim();
    }

    public void clickSaveButton() {
        WebElement saveButton = Browser.waitForElementToBeClickableAndFind(SAVE_BUTTON_LOCATOR);
        saveButton.click();
    }

    public void changePageStatus() throws InterruptedException {

        WebElement pageStatusLink = Browser.waitForElementToBeClickableAndFind(PAGE_STATUS_LINK_LOCATOR);
        pageStatusLink.click();
        statusPopUp.changeItemStatusTo(ItemStatusEnum.PENDING);
        clickSaveButton();
    }



}
