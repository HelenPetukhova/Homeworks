package com.itacademy.aqa.elements;

import com.itacademy.aqa.config.Browser;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WordPressOnAzureDdl {
    private static final By WORD_PRESS_AZURE_BUTTON = By.id("wp-admin-bar-site-name");
    private static Logger logger = Logger.getLogger(WordPressOnAzureDdl.class);

    public WordPressOnAzureDdl() {
    }

    public void wordPressOnAzureButtonClick(){
        WebElement wordPressOnAzureButton = Browser.waitForElementToBeClickableAndFind(WORD_PRESS_AZURE_BUTTON);
        wordPressOnAzureButton.click();
    }
    
}
