package com.itacademy.aqa.pages;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.enums.ItemStatusEnum;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;


public class NewPagePage extends BaseEditorPage {


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
    private static final By PUBLISH_BUTTON_FIRST_LOCATOR = By.xpath("//*[@class='editor-header__settings']//*[contains(text(),'Publish')]");
    private static final By PUBLISH_BUTTON_SECOND_LOCATOR = By.xpath("//*[@class='editor-post-publish-panel']//*[contains(text(),'Publish')]");
    private static final By SAVE_BUTTON_LOCATOR = By.xpath("//*[@class='editor-header__settings']//button[.='Save']");
    private static final By SCHEDULE_BUTTON_LOCATOR = By.xpath("//*[@class='editor-header__settings']//button[.='Schedule']");
    private static final By SCHEDULE_BUTTON_SECOND_LOCATOR = By.className("editor-post-publish-panel__header-publish-button");
    private static final By POST_PUBLISH_PANEL_LOCATOR = By.className("post-publish-panel__postpublish");
    private static final By PAGE_ADDRESS_INPUT_FIELD_LOCATOR = By.className("components-text-control__input");
    private static final By PAGE_LINK_ON_PAGE_TAB_LOCATOR = By.xpath("//*[@class='editor-post-panel__row-label'][.='Link']/..//button");
    private static final By PAGE_LINK_ON_LINK_POPUP = By.xpath("//*[contains(@class,'components-base-control')]//*[@class='components-external-link__contents']");


    private static final Logger logger = Logger.getLogger(NewPagePage.class);

    public NewPagePage() {
        super();
    }


    @Override
    @Step("Check if page to create/edit new page is opened")
    public boolean isPageOpened() {
        try {
            logger.info("Finding Page tab on 'Page editor/creator' page");
            return Browser.waitForElementToBeClickableAndFind(PAGE_TAB_LOCATOR).isDisplayed();
        } catch (RuntimeException ex) {
            logger.error("'Page editor/creator' page is not opened");
            return false;
        }
    }

    @Step("Close 'Choose a Pattern' pop-up if appears")
    public void closeChoosePatternPopUp() {
        try {
            WebElement closeButton = Browser.waitForElementToBeClickableAndFind(CHOOSE_PATTERN_POPUP_CLOSE_BUTTON_LOCATOR);
            closeButton.click();
            logger.info("'Choose a pattern' pop-up was closed");
        } catch (RuntimeException ex) {
            logger.warn("'Choose a pattern' pop-up wasn't appear");
        }
    }


    @Step("Add a page with the title '{pageTitle}'")
    public void addTitleAndText(String pageTitle) {
        logger.info("Adding title and text on page");
        addTitleAndText(pageTitle, "PAGE", NEW_PAGE_PAGE_IFRAME_LOCATOR, NEW_PAGE_TITLE_INPUT_FIELD_LOCATOR, NEW_PAGE_TEXT_INPUT_FIELD_INITIAL_LOCATOR, NEW_PAGE_TEXT_INPUT_FIELD_FINAL_LOCATOR);
    }

    @Step("Click 'Save Draft' button")
    public void saveDraft() {
        logger.info("Saving draft of a new page");
        WebElement saveDraftButton = Browser.waitForElementToBeClickableAndFind(NEW_PAGE_SAVE_DRAFT_BUTTON_LOCATOR);
        saveDraftButton.click();
    }

    @Step("Check if 'Saved' is displayed instead of 'Save Draft' button")
    public boolean isSavedButtonDisplayed() {
        try {
            logger.info("Finding that 'Saved' is displayed instead of 'Save Draft' button");
            return Browser.waitForElementToBeClickableAndFind(NEW_PAGE_SAVED_BUTTON_LOCATOR).isDisplayed();
        } catch (NotFoundException ex) {
            logger.warn("'Saved' is not displayed instead of 'Save Draft' button");
            return false;
        }
    }

    @Step("Click 'View Pages' button")
    public void viewPagesButtonClick() {
        logger.info("Clicking 'View Pages' button");
        WebElement viewPostsButton = Browser.waitForElementToBeClickableAndFind(VIEW_PAGES_BUTTON);
        viewPostsButton.click();
    }

