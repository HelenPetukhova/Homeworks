package com.itacademy.aqa.tests;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.config.Configuration;
import com.itacademy.aqa.elements.ActionsEnum;
import com.itacademy.aqa.elements.LeftMenuEnum;
import com.itacademy.aqa.elements.StatusFilterMenuEnum;
import com.itacademy.aqa.pages.*;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WPPostCanBeDeleted04Test {
    private static Logger logger = Logger.getLogger(WPPostCanBeDeleted04Test.class);


    @BeforeMethod
    public void initialize() {
        Browser.initDriver();
        Browser.getWebDriver().get(Configuration.getBaseUrl());
    }


    @Test
    public void PostCanBeDeletedTest(){
        LoginPage loginPage = new LoginPage();
        loginPage.doLogin("Admin", "kladmin", "OZ%h*i5Bv*0w89%JgEugD$1V"); //admin, editor, author(only his post)


        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.getLeftMenu().clickOnItem(LeftMenuEnum.POSTS);

        PostsPage postsPage = new PostsPage();
        Assert.assertTrue(postsPage.isPageOpened(), "Posts page is not opened");
        postsPage.clickAddNewPostButton();

        NewPostPage newPostPage = new NewPostPage();
        Assert.assertTrue(newPostPage.isPageOpened(), "Add New Post page is not opened");

        newPostPage.addTitleAndText("KL Post to delete");
        newPostPage.publishPost();
        newPostPage.viewPostButtonCLick();

        WordPressPostPage wordPressPostPage = new WordPressPostPage();
        Assert.assertEquals(wordPressPostPage.getPostTitle(), "KL Post to delete", "The title of the post is not found");

        wordPressPostPage.clickWordPressOnAzureLink();

        WordPressMainPage wordPressMainPage = new WordPressMainPage();
        Assert.assertTrue(wordPressMainPage.getAllPostsTitles().contains("KL Post to delete"), "New post is not displayed on WordPress main page");
        wordPressMainPage.getWordPressOnAzureDdl().wordPressOnAzureButtonClick();

        dashboardPage = new DashboardPage();
        dashboardPage.getLeftMenu().clickOnItem(LeftMenuEnum.POSTS);

        postsPage = new PostsPage();
        postsPage.getActionsRow().clickOnItem("KL Post to delete", ActionsEnum.TRASH);

        wordPressMainPage = new WordPressMainPage();
        // Нужно уточнить: если пост отправить в trash, должно ли название поста показываться в Watch, Read, Listen
        // Сейчас отображается и сейчас assert этому соответствует, но это кажется неправильным поведением
        Assert.assertTrue(wordPressMainPage.getAllPostsTitles().contains("KL Post to delete"), "New post is not displayed on WordPress main page");
        wordPressMainPage.getWordPressOnAzureDdl().wordPressOnAzureButtonClick();

        dashboardPage = new DashboardPage();
        dashboardPage.getLeftMenu().clickOnItem(LeftMenuEnum.POSTS);

        postsPage = new PostsPage();
        postsPage.getStatusFilterMenu().clickOnItem(StatusFilterMenuEnum.TRASH);

        postsPage = new PostsPage();
        postsPage.getActionsRow().clickOnItem("KL Post to delete", ActionsEnum.DELETE_PERMANENTLY);

        wordPressMainPage = new WordPressMainPage();
        Assert.assertFalse(wordPressMainPage.getAllPostsTitles().contains("KL Post to delete"), "Deleted post is displayed on WordPress main page");
        wordPressMainPage.getWordPressOnAzureDdl().wordPressOnAzureButtonClick();

        dashboardPage = new DashboardPage();
        dashboardPage.getNameBar().clickLogOut();

    }





    @AfterMethod
    public void tearDown() {
        Browser.close();

    }

}

