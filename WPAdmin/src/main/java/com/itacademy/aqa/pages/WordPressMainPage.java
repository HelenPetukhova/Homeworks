package com.itacademy.aqa.pages;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.elements.NameBar;
import com.itacademy.aqa.elements.WordPressOnAzureDdl;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class WordPressMainPage  extends BaseWPPage {
    private final WordPressOnAzureDdl wordPressOnAzureDdl;
    private final NameBar nameBar;
    private static final By WATCH_READ_LISTEN_SECTION_LOCATOR = By.xpath("//*[@class][.='Watch, Read, Listen']");
    private static final By ALL_POSTS_TITLES_LOCATOR = By.className("wp-block-post-title");
    private static final By PAGES_LINKS_LOCATOR = By.xpath("//*[contains(@class, 'wp-block-pages-list__item__link')]");


    private static final Logger logger = Logger.getLogger(WordPressMainPage.class);

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

    @Step("Get all posts title links on Word Press Main page")
    public List<String> getAllPostsTitles() {
        logger.info("Getting all post titles displayed on Word Press Main page");
        List<WebElement> allPostsTitlesElements = Browser.waitForPresenceOfAllElementsAndFind(ALL_POSTS_TITLES_LOCATOR);
        List<String> allPostsTitles = new ArrayList<>();

        for (WebElement allPostsTitlesElement : allPostsTitlesElements) {
            String postTitle = allPostsTitlesElement.getText().trim();
            allPostsTitles.add(postTitle);
        }
        return allPostsTitles;
    }


    public WordPressOnAzureDdl getWordPressOnAzureDdl() {
        return wordPressOnAzureDdl;
    }

    @Step("Get all pages links on WordPress Main page")
    public List<String> getPagesLinks() {
        logger.info("Getting all pages links on WordPress Main page");
        List<WebElement> pagesLinks = Browser.waitForPresenceOfAllElementsAndFind(PAGES_LINKS_LOCATOR);
        List<String> pagesTitles = new ArrayList<>();

        for (WebElement pageLink : pagesLinks) {
            pagesTitles.add(pageLink.getText().trim());
        }
        return pagesTitles;
    }

    public NameBar getNameBar() {
        return nameBar;
    }



}