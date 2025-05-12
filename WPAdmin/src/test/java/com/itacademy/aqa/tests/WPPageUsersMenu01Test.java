package com.itacademy.aqa.tests;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.config.Configuration;
import com.itacademy.aqa.data.UserRoleLeftMenuData;
import com.itacademy.aqa.pages.DashboardPage;
import com.itacademy.aqa.pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.log4j.Logger;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class WPPageUsersMenu01Test {
    private static Logger logger = Logger.getLogger(WPPageUsersMenu01Test.class);


    @BeforeMethod
    public void initialize() {
        Browser.initDriver();
        Browser.getWebDriver().get(Configuration.getBaseUrl());
    }


//    @Test
//    public void loginPageCanBeOpenedTest() {
//        LoginPage loginPage = new LoginPage();
//        Assert.assertTrue(loginPage.isPageOpened(), "'Log In' button is not displayed");
//    }


//    @Test(dataProvider = "userRoleCredentials", dataProviderClass = UserRoleLeftMenuData.class)
//    // Enter correct login/password of any user and verify that Dashboard/Home page is opened
//    public void loginWithCorrectCredentialsTest(String role, String username, String password) {
//        LoginPage loginPage = new LoginPage();
//        loginPage.doLogin(role, username, password);
//        DashboardPage dashboardPage = new DashboardPage();
//
//        Assert.assertTrue(dashboardPage.isPageOpened(), "Dashboard page is not opened");
//    }


//
//    @Test
//    //TC01 Left menu depends on user roles
//    public void leftMenuItemsForAdmin() {
//        List<String> expectedLeftMenuItems = Arrays.asList("Dashboard", "Posts", "Media", "Pages", "Comments", "Appearance", "Plugins", "Users",
//                                                          "Tools", "Settings", "Performance", "Smush");
//
//        LoginPage loginPage = new LoginPage();
//        loginPage.doLogin("test-admin", "&2agnh5MyevReS8jhoYDTtbt");
//
//        DashboardPage dashboardPage = new DashboardPage();
//        System.out.println("Actual menu: " + dashboardPage.getLeftMenu().getLeftMenuItemsTitles());
//
//        Assert.assertEquals(dashboardPage.getLeftMenu().getLeftMenuItemsTitles(), expectedLeftMenuItems);
//    }
//


    // TC01
    @Test(dataProvider = "userRoleLeftMenuData", dataProviderClass = UserRoleLeftMenuData.class)
    @Description("Test01: Left Menu depends on user's role") @Severity(SeverityLevel.CRITICAL)
    public void leftMenuDependsOnUserRolesTest(String role, String username, String password, List<String> expectedLeftMenuItems) {
        logger.info("Starting test to check dependence of left menu content on user role");
        logger.debug("Creating loginPage object");
        LoginPage loginPage = new LoginPage();
        loginPage.doLogin(role, username, password);
        logger.info("Login was done under user with role: " + role);

        logger.debug("Creating dashboardPage object");
        DashboardPage dashboardPage = new DashboardPage();
        System.out.println("Actual menu: " + dashboardPage.getLeftMenu().getLeftMenuItemsTitles());

        logger.info("Checking that content of left menu corresponds the list of items expected for the role");
        Assert.assertEquals(dashboardPage.getLeftMenu().getLeftMenuItemsTitles(), expectedLeftMenuItems, "Displayed left menu items doesn't correspond to user's role: " + role);
        logger.debug("Taking a screenshot of the page after login");
        Browser.saveScreenShot();
        Browser.takeScreenShot();
        logger.info("User is logging out");
        dashboardPage.getNameBar().clickLogOut();
        logger.info("The test is finished");
    }


    @AfterMethod
    public void tearDown() {
        logger.info("Browser is closing");
        Browser.close();

    }

}

