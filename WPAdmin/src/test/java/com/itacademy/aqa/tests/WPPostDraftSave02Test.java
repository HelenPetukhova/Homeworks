package com.itacademy.aqa.tests;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.config.Configuration;
import com.itacademy.aqa.elements.LeftMenuEnum;
import com.itacademy.aqa.pages.LoginPage;
import com.itacademy.aqa.pages.NewPostPage;
import com.itacademy.aqa.pages.PostsPage;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WPPostDraftSave02Test {
    private static Logger logger = Logger.getLogger(WPPostDraftSave02Test.class);


    @BeforeMethod
    public void initialize() {
        Browser.initDriver();
        Browser.getWebDriver().get(Configuration.getBaseUrl());
    }


//    @Test
//    public void allPostsPageCanBeOpenedTest() {
//        LoginPage loginPage = new LoginPage();
//        loginPage.doLogin("kltestuser", "BT905MYP)^3j2%zFxh@sc)kU"); //("test-admin", "&2agnh5MyevReS8jhoYDTtbt");
//
//        PostsPage postsPage = new PostsPage();
//        postsPage.getLeftMenu().clickOnItem(LeftMenuEnum.POSTS);
//
//        Assert.assertTrue(postsPage.isPageOpened(), "Posts page is not opened");
//    }
//
//
//    @Test
//    public void AddNewPostsButtonOpensNewPostPageTest() {
//        LoginPage loginPage = new LoginPage();
//        loginPage.doLogin("kltestuser", "BT905MYP)^3j2%zFxh@sc)kU"); //("test-admin", "&2agnh5MyevReS8jhoYDTtbt");
//
//        PostsPage postsPage = new PostsPage();
//        postsPage.getLeftMenu().clickOnItem(LeftMenuEnum.POSTS);
//        postsPage.clickAddNewPostButton();
//
//        NewPostPage newPostPage = new NewPostPage();
//        Assert.assertTrue(newPostPage.isPageOpened(), "Add New Post page is not opened");
//    }
//
//
//    @Test
//    public void addTitleForPostAndSaveDraftTest() throws InterruptedException {
//        LoginPage loginPage = new LoginPage();
//        loginPage.doLogin("kltestuser", "BT905MYP)^3j2%zFxh@sc)kU"); //("test-admin", "&2agnh5MyevReS8jhoYDTtbt");
//
//        PostsPage postsPage = new PostsPage();
//        postsPage.getLeftMenu().clickOnItem(LeftMenuEnum.POSTS);
//        postsPage.clickAddNewPostButton();
//
//        NewPostPage newPostPage = new NewPostPage();
//        newPostPage.addTitleAndText();
//
//        newPostPage.saveDraft();
//        Assert.assertTrue(newPostPage.isSavedButtonDisplayed(), "Saved button is not displayed");
//    }
//
//
//    @Test
//    public void savedDraftIsDisplayedOnPostsPageTest() throws InterruptedException {
//        LoginPage loginPage = new LoginPage();
//        loginPage.doLogin("kltestuser", "BT905MYP)^3j2%zFxh@sc)kU"); //("test-admin", "&2agnh5MyevReS8jhoYDTtbt");
//
//        PostsPage postsPage = new PostsPage();
//        postsPage.getLeftMenu().clickOnItem(LeftMenuEnum.POSTS);
//        postsPage.clickAddNewPostButton();
//
//        NewPostPage newPostPage = new NewPostPage();
//        newPostPage.addTitleAndText();
//
//        newPostPage.saveDraft();
//        newPostPage.viewPostButtonClick();
//
//
//        String expectedPostTitle = "KL NEW POST TITLE TEST — Draft";
//        System.out.println("Actual posts list: " + postsPage.getAllPostsTitles());
//
//        Assert.assertTrue(postsPage.getAllPostsTitles().contains(expectedPostTitle), "In All Posts table there is no title of saved draft with 'Draft' mark");
//    }
//
//
//    //savedDraftIsDisplayedOnPostsPageTest() + delete the drafts

    //TC02
    @Test
    public void savedDraftCanBeFindInPostsTableTest() {  // можно сделать data driven и передавать логин/пароль, текст названия и текст поста
        LoginPage loginPage = new LoginPage();
        loginPage.doLogin("Admin","kladmin", "OZ%h*i5Bv*0w89%JgEugD$1V"); //("test-admin", "&2agnh5MyevReS8jhoYDTtbt");

        PostsPage postsPage = new PostsPage();
        postsPage.getLeftMenu().clickOnItem(LeftMenuEnum.POSTS);
        postsPage.clickAddNewPostButton();

        NewPostPage newPostPage = new NewPostPage();
        newPostPage.closeWelcomeToEditorPopUp();
        newPostPage.addTitleAndText();

        newPostPage.saveDraft();
        Browser.saveScreenShot();
        newPostPage.viewPostButtonClick();
        Browser.saveScreenShot();


        postsPage = new PostsPage();
        String expectedPostTitle = "KL NEW POST TITLE TEST — Draft";
        System.out.println("Actual posts list: " + postsPage.getAllPostsTitles());
        Assert.assertTrue(postsPage.getAllPostsTitles().contains(expectedPostTitle), "In All Posts table there is no title of saved draft with 'Draft' mark");

        postsPage.deletePost("KL NEW POST TITLE TEST");
        postsPage.getNameBar().clickLogOut();

    }








    @AfterMethod
    public void tearDown() {
        Browser.close();

    }

}

