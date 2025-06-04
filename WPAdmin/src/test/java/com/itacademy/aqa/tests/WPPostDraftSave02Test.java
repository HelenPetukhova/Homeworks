package com.itacademy.aqa.tests;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.config.Configuration;
import com.itacademy.aqa.data.UserRoleLeftMenuData;
import com.itacademy.aqa.enums.LeftMenuEnum;
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

public class WPPostDraftSave02Test {
    private static Logger logger = Logger.getLogger(WPPostDraftSave02Test.class);


    @BeforeMethod
    public void initialize() {
        Configuration.getProperties();
        Browser.initDriver();
        Browser.getWebDriver().get(Configuration.getBaseUrl());
    }

    //TC02
    @Test(dataProvider = "userRoleCredentialsPostCreators", dataProviderClass = UserRoleLeftMenuData.class)
    @Description("Test02: Posts - Draft can be saved")
    @Severity(SeverityLevel.CRITICAL)
    public void savedDraftCanBeFindInPostsTableTest(String role, String userName, String password, String postTitle) {
        logger.info("Starting text that is checking that post draft can be saved");
        LoginPage loginPage = new LoginPage();
        loginPage.doLogin(role, userName, password); //("Admin","kladmin", "OZ%h*i5Bv*0w89%JgEugD$1V");
        logger.info(role + " user is logged in");


        PostsPage postsPage = new PostsPage();
        logger.info("Opening Posts page");
        postsPage.getLeftMenu().clickOnItem(LeftMenuEnum.POSTS);
        Assert.assertTrue(postsPage.isPageOpened(), "Posts page is not opened");

        logger.info("Adding a new post");
        postsPage.clickAddNewPostButton();

        NewPostPage newPostPage = new NewPostPage();
        logger.info("Closing 'Welcome to Editor' pop-up window if displayed");
        newPostPage.closeWelcomeToEditorPopUp();
        newPostPage.addTitleAndText(postTitle);
        logger.info("Created post with title: " + postTitle);

        logger.info("Saving post draft");
        newPostPage.saveDraft();
        Browser.saveScreenShot();
        Browser.takeScreenShot();
        logger.info("Post draft is saved");
        Assert.assertTrue(newPostPage.isSavedButtonDisplayed(), "Saved button is not displayed");
        logger.info("Opening Posts page with the list of posts");
        newPostPage.viewPostsButtonClick();
        Browser.saveScreenShot();
        Browser.takeScreenShot();

        postsPage = new PostsPage();
        String expectedPostTitle = postTitle + " — Draft";
        System.out.println("Actual posts list: " + postsPage.getAllPostsTitles());
        Assert.assertTrue(postsPage.getAllPostsTitles().contains(expectedPostTitle), "In All Posts table there is no title of saved draft with 'Draft' mark");
        logger.info("Found created post with title " + postTitle + " — Draft");

        logger.info("Deleting testing post");
        postsPage.deletePost(postTitle);
        logger.info("Logging out");
        postsPage.getNameBar().clickLogOut();
    }

    @AfterMethod
    public void tearDown() {
        Browser.close();

    }

}

