package com.itacademy.aqa.tests;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.config.Configuration;
import com.itacademy.aqa.enums.LeftMenuEnum;
import com.itacademy.aqa.enums.StatusFilterMenuEnum;
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
    private static final Logger logger = Logger.getLogger(WPPostCanBePublished03Test.class);


    @BeforeMethod(groups = {"smoke", "regression"})
    public void initialize() {
        Configuration.getProperties();
        Browser.initDriver();
        Browser.getWebDriver().get(Configuration.getBaseUrl());
    }


    //TC03
    @Test(groups = {"smoke", "regression"})
    @Description("Test03: Editor can publish Contributor's post")
    @Severity(SeverityLevel.NORMAL)
    public void ContributorPostPublishedByEditorTest() {
        logger.info("Starting test: Editor can publish Contributor's post");

        LoginPage loginPage = new LoginPage();
        loginPage.doLogin("Contributor", Configuration.getProperties().getProperty("contributorUserName"), Configuration.getProperties().getProperty("contributorPassword")); //("test-admin", "&2agnh5MyevReS8jhoYDTtbt");

        logger.info("Contributor user logged in");
        logger.info("Opening 'Posts' page");

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.getLeftMenu().clickOnItem(LeftMenuEnum.POSTS);

        PostsPage postsPage = new PostsPage();
        Assert.assertTrue(postsPage.isPageOpened(), "Posts page is not opened");

        logger.info("Clicking 'Add New Post' button");

        postsPage.clickAddNewPostButton();

        NewPostPage newPostPage = new NewPostPage();
        Assert.assertTrue(newPostPage.isPageOpened(), "Add New Post page is not opened");

        logger.info("'New Post' page is opened");

        String postTitle = "KL CONTRIBUTOR Post Title";
        newPostPage.addTitleAndText(postTitle);

        logger.info("New post created: " + postTitle);

        newPostPage.submitPostForReview();

        logger.info("Contributor user submitted new post for review");
        Browser.takeScreenShot();

        newPostPage.viewPostsButtonClick();

        postsPage = new PostsPage();
        postsPage.getNameBar().clickLogOut();

        logger.info("Contributor user logged out");

        tearDown();
        initialize();

        logger.info("Editor user is logging in");

        loginPage = new LoginPage();
        loginPage.doLogin("Editor", Configuration.getProperties().getProperty("editorUserName"), Configuration.getProperties().getProperty("editorPassword"));

        logger.info("Editor user logged in");
        logger.info("Opening 'Posts' page");

        dashboardPage = new DashboardPage();
        dashboardPage.getLeftMenu().clickOnItem(LeftMenuEnum.POSTS);

        postsPage = new PostsPage();
        String expectedSentForReviewPostTitle = postTitle + " — Pending";

        System.out.println("Actual posts list: " + postsPage.getAllPostsTitles());
        System.out.println(expectedSentForReviewPostTitle);
        Browser.takeScreenShot();
        Assert.assertTrue(postsPage.getAllPostsTitles().contains(expectedSentForReviewPostTitle), "In All Posts table there is no title of the post or 'Pending' mark");

        logger.info("Editor user found Contributor's post");
        logger.info("Editor user is opening Contributor's post");

        postsPage.clickPostTitle(postTitle);

        newPostPage = new NewPostPage();
        newPostPage.publishPost();

        Browser.takeScreenShot();

        logger.info("Editor user published Contributor's post");

        newPostPage.viewPostsButtonClick();

        postsPage = new PostsPage();
        System.out.println("Actual posts list: " + postsPage.getAllPostsTitles());
        Browser.takeScreenShot();
        Assert.assertTrue(postsPage.getAllPostsTitles().contains(postTitle), "In All Posts table there is no correct title of the post");

        logger.info("Editor user found published Contributor's post on Posts table without 'Pending' mark");
        logger.info("Filtering all posts by status: " + StatusFilterMenuEnum.PUBLISHED);

        postsPage.getStatusFilterMenu().clickOnItem(StatusFilterMenuEnum.PUBLISHED);
        postsPage = new PostsPage();
        System.out.println("Actual posts list: " + postsPage.getAllPostsTitles());
        Browser.takeScreenShot();
        Assert.assertTrue(postsPage.getAllPostsTitles().contains(postTitle), "In Published Posts table there is no correct title of published post");

        logger.info("Editor user found published Contributor's post on Posts table when 'Published' filter is selected");

        postsPage.deletePost(postTitle);

        logger.info("Editor deleted the post");

        postsPage.getNameBar().clickLogOut();

        logger.info("Editor logged out");

    }

    @AfterMethod(groups = {"smoke", "regression"})
    public void tearDown() {
        Browser.close();

    }

}

