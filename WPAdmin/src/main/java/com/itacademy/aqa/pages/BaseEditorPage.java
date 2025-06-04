package com.itacademy.aqa.pages;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.elements.StatusPopUp;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public abstract class BaseEditorPage extends BaseWPPage{
    private static Logger logger = Logger.getLogger(BaseEditorPage.class);

    private StatusPopUp statusPopUp;


    protected BaseEditorPage() {
        super();
        this.statusPopUp = new StatusPopUp();

    }

    public StatusPopUp getStatusPopUp() {
        return statusPopUp;
    }


    public void addTitleAndText(String title, String contentType, By iframeLocator, By titleInputFieldLocator, By inputFieldInitialLocator, By inputFieldFinalLocator) {
        logger.info("Filling title in iframe");
        WebElement frame = Browser.waitForVisibilityOfElementLocatedAndFind(iframeLocator); //можно упростить до //*[@name="editor-canvas"], //iframe тоже уникальный
        Browser.getWebDriver().switchTo().frame(frame);
        WebElement newPostTitleInputField = Browser.waitForElementToBeClickableAndFind(titleInputFieldLocator);
        newPostTitleInputField.sendKeys(title);

        logger.info("Filling text in text block of iframe");
        WebElement newPostTextInputField = Browser.waitForElementToBeClickableAndFind(inputFieldInitialLocator);
        newPostTextInputField.click();
        WebElement newPostTextInputField2 = Browser.waitForElementToBeClickableAndFind(inputFieldFinalLocator);
        newPostTextInputField2.click();
        newPostTextInputField2.sendKeys("KL NEW " + contentType + " Text TEST");

        Browser.getWebDriver().switchTo().defaultContent();
    }
}
