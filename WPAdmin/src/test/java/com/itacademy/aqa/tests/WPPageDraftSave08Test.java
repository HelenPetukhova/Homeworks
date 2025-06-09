package com.itacademy.aqa.tests;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.config.Configuration;
import com.itacademy.aqa.data.UserRoleLeftMenuData;
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

public class WPPageDraftSave08Test {
    private static final Logger logger = Logger.getLogger(WPPageDraftSave08Test.class);


    @BeforeMethod
    public void initialize() {
        Configuration.getProperties();
        Browser.initDriver();
        Browser.getWebDriver().get(Configuration.getBaseUrl());
    }


    @Test
    @Description("Test: Pages page can be opened") @Severity(SeverityLevel.CRITICAL)
    public void pagesPageCanBeOpenedTest() {
        logger.info("Starting test: 'Pages' page can be opened");
        LoginPage loginPage = new LoginPage();
        loginPage.doLogin("Admin",Configuration.getProperties().getProperty("adminUserName"),Configuration.getProperties().getProperty("adminPassword")); //("test-admin", "&2agnh5MyevReS8jhoYDTtbt");

        DashboardPage dashboardPage = new DashboardPage();
        logger.info("Opening 'Pages' page");
        dashboardPage.getLeftMenu().clickOnItem(LeftMenuEnum.PAGES);

        PagesPage pagesPage = new PagesPage();
        Browser.takeScreenShot();
        Browser.saveScreenShot();
        Assert.assertTrue(pagesPage.isPageOpened(), "Pages page is not opened");
        logger.info("Found 'Pages' page opened");
        pagesPage.getNameBar().clickLogOut();
        logger.info("The user is logged out");

    }



    @Test(dataProvider = "userRoleCredentialsPageCreators", dataProviderClass = UserRoleLeftMenuData.class)
    @Description("Test08: Page - Draft can be saved") @Severity(SeverityLevel.CRITICAL)

    public void PageDraftCanBeSavedAndFoundInPagesTableTest(String role, String userName, String password, String pageTitle) {
        logger.info("Starting test: Page Draft can be saved");
        LoginPage loginPage = new LoginPage();
        loginPage.doLogin(role, userName, password); //("test-admin", "&2agnh5MyevReS8jhoYDTtbt");

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.getLeftMenu().clickOnItem(LeftMenuEnum.PAGES);

        PagesPage pagesPage = new PagesPage();
        logger.info("Creating a new page");
        pagesPage.addNewPageButtonClick();

        NewPagePage newPagePage = new NewPagePage();
        newPagePage.closeChoosePatternPopUp();
        newPagePage.addTitleAndText(pageTitle);

        logger.info("Saving the page as a draft");

        newPagePage.saveDraft();
        Browser.takeScreenShot();
        Browser.saveScreenShot();
        Assert.assertTrue(newPagePage.isSavedButtonDisplayed(), "Saved button is not displayed");


        logger.info("Opening 'Pages' page");
        newPagePage.viewPagesButtonClick();

        pagesPage = new PagesPage();
        String expectedPageTitle = pageTitle + " — Draft";
        System.out.println("Actual posts list: " + pagesPage.getAllPagesTitles());
        Browser.takeScreenShot();
        Browser.saveScreenShot();
        Assert.assertTrue(pagesPage.getAllPagesTitles().contains(expectedPageTitle), "In Pages table there is no title of saved draft with 'Draft' mark");
        logger.info("Found Draft page displayed on 'Pages' page with 'Draft' mark");
        pagesPage.deletePage(pageTitle);
        logger.info("The draft page is deleted");
        pagesPage.getNameBar().clickLogOut();
        logger.info("The user is logged out");
    }

    @AfterMethod
    public void tearDown() {
        Browser.close();

    }

}

