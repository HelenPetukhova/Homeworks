package com.itacademy.aqa.tests;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.config.Configuration;
import com.itacademy.aqa.data.UserRoleLeftMenuData;
import com.itacademy.aqa.enums.ItemStatusEnum;
import com.itacademy.aqa.enums.LeftMenuEnum;
import com.itacademy.aqa.pages.DashboardPage;
import com.itacademy.aqa.pages.LoginPage;
import com.itacademy.aqa.pages.NewPagePage;
import com.itacademy.aqa.pages.PagesPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WPPageStatusCanBeChanged09Test {
    private static final Logger logger = Logger.getLogger(WPPageStatusCanBeChanged09Test.class);


    @BeforeMethod(groups = {"smoke", "regression"})
    public void initialize() {
        Configuration.getProperties();
        Browser.initDriver();
        Browser.getWebDriver().get(Configuration.getBaseUrl());

    }


    private NewPagePage loginAndGoToPagesAndStartNewPage(String role, String userName, String password){
        logger.info("Starting test of page status");
        logger.info("Logging in as a user with role " + role);
        LoginPage loginPage = new LoginPage();
        loginPage.doLogin(role, userName, password);

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.getLeftMenu().clickOnItem(LeftMenuEnum.PAGES);

        PagesPage pagesPage = new PagesPage(); //loginAndGoToPages(role, userName, password); //
        logger.info("Starting creation of a new page");
        pagesPage.addNewPageButtonClick();
        return new NewPagePage();
    }

    @Test(dataProvider = "userRoleCredentialsPageCreators", dataProviderClass = UserRoleLeftMenuData.class, groups = "regression")
    @Description("Test09-1: Page status can be changed from Draft to Pending")
    @Severity(SeverityLevel.CRITICAL)

    public void pageStatusCanBeChangedFromDraftToPendingTest(String role, String userName, String password, String pageTitle) {

        NewPagePage newPagePage = loginAndGoToPagesAndStartNewPage(role, userName, password);  //new NewPagePage();
        newPagePage.closeChoosePatternPopUp();
        newPagePage.addTitleAndText(pageTitle);

        logger.info("Saving the draft of the page");

        newPagePage.saveDraft();
        Assert.assertTrue(newPagePage.isSavedButtonDisplayed(), "Saved button is displayed after draft saving");

        logger.info("Opening 'Pages' page");

        newPagePage.viewPagesButtonClick();

        PagesPage pagesPage = new PagesPage();
        String expectedPageTitle = pageTitle + " — Draft";
        System.out.println("Actual pages list: " + pagesPage.getAllPagesTitles());
        Assert.assertTrue(pagesPage.getAllPagesTitles().contains(expectedPageTitle), "In Pages table there is no title of saved draft with 'Draft' mark");
        Browser.takeScreenShot();
        logger.info("Found the draft in Pages list with 'Draft' mark");
        logger.info("Opening the page draft");

        pagesPage.clickPageTitle(pageTitle);

        newPagePage = new NewPagePage();
        Assert.assertTrue(newPagePage.isPageOpened(), "The draft of the page is not displayed");
        Assert.assertEquals(newPagePage.getPageStatus(), "Draft", "The wrong page's status is displayed. Should be 'Draft'");
        logger.info("Found 'Draft' status on editor page");

        logger.info("Changing the status of the page to " + ItemStatusEnum.PENDING);
        newPagePage.changePageStatus(ItemStatusEnum.PENDING);
        Browser.takeScreenShot();
        Assert.assertEquals(newPagePage.getPageStatus(), "Pending", "Status of the page in Settings panel is " + newPagePage.getPageStatus() + "instead of Pending");  // или оставить "Pending" вместо ItemStatusEnum.PENDING.getValue() ?
        logger.info("Found the new status of the page: " + newPagePage.getPageStatus());
        logger.info("Opening 'Pages' page");

        newPagePage.viewPagesButtonClick();
        pagesPage = new PagesPage();
        expectedPageTitle = pageTitle + " — Pending";
        System.out.println("Actual posts list: " + pagesPage.getAllPagesTitles());
        Browser.takeScreenShot();
        Assert.assertTrue(pagesPage.isPageOpened(), "'Pages' page is not opened");
        Assert.assertTrue(pagesPage.getAllPagesTitles().contains(expectedPageTitle), "In Pages table there is no title of the page with 'Pending' mark");
        logger.info("Found the page with a new status: Pending");

        pagesPage.deletePage(pageTitle);
        logger.info("The page is deleted");
        pagesPage.getNameBar().clickLogOut();
        logger.info("The user is logged out");

    }


    @Test(dataProvider = "userRoleCredentialsPageCreators", dataProviderClass = UserRoleLeftMenuData.class, groups = {"regression"})
    @Description("Test09-02: Page status can be changed from Draft to Private")
    @Severity(SeverityLevel.CRITICAL)

    public void pageStatusCanBeChangedFromDraftToPrivateTest(String role, String userName, String password, String pageTitle) throws InterruptedException {

        NewPagePage newPagePage = loginAndGoToPagesAndStartNewPage(role, userName, password);  //new NewPagePage();
        newPagePage.closeChoosePatternPopUp();

        newPagePage.addTitleAndText(pageTitle);
        logger.info("Saving the draft of the page");
        newPagePage.saveDraft();
        Assert.assertTrue(newPagePage.isSavedButtonDisplayed(), "Saved button is displayed after draft saving");

        logger.info("Opening 'Pages' page");
        newPagePage.viewPagesButtonClick();

        PagesPage pagesPage = new PagesPage();
        String expectedPageTitle = pageTitle + " — Draft";
        System.out.println("Actual posts list: " + pagesPage.getAllPagesTitles());
        Assert.assertTrue(pagesPage.getAllPagesTitles().contains(expectedPageTitle), "In Pages table there is no title of saved draft with 'Draft' mark");
        Browser.takeScreenShot();
        logger.info("Found the draft in Pages list with 'Draft' mark");
        logger.info("Opening the page draft");
        pagesPage.clickPageTitle(pageTitle);

        newPagePage = new NewPagePage();
        Assert.assertTrue(newPagePage.isPageOpened(), "The draft of the page is not displayed");
        Assert.assertEquals(newPagePage.getPageStatus(), "Draft", "The wrong page's status is displayed. Should be 'Draft'");
        Browser.takeScreenShot();

        logger.info("Found 'Draft' status on editor page");
        logger.info("Changing the status of the page to " + ItemStatusEnum.PRIVATE);

        newPagePage.changePageStatus(ItemStatusEnum.PRIVATE);
        Browser.takeScreenShot();
        Assert.assertEquals(newPagePage.getPageStatus(), "Private", "Status of the page in Settings panel is " + newPagePage.getPageStatus() + "instead of Private");  // или оставить "Pending" вместо ItemStatusEnum.PENDING.getValue() ?

        logger.info("Found the new status of the page: " + newPagePage.getPageStatus());
        logger.info("Opening 'Pages' page");

        newPagePage.viewPagesButtonClick();
        Browser.takeScreenShot();

        pagesPage = new PagesPage();
        expectedPageTitle = pageTitle + " — Private";
        System.out.println("Actual posts list: " + pagesPage.getAllPagesTitles());
        Assert.assertTrue(pagesPage.isPageOpened(), "'Pages' page is not opened");
        Assert.assertTrue(pagesPage.getAllPagesTitles().contains(expectedPageTitle), "In Pages table there is no title of the page with 'Private' mark");
        logger.info("Found the page with a new status: Private");

        pagesPage.deletePage(pageTitle);
        logger.info("The page is deleted");
        pagesPage.getNameBar().clickLogOut();
        logger.info("The user is logged out");
    }


    @Test(dataProvider = "userRoleCredentialsPageCreators", dataProviderClass = UserRoleLeftMenuData.class, groups = {"regression", "smoke"})
    @Description("Test09-03: Page status can be changed from Draft to Scheduled")
    @Severity(SeverityLevel.CRITICAL)

    public void pageStatusCanBeChangedFromDraftToScheduledTest(String role, String userName, String password, String pageTitle) throws InterruptedException {

        NewPagePage newPagePage = loginAndGoToPagesAndStartNewPage(role, userName, password);  //new NewPagePage();
        newPagePage.closeChoosePatternPopUp();
        newPagePage.addTitleAndText(pageTitle);

        logger.info("Saving the draft of the page");

        newPagePage.saveDraft();

        logger.info("Opening 'Pages' page");

        newPagePage.viewPagesButtonClick();

        PagesPage pagesPage = new PagesPage();
        String expectedPageTitle = pageTitle + " — Draft";
        System.out.println("Actual posts list: " + pagesPage.getAllPagesTitles());
        Assert.assertTrue(pagesPage.getAllPagesTitles().contains(expectedPageTitle), "In Pages table there is no title of saved draft with 'Draft' mark");
        Browser.takeScreenShot();

        logger.info("Found the draft in Pages list with 'Draft' mark");
        logger.info("Opening the page draft");

        pagesPage.clickPageTitle(pageTitle);

        newPagePage = new NewPagePage();
        Assert.assertTrue(newPagePage.isPageOpened(), "The draft of the page is not displayed");
        Assert.assertEquals(newPagePage.getPageStatus(), "Draft", "The wrong page's status is displayed. Should be 'Draft'");

        logger.info("Found 'Draft' status on editor page");
        logger.info("Changing the status of the page to " + ItemStatusEnum.SCHEDULED);

        newPagePage.changePageStatus(ItemStatusEnum.SCHEDULED);
        Browser.takeScreenShot();
        Assert.assertEquals(newPagePage.getPageStatus(), "Scheduled", "Status of the page in Settings panel is " + newPagePage.getPageStatus() + "instead of Scheduled");
        logger.info("Opening 'Pages' page");

        newPagePage.viewPagesButtonClick();
        Browser.takeScreenShot();

        pagesPage = new PagesPage();
        expectedPageTitle = pageTitle + " — Scheduled";
        System.out.println("Actual posts list: " + pagesPage.getAllPagesTitles());
        Assert.assertTrue(pagesPage.isPageOpened(), "'Pages' page is not opened");
        Assert.assertTrue(pagesPage.getAllPagesTitles().contains(expectedPageTitle), "In Pages table there is no title of saved page with 'Scheduled' mark");
        logger.info("Found the page with a new status: Scheduled");


        pagesPage.deletePage(pageTitle);
        logger.info("The page is deleted");
        pagesPage.getNameBar().clickLogOut();
        logger.info("The user is logged out");
    }


    @Test(dataProvider = "userRoleCredentialsPageCreators", dataProviderClass = UserRoleLeftMenuData.class, groups = {"regression", "smoke"})
    @Description("Test09-04: Page status can be changed from Draft to Published")
    @Severity(SeverityLevel.CRITICAL)

    public void pageStatusCanBeChangedFromDraftToPublishedTest(String role, String userName, String password, String pageTitle) throws InterruptedException {

        NewPagePage newPagePage = loginAndGoToPagesAndStartNewPage(role, userName, password);  //new NewPagePage();
        newPagePage.closeChoosePatternPopUp();
        newPagePage.addTitleAndText(pageTitle);

        logger.info("Saving the draft of the page");

        newPagePage.saveDraft();
        logger.info("Opening 'Pages' page");

        newPagePage.viewPagesButtonClick();

        PagesPage pagesPage = new PagesPage();
        String expectedPageTitle = pageTitle + " — Draft";
        System.out.println("Actual posts list: " + pagesPage.getAllPagesTitles());
        Assert.assertTrue(pagesPage.getAllPagesTitles().contains(expectedPageTitle), "In Pages table there is no title of saved draft with 'Draft' mark");
        Browser.takeScreenShot();

        logger.info("Found the draft in Pages list with 'Draft' mark");
        logger.info("Opening the page draft");

        pagesPage.clickPageTitle(pageTitle);

        newPagePage = new NewPagePage();
        Assert.assertTrue(newPagePage.isPageOpened(), "The draft of the page is not displayed");
        Assert.assertEquals(newPagePage.getPageStatus(), "Draft", "The wrong page's status is displayed. Should be 'Draft'");

        logger.info("Found 'Draft' status on editor page");
        logger.info("Changing the status of the page to " + ItemStatusEnum.PUBLISHED);

        newPagePage.changePageStatus(ItemStatusEnum.PUBLISHED);
        Browser.takeScreenShot();
        Assert.assertEquals(newPagePage.getPageStatus(), "Published", "Status of the page in Settings panel is " + newPagePage.getPageStatus() + "instead of Published");
        logger.info("Found the new status of the page: " + newPagePage.getPageStatus());
        logger.info("Opening 'Pages' page");

        newPagePage.viewPagesButtonClick();
        Browser.takeScreenShot();

        pagesPage = new PagesPage();
        expectedPageTitle = pageTitle;
        System.out.println("Actual posts list: " + pagesPage.getAllPagesTitles());
        Assert.assertTrue(pagesPage.isPageOpened(), "'Pages' page is not opened");
        Assert.assertTrue(pagesPage.getAllPagesTitles().contains(expectedPageTitle), "In Pages table there is no title of saved page without status mark");
        logger.info("Found the page without status mark");

        pagesPage.deletePage(pageTitle);
        logger.info("The page is deleted");
        pagesPage.getNameBar().clickLogOut();
        logger.info("The user is logged out");
    }



    @AfterMethod(groups = {"smoke", "regression"})
    public void tearDown() {

        Browser.close();

    }

}
