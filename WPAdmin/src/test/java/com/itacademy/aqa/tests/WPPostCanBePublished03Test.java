package com.itacademy.aqa.tests;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.config.Configuration;
import com.itacademy.aqa.elements.LeftMenuEnum;
import com.itacademy.aqa.elements.StatusFilterMenuEnum;
import com.itacademy.aqa.pages.DashboardPage;
import com.itacademy.aqa.pages.LoginPage;
import com.itacademy.aqa.pages.NewPostPage;
import com.itacademy.aqa.pages.PostsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WPPostCanBePublished03Test {
    private static Logger logger = Logger.getLogger(WPPostCanBePublished03Test.class);


    @BeforeMethod
    public void initialize() {
        Browser.initDriver();
        Browser.getWebDriver().get(Configuration.getBaseUrl());
    }


    @Test
    public void ContributorCanSendPostForReviewTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage();
        loginPage.doLogin("Contributor","klone", "C(17oLf9q)bRGC)4w&3Xkoin"); //("test-admin", "&2agnh5MyevReS8jhoYDTtbt");


        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.getLeftMenu().clickOnItem(LeftMenuEnum.POSTS);

        PostsPage postsPage = new PostsPage();
        Assert.assertTrue(postsPage.isPageOpened(), "Posts page is not opened");
        postsPage.clickAddNewPostButton();

        NewPostPage newPostPage = new NewPostPage();
        Assert.assertTrue(newPostPage.isPageOpened(), "Add New Post page is not opened");

        newPostPage.addTitleAndText("KL CONTRIBUTOR Post Title");

        newPostPage.submitPostForReview();
        Browser.saveScreenShot();
        Assert.assertEquals(newPostPage.getPostStatusInSettings(), "Pending");

        newPostPage.viewPostsButtonClick();
        postsPage = new PostsPage();
        postsPage.deletePost("KL CONTRIBUTOR Post Title");
        postsPage.getNameBar().clickLogOut();

    }

    //TC03
    @Test @Description("Test03: Editor can publish Contributor's post") @Severity(SeverityLevel.NORMAL)
    public void ContributorPostPublishedByEditorTest() throws InterruptedException {
        logger.info("Starting test to check that an Editor can publish Contributor's post");
        LoginPage loginPage = new LoginPage();
        loginPage.doLogin("Contributor","klone", "C(17oLf9q)bRGC)4w&3Xkoin"); //("test-admin", "&2agnh5MyevReS8jhoYDTtbt");
        logger.info("Contributor user logged in");

        DashboardPage dashboardPage = new DashboardPage();

        dashboardPage.getLeftMenu().clickOnItem(LeftMenuEnum.POSTS);

        PostsPage postsPage = new PostsPage();
        Assert.assertTrue(postsPage.isPageOpened(), "Posts page is not opened");
        postsPage.clickAddNewPostButton();

        NewPostPage newPostPage = new NewPostPage();
        Assert.assertTrue(newPostPage.isPageOpened(), "Add New Post page is not opened");

        newPostPage.addTitleAndText("KL CONTRIBUTOR Post Title");

        newPostPage.submitPostForReview();
        logger.info("Contributor user submitted new post for review");

        newPostPage.viewPostsButtonClick();

        postsPage = new PostsPage();
        postsPage.getNameBar().clickLogOut();
        logger.info("Contributor user logged out");

        tearDown();
        initialize();

        loginPage = new LoginPage();
        loginPage.doLogin("Editor","kleditor", "OIwJEqB8F1Xa4J@z@&5gN2AI");
        logger.info("Editor user logged in");


        dashboardPage = new DashboardPage();
        dashboardPage.getLeftMenu().clickOnItem(LeftMenuEnum.POSTS);

        postsPage = new PostsPage();
        String expectedSentForReviewPostTitle = "KL CONTRIBUTOR Post Title — Pending";

        System.out.println("Actual posts list: " + postsPage.getAllPostsTitles());
        System.out.println(expectedSentForReviewPostTitle);
        Assert.assertTrue(postsPage.getAllPostsTitles().contains(expectedSentForReviewPostTitle), "In All Posts table there is no title of the post or 'Pending' mark");
        logger.info("Editor user found Contributor's post");

        postsPage.clickPostTitle("KL CONTRIBUTOR Post Title");

        newPostPage = new NewPostPage();
        newPostPage.publishPost();
        logger.info("Editor user published Contributor's post");

        newPostPage.viewPostsButtonClick();

        postsPage = new PostsPage();
        String expectedPublishedPostTitle = "KL CONTRIBUTOR Post Title";
        System.out.println("Actual posts list: " + postsPage.getAllPostsTitles());
        Assert.assertTrue(postsPage.getAllPostsTitles().contains(expectedPublishedPostTitle), "In All Posts table there is no correct title of the post");
        logger.info("Editor user found published Contributor's post on Posts table without 'Pending' mark");

        postsPage.getStatusFilterMenu().clickOnItem(StatusFilterMenuEnum.PUBLISHED);
        postsPage = new PostsPage();
        System.out.println("Actual posts list: " + postsPage.getAllPostsTitles());
        Assert.assertTrue(postsPage.getAllPostsTitles().contains(expectedPublishedPostTitle), "In Published Posts table there is no correct title of published post");
        logger.info("Editor user found published Contributor's post on Posts table when 'Published' filter is selected");
    }




    @AfterMethod
    public void tearDown() {
        Browser.close();

    }

}

