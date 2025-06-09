package com.itacademy.aqa.tests;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.config.Configuration;
import com.itacademy.aqa.enums.LeftMenuEnum;
import com.itacademy.aqa.pages.*;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WPCommentCanBeAdded11Test {
    private static Logger logger = Logger.getLogger(WPCommentCanBeAdded11Test.class);


    @BeforeMethod
    public void initialize() {
        Configuration.getProperties();
//        System.out.println(Configuration.getProperties().get("adminPassword"));
//        System.out.println(Configuration.getProperties().get("baseUrl"));
//        System.out.println(Configuration.getProperties().get("browser"));
//        System.out.println(System.getProperties().get("browser"));
//        System.out.println(System.getProperties().get("env"));
        Browser.initDriver();
        Browser.getWebDriver().get(Configuration.getBaseUrl());
    }


    //TC11
    @Test(groups = {"regression", "smoke"})
    @Description("Test11: Comment can added to published post")
    @Severity(SeverityLevel.NORMAL)
    public void commentCanBeAddedToPublishedPost() {

        String postTitle = "KL post for comments test";
        String commentTest = "KL comment to post";
        String commentatorName = "KL Commentator Name";
        String commentatorEmail = "klcomm11@test.com";

        logger.info("Starting test: Comment can added to published post");
        LoginPage loginPage = new LoginPage();
        loginPage.doLogin("Admin", Configuration.getProperties().getProperty("adminUserName"), Configuration.getProperties().getProperty("adminPassword")); //("test-admin", "&2agnh5MyevReS8jhoYDTtbt");

        DashboardPage dashboardPage = new DashboardPage();
        logger.info("Opening 'Posts' page");

        dashboardPage.getLeftMenu().clickOnItem(LeftMenuEnum.POSTS);

        PostsPage postsPage = new PostsPage();
        Assert.assertTrue(postsPage.isPageOpened(), "Posts page is not opened");
        postsPage.clickAddNewPostButton();

        NewPostPage newPostPage = new NewPostPage();
        Assert.assertTrue(newPostPage.isPageOpened(), "Add New Post page is not opened");

        newPostPage.addTitleAndText(postTitle);

        logger.info("Publishing a new post");
        newPostPage.publishPost();

        String pageUrl = newPostPage.takeNewPageUrl();
        newPostPage.viewPostsButtonClick();

        postsPage = new PostsPage();
        postsPage.getNameBar().clickLogOut();

        logger.info("Opening WordPress page with URL: " + pageUrl);
        Browser.getWebDriver().get(pageUrl);

        WordPressPostPagePage wordPressPostPagePage = new WordPressPostPagePage();
        Assert.assertTrue(wordPressPostPagePage.isPageOpened(), "Published page is opened");
        Assert.assertTrue(wordPressPostPagePage.commentsSectionDisplayed(), "Comments section title is not displayed");
        Assert.assertTrue(wordPressPostPagePage.commentFieldDisplayed(), "Comment field is not displayed");
        Assert.assertTrue(wordPressPostPagePage.nameFieldDisplayed(), "Name field is not displayed");
        Assert.assertTrue(wordPressPostPagePage.emailFieldDisplayed(), "Email field is not displayed");
        logger.info("Found 'Comments' section with 'Comment', 'Name', 'Email' fields");

        logger.info("Adding a comment to post");
        wordPressPostPagePage.addCommentToPost(commentTest, commentatorName, commentatorEmail);
        Browser.saveScreenShot();
        Browser.takeScreenShot();
        wordPressPostPagePage = new WordPressPostPagePage();

        System.out.println(wordPressPostPagePage.getCommentText());
        Assert.assertTrue(wordPressPostPagePage.getCommentText().contains(commentTest), "The comment is not displayed after submitting");
        logger.info("Added comment to the post");

        tearDown();
        initialize();

        logger.info("Logging in WP Admin site");
        loginPage = new LoginPage();
        loginPage.doLogin("Admin", Configuration.getProperties().getProperty("adminUserName"), Configuration.getProperties().getProperty("adminPassword")); //("test-admin", "&2agnh5MyevReS8jhoYDTtbt");

        dashboardPage = new DashboardPage();
        logger.info("Opening 'Comments' page");
        dashboardPage.getLeftMenu().clickOnItem(LeftMenuEnum.COMMENTS);

        CommentsPage commentsPage = new CommentsPage();
        Assert.assertTrue(commentsPage.isPageOpened(), "Comments page is not opened");
        Assert.assertTrue(commentsPage.getAllCommentsTitles().contains(commentTest));
        logger.info("Found added comment on 'Comments' page");

        commentsPage.deleteComment(commentTest);
        logger.info("Comment is deleted");

        commentsPage.getLeftMenu().clickOnItem(LeftMenuEnum.POSTS);
        postsPage = new PostsPage();
        postsPage.deletePost(postTitle);
        logger.info("Post is deleted");

        postsPage.getNameBar().clickLogOut();
        logger.info("User is logged out");

    }


    @AfterMethod
    public void tearDown() {
        Browser.close();

    }
}
