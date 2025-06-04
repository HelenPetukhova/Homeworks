package com.itacademy.aqa.tests;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.config.Configuration;
import com.itacademy.aqa.enums.ActionsEnum;
import com.itacademy.aqa.enums.LeftMenuEnum;
import com.itacademy.aqa.enums.StatusFilterMenuEnum;
import com.itacademy.aqa.pages.*;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WPPostCanBeDeleted04Test {
    private static Logger logger = Logger.getLogger(WPPostCanBeDeleted04Test.class);


    @BeforeMethod
    public void initialize() {
        Configuration.getProperties();
        Browser.initDriver();
        Browser.getWebDriver().get(Configuration.getBaseUrl());
    }


    @Test
    @Description("Test04: Post can be deleted")
    @Severity(SeverityLevel.CRITICAL)
    public void PostCanBeDeletedTest(){
        logger.info("Starting test: Post can be deleted");
        LoginPage loginPage = new LoginPage();
        logger.info("Admin user is logging in");
        loginPage.doLogin("Admin", Configuration.getProperties().getProperty("adminUserName"),Configuration.getProperties().getProperty("adminPassword")); //admin, editor, author(only his post)

        logger.info("Opening 'Posts' page");
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.getLeftMenu().clickOnItem(LeftMenuEnum.POSTS);

        PostsPage postsPage = new PostsPage();
        Assert.assertTrue(postsPage.isPageOpened(), "Posts page is not opened");
        logger.info("Adding a new post");
        postsPage.clickAddNewPostButton();

        NewPostPage newPostPage = new NewPostPage();
        Assert.assertTrue(newPostPage.isPageOpened(), "Add New Post page is not opened");

        logger.info("Filling title and text of the post");
        newPostPage.addTitleAndText("KL Post to delete");
        logger.info("Publishing the post");
        newPostPage.publishPost();

        logger.info("Opening post on WordPress site");
        newPostPage.viewPostButtonCLick();

        WordPressPostPagePage wordPressPostPage = new WordPressPostPagePage();
        Assert.assertEquals(wordPressPostPage.getPostTitle(), "KL Post to delete", "The title of the post is not found");
        logger.info("Found the title of created WordPress Post page");

        logger.info("Opening Main WordPress page");
        wordPressPostPage.clickWordPressOnAzureLink();

        WordPressMainPage wordPressMainPage = new WordPressMainPage();
        Assert.assertTrue(wordPressMainPage.getAllPostsTitles().contains("KL Post to delete"), "New post is not displayed on WordPress main page");
        logger.info("Found the link of created WordPress Post on Main WordPress page");

        logger.info("Opening WP Admin page");
        wordPressMainPage.getWordPressOnAzureDdl().wordPressOnAzureDdlClick();

        logger.info("Opening WP Admin Posts page");
        dashboardPage = new DashboardPage();
        dashboardPage.getLeftMenu().clickOnItem(LeftMenuEnum.POSTS);

        logger.info("Moving post to Trash");
        postsPage = new PostsPage();
        postsPage.getActionsRow().clickOnItem("KL Post to delete", ActionsEnum.TRASH);

        wordPressMainPage = new WordPressMainPage();
        // Нужно уточнить: если пост отправить в trash, должно ли название поста показываться в Watch, Read, Listen
        // Сейчас отображается и сейчас assert этому соответствует, но это кажется неправильным поведением
        Assert.assertTrue(wordPressMainPage.getAllPostsTitles().contains("KL Post to delete"), "New post is not displayed on WordPress main page");
        logger.info("Found moved to trash post's link on WordPress Main page");

        logger.info("Opening WP Admin Posts page");
        wordPressMainPage.getWordPressOnAzureDdl().wordPressOnAzureDdlClick();

        dashboardPage = new DashboardPage();
        dashboardPage.getLeftMenu().clickOnItem(LeftMenuEnum.POSTS);

        logger.info("Filter posts by status: " + StatusFilterMenuEnum.TRASH);
        postsPage = new PostsPage();
        postsPage.getStatusFilterMenu().clickOnItem(StatusFilterMenuEnum.TRASH);

        logger.info("Permanently deleting the post");
        postsPage = new PostsPage();
        postsPage.getActionsRow().clickOnItem("KL Post to delete", ActionsEnum.DELETE_PERMANENTLY);

        wordPressMainPage = new WordPressMainPage();
        Assert.assertFalse(wordPressMainPage.getAllPostsTitles().contains("KL Post to delete"), "Deleted post is displayed on WordPress main page");
        logger.info("Deleted post's link is not displayed on the WordPress Main page");

        wordPressMainPage.getWordPressOnAzureDdl().wordPressOnAzureDdlClick();

        dashboardPage = new DashboardPage();
        dashboardPage.getNameBar().clickLogOut();
        logger.info("User logged out");

    }

    @AfterMethod
    public void tearDown() {
        Browser.close();

    }

}