    @Step("Get status of page on 'Page' tab of side panel")
    public String getPageStatus() {
        logger.info("Getting status of page on 'Page Editor' page");
        WebElement pageStatusLink = Browser.waitForElementToBeClickableAndFind(PAGE_STATUS_LINK_LOCATOR);
        return pageStatusLink.getText().trim();
    }

    @Step("Click 'Save' button")
    public void clickSaveButton() {
        logger.info("Clicking 'Save' button");
        WebElement saveButton = Browser.waitForElementToBeClickableAndFind(SAVE_BUTTON_LOCATOR);
        saveButton.click();
    }

    @Step("Change page status throw status pop-up")
    public void changePageStatus(ItemStatusEnum itemStatusEnum) {
        logger.info("Changing page status throw status pop-up");
        WebElement pageStatusLink = Browser.waitForElementToBeClickableAndFind(PAGE_STATUS_LINK_LOCATOR);
        pageStatusLink.click();
        getStatusPopUp().changeItemStatusTo(itemStatusEnum);
        if (itemStatusEnum.equals(ItemStatusEnum.SCHEDULED)) {
            clickScheduleButton();
        } else if (itemStatusEnum.equals(ItemStatusEnum.PUBLISHED)) {
            clickPublishButton();
        } else {
            clickSaveButton();
        }
    }

    @Step("Click 'Schedule' button")
    private void clickScheduleButton() {
        logger.info("Clicking 'Schedule' button");
        WebElement scheduleButton = Browser.waitForElementToBeClickableAndFind(SCHEDULE_BUTTON_LOCATOR);
        scheduleButton.click();
        WebElement scheduleButtonSecond = Browser.waitForElementToBeClickableAndFind(SCHEDULE_BUTTON_SECOND_LOCATOR);
        scheduleButtonSecond.click();
        WebElement postPublishPanel = Browser.waitForElementToBeClickableAndFind(POST_PUBLISH_PANEL_LOCATOR);
    }

    @Step("Click 'Publish' button")
    public void clickPublishButton() {
        try {
            logger.info("Clicking 'Publish' button");
            WebElement publishButton1 = Browser.waitForElementToBeClickableAndFind(PUBLISH_BUTTON_FIRST_LOCATOR);
            publishButton1.click();
            WebElement publishButton2 = Browser.waitForElementToBeClickableAndFind(PUBLISH_BUTTON_SECOND_LOCATOR);
            publishButton2.click();
            WebElement postPublishPanel = Browser.waitForElementToBeClickableAndFind(POST_PUBLISH_PANEL_LOCATOR);
        } catch (RuntimeException ex) {
            logger.error("'Publish' button is not found", ex);
        }
    }

    public String takeNewPageUrl() {
        logger.info("Getting WordPress page URL");
        WebElement pageAddressField = Browser.waitForVisibilityOfElementLocatedAndFind(PAGE_ADDRESS_INPUT_FIELD_LOCATOR);
        return pageAddressField.getAttribute("value");
    }

    public void openPageTab() {
        logger.info("Opening 'Page' tab");
        WebElement pageTab = Browser.waitForElementToBeClickableAndFind(PAGE_TAB_LOCATOR);
        pageTab.click();
    }

    public void pageLinkOnPageTabClick() {
        logger.info("Clicking page link on 'Page' tab");
        WebElement pageLink = Browser.waitForElementToBeClickableAndFind(PAGE_LINK_ON_PAGE_TAB_LOCATOR);
        pageLink.click();
    }

    public String takeTextOfPageFromLinkPopup() {
        logger.info("Getting text of link");
        WebElement draftPageLink = Browser.waitForElementToBeClickableAndFind(PAGE_LINK_ON_LINK_POPUP);
        return draftPageLink.getText();
    }

    public String takePageUrlFromSidebar(){
        openPageTab();
        pageLinkOnPageTabClick();
        return takeTextOfPageFromLinkPopup();
    }
}

