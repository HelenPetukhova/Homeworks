package com.itacademy.aqa.pages;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.elements.NameBar;
import com.itacademy.aqa.elements.WordPressOnAzureDdl;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class WordPressMainPage  extends BaseWPPage {
    private WordPressOnAzureDdl wordPressOnAzureDdl;
    private NameBar nameBar;
    private static final By WATCH_READ_LISTEN_SECTION_LOCATOR = By.xpath("//*[@class][.='Watch, Read, Listen']");
    private static final By ALL_POSTS_TITLES_LOCATOR = By.className("wp-block-post-title");
    private static final By PAGES_LINKS_LOCATOR = By.xpath("//*[contains(@class, 'wp-block-pages-list__item__link')]");


    private static Logger logger = Logger.getLogger(WordPressMainPage.class);

    public WordPressMainPage() {
        wordPressOnAzureDdl = new WordPressOnAzureDdl();
        nameBar = new NameBar();
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
        logger.info("Getting all post titles displayed on Word Press Main page");
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


    public List<String> getPagesLinks(){
        logger.info("Getting all pages links on WordPress Main page");
        List<WebElement> pagesLinks = Browser.waitForPresenceOfAllElementsAndFind(PAGES_LINKS_LOCATOR);
        List<String> pagesTitles = new ArrayList<>();

        for(WebElement pageLink : pagesLinks){
            pagesTitles.add(pageLink.getText().trim());
        }
        return  pagesTitles;
    }

    public NameBar getNameBar() {
        return nameBar;
    }


//    public void clickWordPressOnAzureLink() {
//        WebElement wordPressOnAzureLink = Browser.waitForElementToBeClickableAndFind(WORDPRESS_ON_AZURE_LINK_LOCATOR);
//        wordPressOnAzureLink.click();
//
//    }




}