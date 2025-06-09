package com.itacademy.aqa.tests;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.config.Configuration;
import com.itacademy.aqa.enums.ActionsEnum;
import com.itacademy.aqa.enums.BulkActionsEnum;
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

public class WPCommentCanBeDeleted13Test {
    private static final Logger logger = Logger.getLogger(WPCommentCanBeDeleted13Test.class);

    @BeforeMethod
    public void initialize() {
        Configuration.getProperties();
        System.out.println(Configuration.getProperties().get("adminPassword"));
        System.out.println(Configuration.getProperties().get("baseUrl"));
        System.out.println(Configuration.getProperties().get("browser"));
        System.out.println(System.getProperties().get("browser"));
        System.out.println(System.getProperties().get("env"));
        Browser.initDriver();
        Browser.getWebDriver().get(Configuration.getBaseUrl());
    }


    @Test(groups = {"regression"})
    @Description("Test13: Comment can be moved to Trash")
    @Severity(SeverityLevel.NORMAL)
    public void CommentCanBeMovedToTrashTest() {
        String commentText = "KL comment to be approved and moved to trash";
        logger.info("Starting test: Comment can be moved to Trash");
        logger.info("Logging in as admin");
        LoginPage loginPage = new LoginPage();
        loginPage.doLogin("Admin", Configuration.getProperties().getProperty("adminUserName"), Configuration.getProperties().getProperty("adminPassword")); //("test-admin", "&2agnh5MyevReS8jhoYDTtbt");

        DashboardPage dashboardPage = new DashboardPage();
        logger.info("Opening 'Posts' page");
        dashboardPage.getLeftMenu().clickOnItem(LeftMenuEnum.POSTS);

        PostsPage postsPage = new PostsPage();
        Assert.assertTrue(postsPage.isPageOpened(), "Posts page is not opened");

        logger.info("Adding a new post");
        postsPage.clickAddNewPostButton();

        NewPostPage newPostPage = new NewPostPage();
        Assert.assertTrue(newPostPage.isPageOpened(), "Add New Post page is not opened");

        newPostPage.addTitleAndText(commentText);
        logger.info("Publishing the post");
        newPostPage.publishPost();

        String pageUrl = newPostPage.takeNewPageUrl();
        newPostPage.viewPostsButtonClick();

        postsPage = new PostsPage();
        postsPage.getNameBar().clickLogOut();

        logger.info("Opening publish post on Word Press using URL: " + pageUrl);
        Browser.getWebDriver().get(pageUrl);

        WordPressPostPagePage wordPressPostPagePage = new WordPressPostPagePage();
        Assert.assertTrue(wordPressPostPagePage.isPageOpened(), "Published page is opened");

        logger.info("Adding a comment to published post");
        wordPressPostPagePage.addCommentToPost(commentText, "KL Commentator Approved Name", "klcommapp@test.com");

        wordPressPostPagePage = new WordPressPostPagePage();
        System.out.println(wordPressPostPagePage.getCommentText());
        Assert.assertTrue(wordPressPostPagePage.getCommentText().contains(commentText), "The comment is not displayed after submitting");


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

        logger.info("Approve the comment");
        commentsPage.getCommentsActionsRow().clickOnItem(commentText, ActionsEnum.APPROVE);

        commentsPage = new CommentsPage();
        Assert.assertTrue(commentsPage.getCommentsActionsRow().isActionOptionAvailable(commentText, ActionsEnum.UNAPPROVE), "'Approve' link is not changed to 'Unapprove' action link");

        commentsPage.getNameBar().clickLogOut();


        logger.info("Opening publish post on Word Press using URL: " + pageUrl);
        Browser.getWebDriver().get(pageUrl);

        wordPressPostPagePage = new WordPressPostPagePage();
        Browser.saveScreenShot();
        Browser.takeScreenShot();
        Assert.assertTrue(wordPressPostPagePage.getCommentText().contains(commentText));
        Assert.assertTrue(wordPressPostPagePage.getCommentAuthorByCommentText(commentText).contains("KL Commentator Approved Name"));
        logger.info("Found approved comment on Word Press Post page");

        tearDown();
        initialize();

        logger.info("Logging in WP Admin site");
        loginPage = new LoginPage();
        loginPage.doLogin("Admin", Configuration.getProperties().getProperty("adminUserName"), Configuration.getProperties().getProperty("adminPassword")); //("test-admin", "&2agnh5MyevReS8jhoYDTtbt");

        dashboardPage = new DashboardPage();

        logger.info("Opening 'Comments' page");
        dashboardPage.getLeftMenu().clickOnItem(LeftMenuEnum.COMMENTS);

        commentsPage = new CommentsPage();
        logger.info("Moving the comment to Trash");
        commentsPage.getCommentsActionsRow().clickOnItem(commentText, ActionsEnum.TRASH);
        commentsPage.getStatusFilterMenu().clickOnItem(StatusFilterMenuEnum.TRASH);
        commentsPage = new CommentsPage();
        Assert.assertTrue(commentsPage.getAllCommentsTitles().contains(commentText));
        commentsPage.getNameBar().clickLogOut();

        logger.info("Opening publish post on Word Press using URL: " + pageUrl);
        Browser.getWebDriver().get(pageUrl);
        wordPressPostPagePage = new WordPressPostPagePage();
        Browser.saveScreenShot();
        Browser.takeScreenShot();
        Assert.assertFalse(wordPressPostPagePage.isCommentDisplayed(commentText), "Moved to Trash comment is still displayed");
        logger.info("The comment that is moved to trash is not found on Word Press Post page");


        tearDown();
        initialize();

        logger.info("Logging in WP Admin site");
        loginPage = new LoginPage();
        loginPage.doLogin("Admin", Configuration.getProperties().getProperty("adminUserName"), Configuration.getProperties().getProperty("adminPassword")); //("test-admin", "&2agnh5MyevReS8jhoYDTtbt");

        dashboardPage = new DashboardPage();
        logger.info("Deleting the comment");
        dashboardPage.getLeftMenu().clickOnItem(LeftMenuEnum.COMMENTS);

        commentsPage = new CommentsPage();
        commentsPage.getStatusFilterMenu().clickOnItem(StatusFilterMenuEnum.TRASH);
        commentsPage.checkOffComment(commentText);
        commentsPage.getBulkActionsDdl().selectBulkAction(BulkActionsEnum.DELETE_PERMANENTLY);
        commentsPage.clickApplyButton();

        logger.info("Deleting the post");

        commentsPage.getLeftMenu().clickOnItem(LeftMenuEnum.POSTS);
        postsPage = new PostsPage();
        postsPage.deletePost("KL post for comment deleting test");
        Browser.saveScreenShot();
        Browser.takeScreenShot();
        postsPage.getNameBar().clickLogOut();
        logger.info("User is logged out");

    }


    @AfterMethod
    public void tearDown() {
        Browser.close();

    }

}

