package com.itacademy.aqa.pages;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.elements.WordPressOnAzureDdl;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;


public class WordPressPostPage extends BaseWPPage {
    private WordPressOnAzureDdl wordPressOnAzureDdl;
    private static final By POST_TITLE_LOCATOR = By.xpath("//*[contains(@class,'wp-block-post-title')]");
    public static final By WORDPRESS_ON_AZURE_LINK_LOCATOR = By.className("wp-block-site-title");

    private static Logger logger = Logger.getLogger(WordPressPostPage.class);

    public WordPressPostPage() {
        wordPressOnAzureDdl = new WordPressOnAzureDdl();
    }


    @Override
    public boolean isPageOpened() {
        try {
            return Browser.waitForVisibilityOfElementLocatedAndFind(POST_TITLE_LOCATOR).isDisplayed();
        } catch (NotFoundException ex) {
            return false;
        }
    }

    public String getPostTitle(){
        WebElement postTitle = Browser.waitForVisibilityOfElementLocatedAndFind(POST_TITLE_LOCATOR);
        return  postTitle.getText().trim();
    }

    public void clickWordPressOnAzureLink(){
        WebElement wordPressOnAzureLink = Browser.waitForElementToBeClickableAndFind(WORDPRESS_ON_AZURE_LINK_LOCATOR);
        wordPressOnAzureLink.click();

    }


    public WordPressOnAzureDdl getWordPressOnAzureDdl() {
        return wordPressOnAzureDdl;
    }
}
