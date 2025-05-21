package com.itacademy.aqa.pages;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.elements.WordPressOnAzureDdl;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class WordPressMainPage extends BaseWPPage {
    private WordPressOnAzureDdl wordPressOnAzureDdl;
    private static final By WATCH_READ_LISTEN_SECTION_LOCATOR = By.xpath("//*[@class][.='Watch, Read, Listen']");
    private static final By ALL_POSTS_TITLES_LOCATOR = By.className("wp-block-post-title");


    private static Logger logger = Logger.getLogger(WordPressMainPage.class);

    public WordPressMainPage() {
        wordPressOnAzureDdl = new WordPressOnAzureDdl();

    }


    @Override
    public boolean isPageOpened() {
        try {
            return Browser.waitForVisibilityOfElementLocatedAndFind(WATCH_READ_LISTEN_SECTION_LOCATOR).isDisplayed();
        } catch (NotFoundException ex) {
            return false;
        }
    }

    public List<String> getAllPostsTitles() {
        List<WebElement> allPostsTitlesElements = Browser.waitForPresenceOfAllElementsAndFind(ALL_POSTS_TITLES_LOCATOR);
        List<String> allPostsTitles = new ArrayList<>();

        for (WebElement allPostsTitlesElement : allPostsTitlesElements){
            String postTitle = allPostsTitlesElement.getText().trim();
            allPostsTitles.add(postTitle);
        }
        return allPostsTitles;
    }


    public WordPressOnAzureDdl getWordPressOnAzureDdl() {
        return wordPressOnAzureDdl;
    }


//    public void clickWordPressOnAzureLink() {
//        WebElement wordPressOnAzureLink = Browser.waitForElementToBeClickableAndFind(WORDPRESS_ON_AZURE_LINK_LOCATOR);
//        wordPressOnAzureLink.click();
//
//    }


}
