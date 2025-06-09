package com.itacademy.aqa.elements;

import com.itacademy.aqa.config.Browser;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WordPressOnAzureDdl {
    private static final By WORD_PRESS_AZURE_BUTTON = By.id("wp-admin-bar-site-name");
    private static final Logger logger = Logger.getLogger(WordPressOnAzureDdl.class);

    public WordPressOnAzureDdl() {
    }

    @Step("Click 'WordPress On Azure' option of 'WordPress On Azure' ddl")
    public void wordPressOnAzureDdlClick(){
        logger.info("Clicking on 'WordPress On Azure' option of 'WordPress On Azure' ddl");
        WebElement wordPressOnAzureButton = Browser.waitForElementToBeClickableAndFind(WORD_PRESS_AZURE_BUTTON);
        wordPressOnAzureButton.click();
    }
    
}
