package com.itacademy.aqa.tests;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.config.Configuration;
import com.itacademy.aqa.data.UserRoleLeftMenuData;
import com.itacademy.aqa.enums.ItemStatusEnum;
import com.itacademy.aqa.enums.LeftMenuEnum;
import com.itacademy.aqa.pages.*;
import com.itacademy.aqa.utils.ApiCheckUrl;
import com.itacademy.aqa.utils.RandomUtil;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class WPPublishedPageAvailableOnSite10Test {
    private static Logger logger = Logger.getLogger(WPPublishedPageAvailableOnSite10Test.class);


    @BeforeMethod(groups = {"regression", "smoke"})
    public void initialize() {
        Configuration.getProperties();
        Browser.initDriver();
        Browser.getWebDriver().get(Configuration.getBaseUrl());
    }


    @Test(dataProvider = "userRoleCredentialsPageCreators", dataProviderClass = UserRoleLeftMenuData.class, groups = {"regression", "smoke"})
    @Description("Test10: Published page is available on WP site and not available after deleting")
    @Severity(SeverityLevel.CRITICAL)

    public void publishedPageIsDisplayedOnWPHomePageTest(String role, String userName, String password, String pageTitle) {
        LoginPage loginPage = new LoginPage();
        loginPage.doLogin(role, userName, password);

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.getLeftMenu().clickOnItem(LeftMenuEnum.PAGES);

        PagesPage pagesPage = new PagesPage();
        pagesPage.addNewPageButtonClick();

        NewPagePage newPagePage = new NewPagePage();
        newPagePage.closeChoosePatternPopUp();
        newPagePage.addTitleAndText(pageTitle);

        logger.info("Publishing a new page");
        newPagePage.clickPublishButton();
        Browser.takeScreenShot();
        Browser.saveScreenShot();
        String pageUrl = newPagePage.takeNewPageUrl();

        logger.info("Opening 'Pages' page");
        newPagePage.viewPagesButtonClick();

        pagesPage = new PagesPage();
        pagesPage.getNameBar().clickLogOut();

        logger.info("The user is logged out");
        logger.info("Opening published page on WordPress using: " + pageUrl);

        Browser.getWebDriver().get(pageUrl);
        WordPressPostPagePage wordPressPostPage = new WordPressPostPagePage();
        Browser.takeScreenShot();
        Browser.saveScreenShot();
        Assert.assertEquals(wordPressPostPage.getPostTitle(), pageTitle, "The page title " + pageTitle + " is not displayed on Page WP page");
        Assert.assertTrue(wordPressPostPage.getPagesLinks().contains(pageTitle));

        logger.info("Found the page: " + pageTitle);
        logger.info("Opening WordPress main page");
        wordPressPostPage.clickWordPressOnAzureLink();

        WordPressMainPage wordPressMainPage = new WordPressMainPage();
        Browser.takeScreenShot();
        Browser.saveScreenShot();
        Assert.assertTrue(wordPressMainPage.getPagesLinks().contains(pageTitle), "The page title " + pageTitle + " is not displayed on main WP page");

        tearDown();
        initialize();

        logger.info("Logging in WP Admin site");
        loginPage = new LoginPage();
        loginPage.doLogin(role, userName, password);

        dashboardPage = new DashboardPage();
        logger.info("Opening 'Pages' page");
        dashboardPage.getLeftMenu().clickOnItem(LeftMenuEnum.PAGES);

        pagesPage = new PagesPage();
        logger.info("Deleting published page: " + pageTitle);
        pagesPage.deletePage(pageTitle);

        logger.info("Opening WordPress Main page");
        pagesPage.getWordPressOnAzureDdl().wordPressOnAzureDdlClick();

        wordPressMainPage = new WordPressMainPage();
        Browser.takeScreenShot();
        Browser.saveScreenShot();
        Assert.assertFalse(wordPressMainPage.getPagesLinks().contains(pageTitle), "The page title " + pageTitle + " is still displayed on main WP page");
        logger.info("Deleted page is not displayed on WordPress Main page");
        wordPressMainPage.getNameBar().clickLogOut();
        logger.info("The user is logged out");

    }


    @Test(groups = {"regression", "smoke"})
    @Description("Test10-2: Api - Status code for get request for published page")
    @Severity(SeverityLevel.CRITICAL)

    public void apiPageCodeStatusTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.doLogin("Admin", Configuration.getProperties().getProperty("adminUserName"), Configuration.getProperties().getProperty("adminPassword"));

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.getLeftMenu().clickOnItem(LeftMenuEnum.PAGES);

        PagesPage pagesPage = new PagesPage();
        pagesPage.addNewPageButtonClick();

        NewPagePage newPagePage = new NewPagePage();
        newPagePage.closeChoosePatternPopUp();
        String pageTitle = "kl" + RandomUtil.generateRandomString(6);
        newPagePage.addTitleAndText(pageTitle);

        logger.info("Publishing a new page");
        newPagePage.saveDraft();
        newPagePage.isSavedButtonDisplayed();
        Browser.takeScreenShot();

        String pageUrl = newPagePage.takePageUrlFromSidebar();
        newPagePage.viewPagesButtonClick();

        pagesPage = new PagesPage();
        pagesPage.getNameBar().clickLogOut();

        Assert.assertTrue(ApiCheckUrl.getStatusCode(pageUrl)==404, "Status code is not 404");

        tearDown();
        initialize();

        logger.info("Logging in WP Admin site");
        loginPage = new LoginPage();
        loginPage.doLogin("Admin", Configuration.getProperties().getProperty("adminUserName"), Configuration.getProperties().getProperty("adminPassword"));

        dashboardPage = new DashboardPage();
        logger.info("Opening 'Pages' page");
        dashboardPage.getLeftMenu().clickOnItem(LeftMenuEnum.PAGES);
        pagesPage = new PagesPage();
        pagesPage.clickPageTitle(pageTitle);


        newPagePage = new NewPagePage();
        logger.info("Changing the status of the page to " + ItemStatusEnum.PUBLISHED);

        newPagePage.changePageStatus(ItemStatusEnum.PUBLISHED);
        Browser.takeScreenShot();

        logger.info("Opening 'Pages' page");
        newPagePage.viewPagesButtonClick();
        Browser.takeScreenShot();
        pagesPage.getNameBar().clickLogOut();

        Assert.assertTrue(ApiCheckUrl.getStatusCode(pageUrl)==200, "Status code is not 200");

        logger.info("Logging in WP Admin site");
        loginPage = new LoginPage();
        loginPage.doLogin("Admin", Configuration.getProperties().getProperty("adminUserName"), Configuration.getProperties().getProperty("adminPassword"));

        dashboardPage = new DashboardPage();
        logger.info("Opening 'Pages' page");
        dashboardPage.getLeftMenu().clickOnItem(LeftMenuEnum.PAGES);
        pagesPage = new PagesPage();
        pagesPage.deletePage(pageTitle);
        logger.info("The page is deleted");
        pagesPage.getNameBar().clickLogOut();
        logger.info("The user is logged out");


    }


    @AfterMethod(groups = {"regression", "smoke"})
    public void tearDown() {

        Browser.close();

    }

}
