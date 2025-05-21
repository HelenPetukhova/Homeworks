package com.itacademy.aqa.tests;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.config.Configuration;
import com.itacademy.aqa.data.UserRoleLeftMenuData;
import com.itacademy.aqa.elements.LeftMenuEnum;
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
    private static Logger logger = Logger.getLogger(WPPageDraftSave08Test.class);


    @BeforeMethod
    public void initialize() {
        Browser.initDriver();
        Browser.getWebDriver().get(Configuration.getBaseUrl());
    }


    @Test
    public void pagesPageCanBeOpenedTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.doLogin("Admin","kladmin", "OZ%h*i5Bv*0w89%JgEugD$1V"); //("test-admin", "&2agnh5MyevReS8jhoYDTtbt");

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.getLeftMenu().clickOnItem(LeftMenuEnum.PAGES);

        PagesPage pagesPage = new PagesPage();
        Assert.assertTrue(pagesPage.isPageOpened(), "Pages page is not opened");
    }




    @Test
    public void AddNewPageButtonOpensNewPagePageTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.doLogin("Admin","kladmin", "OZ%h*i5Bv*0w89%JgEugD$1V"); //("test-admin", "&2agnh5MyevReS8jhoYDTtbt");

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.getLeftMenu().clickOnItem(LeftMenuEnum.PAGES);

        PagesPage pagesPage = new PagesPage();
        pagesPage.addNewPageButtonClick();

        NewPagePage newPagePage = new NewPagePage();
        newPagePage.closeChoosePatternPopUp();

        Assert.assertTrue(newPagePage.isPageOpened(), "'Add New Page' page is not opened");

    }


    @Test(dataProvider = "userRoleCredentialsPageCreators", dataProviderClass = UserRoleLeftMenuData.class)

    public void AddTitleTextAndSaveDraftTest(String role, String userName, String password, String postTitle) {
        LoginPage loginPage = new LoginPage();
        loginPage.doLogin(role, userName, password); //("test-admin", "&2agnh5MyevReS8jhoYDTtbt");

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.getLeftMenu().clickOnItem(LeftMenuEnum.PAGES);

        PagesPage pagesPage = new PagesPage();
        pagesPage.addNewPageButtonClick();

        NewPagePage newPagePage = new NewPagePage();
        newPagePage.closeChoosePatternPopUp();

        newPagePage.addTitleAndText(postTitle);
        newPagePage.saveDraft();
        Assert.assertTrue(newPagePage.isSavedButtonDisplayed(), "Saved button is not displayed");


    }


    @Test(dataProvider = "userRoleCredentialsPageCreators", dataProviderClass = UserRoleLeftMenuData.class)

    public void SavedDraftDisplayedOnPagesPageTest(String role, String userName, String password, String pageTitle) {
        LoginPage loginPage = new LoginPage();
        loginPage.doLogin(role, userName, password); //("test-admin", "&2agnh5MyevReS8jhoYDTtbt");

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.getLeftMenu().clickOnItem(LeftMenuEnum.PAGES);

        PagesPage pagesPage = new PagesPage();
        pagesPage.addNewPageButtonClick();

        NewPagePage newPagePage = new NewPagePage();
        newPagePage.closeChoosePatternPopUp();

        newPagePage.addTitleAndText(pageTitle);
        newPagePage.saveDraft();
        newPagePage.viewPagesButtonClick();

        pagesPage = new PagesPage();
        String expectedPageTitle = pageTitle + " — Draft";
        System.out.println("Actual posts list: " + pagesPage.getAllPagesTitles());
        Assert.assertTrue(pagesPage.getAllPagesTitles().contains(expectedPageTitle), "In Pages table there is no title of saved draft with 'Draft' mark");

    }



    @Test(dataProvider = "userRoleCredentialsPageCreators", dataProviderClass = UserRoleLeftMenuData.class)
    @Description("Test08: Page - Draft can be saved") @Severity(SeverityLevel.CRITICAL)

    public void PageDraftCanBeSavedAndFoundInPagesTableTest(String role, String userName, String password, String pageTitle) {
        LoginPage loginPage = new LoginPage();
        loginPage.doLogin(role, userName, password); //("test-admin", "&2agnh5MyevReS8jhoYDTtbt");

        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.getLeftMenu().clickOnItem(LeftMenuEnum.PAGES);

        PagesPage pagesPage = new PagesPage();
        pagesPage.addNewPageButtonClick();

        NewPagePage newPagePage = new NewPagePage();
        newPagePage.closeChoosePatternPopUp();

        newPagePage.addTitleAndText(pageTitle);
        newPagePage.saveDraft();
        newPagePage.viewPagesButtonClick();

        pagesPage = new PagesPage();
        String expectedPageTitle = pageTitle + " — Draft";
        System.out.println("Actual posts list: " + pagesPage.getAllPagesTitles());
        Assert.assertTrue(pagesPage.getAllPagesTitles().contains(expectedPageTitle), "In Pages table there is no title of saved draft with 'Draft' mark");
        pagesPage.deletePage(pageTitle);
        pagesPage.getNameBar().clickLogOut();
    }



    @AfterMethod
    public void tearDown() {
        Browser.close();

    }

}

