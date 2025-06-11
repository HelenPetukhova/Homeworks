package com.itacademy.aqa.tests;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.config.Configuration;
import com.itacademy.aqa.enums.ActionsEnum;
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

public class WPCommentCanBeApproved12Test {
    private static final String COMMENT_TEXT = "KL comment to be approved";

    private static Logger logger = Logger.getLogger(WPCommentCanBeApproved12Test.class);

    @BeforeMethod(groups = {"regression", "smoke"})
    public void initialize() {
        Configuration.getProperties();
        Browser.initDriver();
        Browser.getWebDriver().get(Configuration.getBaseUrl());
    }


    //TC12
    @Test(groups = {"regression"})
    @Description("Test12: Row color on Comments page is changed after comment is approved")
    @Severity(SeverityLevel.NORMAL)
    public void commentRowColorIsChangedWhenCommentIsApprovedTest() {
        logger.info("Starting test: Row color on Comments page is changed after comment is approved");

        logger.info("Logging in as an Admin");
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

        newPostPage.addTitleAndText("KL post for comment status test");

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

        logger.info("Adding a comment to post");

        wordPressPostPagePage.addCommentToPost(COMMENT_TEXT, "KL Commentator Approved Name", "klcommapp@test.com");
        Browser.takeScreenShot();
        wordPressPostPagePage = new WordPressPostPagePage();
        System.out.println(wordPressPostPagePage.getCommentText());
        Assert.assertTrue(wordPressPostPagePage.getCommentText().contains(COMMENT_TEXT), "The comment is not displayed after submitting");
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
        Assert.assertTrue(commentsPage.getAllCommentsTitles().contains(COMMENT_TEXT), "'KL comment to be approved' is not displayed on Comments page");

        Browser.takeScreenShot();

        logger.info("Taking a background color of the row with not approved comment and left border color");
        commentsPage.takeRowColor(COMMENT_TEXT);
        System.out.println(commentsPage.takeRowColor(COMMENT_TEXT));
        System.out.println(commentsPage.takeLeftBorderColor(COMMENT_TEXT));
        Assert.assertTrue(commentsPage.takeRowColor(COMMENT_TEXT).contains("252, 249, 232, 1"), "Wrong background color for not approved comment");
        Assert.assertTrue(commentsPage.takeLeftBorderColor(COMMENT_TEXT).contains("214, 54, 56, 1"), "Left border of row with unapproved comment has wrong color");

        logger.info("Approving the comment");
        commentsPage.getCommentsActionsRow().clickOnItem(COMMENT_TEXT, ActionsEnum.APPROVE);

        commentsPage = new CommentsPage();
        Browser.takeScreenShot();
        Assert.assertTrue(commentsPage.getCommentsActionsRow().isActionOptionAvailable("KL comment to be approved", ActionsEnum.UNAPPROVE), "'Approve' link is not changed to 'Unapprove' action link");
        System.out.println(commentsPage.takeRowColor(COMMENT_TEXT));
        System.out.println(commentsPage.takeLeftBorderColor(COMMENT_TEXT));
        Assert.assertTrue(commentsPage.takeRowColor(COMMENT_TEXT).contains("0, 0, 0, 0"), "Wrong background color for approved comment");
        Assert.assertTrue(commentsPage.takeLeftBorderColor(COMMENT_TEXT).contains("80, 87, 94, 1"), "Left border is not displayed for approved comment");
        logger.info("Found that background color of the comment is changed to white after approval");

        logger.info("Deleting comment");
        commentsPage.deleteComment(COMMENT_TEXT);
        commentsPage.getLeftMenu().clickOnItem(LeftMenuEnum.POSTS);
        postsPage = new PostsPage();
        logger.info("Deleting posts");
        postsPage.deletePost("KL post for comment status test");
        postsPage.getNameBar().clickLogOut();
        logger.info("User is logged out");
    }


    @Test(groups = {"regression", "smoke"})
    @Description("Test12-2: Approved comment is displayed on WP site")
    @Severity(SeverityLevel.NORMAL)
    public void approvedCommentIsDisplayedOnWPPostPageTest() {
        logger.info("Starting test: Approved comment is displayed on WP site");
        logger.info("Logging in as an Admin");

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

        newPostPage.addTitleAndText("KL post for comment status test");

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

        logger.info("Adding a comment to post");

        wordPressPostPagePage.addCommentToPost(COMMENT_TEXT, "KL Commentator Approved Name", "klcommapp@test.com");

        wordPressPostPagePage = new WordPressPostPagePage();
        Browser.takeScreenShot();
        System.out.println(wordPressPostPagePage.getCommentText());
        Assert.assertTrue(wordPressPostPagePage.getCommentText().contains(COMMENT_TEXT), "The comment is not displayed after submitting");


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
        Assert.assertTrue(commentsPage.getAllCommentsTitles().contains(COMMENT_TEXT), "'KL comment to be approved' is not displayed on Comments page");
        Browser.takeScreenShot();

        logger.info("Approving the comment");

        commentsPage.getCommentsActionsRow().clickOnItem(COMMENT_TEXT, ActionsEnum.APPROVE);

        commentsPage = new CommentsPage();
        Browser.takeScreenShot();
        Assert.assertTrue(commentsPage.getCommentsActionsRow().isActionOptionAvailable(COMMENT_TEXT, ActionsEnum.UNAPPROVE), "'Approve' link is not changed to 'Unapprove' action link");

        commentsPage.getNameBar().clickLogOut();

        logger.info("Opening published post with URL: " + pageUrl);
        Browser.getWebDriver().get(pageUrl);

        wordPressPostPagePage = new WordPressPostPagePage();
        Assert.assertEquals(wordPressPostPagePage.getCommentText(), COMMENT_TEXT);
        Assert.assertTrue(wordPressPostPagePage.getCommentAuthorByCommentText(COMMENT_TEXT).contains("KL Commentator Approved Name"));
        Browser.takeScreenShot();
        logger.info("Approved comment is displayed under published post");

        tearDown();
        initialize();

        logger.info("Logging in WP Admin site");

        loginPage = new LoginPage();
        loginPage.doLogin("Admin", Configuration.getProperties().getProperty("adminUserName"), Configuration.getProperties().getProperty("adminPassword")); //("test-admin", "&2agnh5MyevReS8jhoYDTtbt");

        dashboardPage = new DashboardPage();

        logger.info("Opening 'Comments' page");

        dashboardPage.getLeftMenu().clickOnItem(LeftMenuEnum.COMMENTS);

        logger.info("Deleting comment");

        commentsPage.deleteComment(COMMENT_TEXT);
        commentsPage.getLeftMenu().clickOnItem(LeftMenuEnum.POSTS);
        postsPage = new PostsPage();

        logger.info("Deleting posts");
        postsPage.deletePost("KL post for comment status test");
        postsPage.getNameBar().clickLogOut();
        logger.info("User is logged out");

    }


    @AfterMethod(groups = {"regression", "smoke"})
    public void tearDown() {

        Browser.close();

    }

}
