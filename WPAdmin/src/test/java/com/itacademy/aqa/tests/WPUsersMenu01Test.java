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

public class WPUsersMenu01Test {

    private static final Logger logger = Logger.getLogger(WPUsersMenu01Test.class);

    @BeforeMethod(groups = {"regression", "smoke"})
    public void initialize() {
        Configuration.getProperties();
        Browser.initDriver();
        Browser.getWebDriver().get(Configuration.getBaseUrl());
    }


    @Test(dataProvider = "userRoleLeftMenuData", dataProviderClass = UserRoleLeftMenuData.class, groups = {"regression", "smoke"})
    @Description("Test01: Left Menu depends on user's role")
    @Severity(SeverityLevel.CRITICAL)
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
        logger.info("User is logging out");
        dashboardPage.getNameBar().clickLogOut();
        logger.info("The test is finished");
    }


    @AfterMethod(groups = {"regression", "smoke"})
    public void tearDown() {
        logger.info("Browser is closing");
        Browser.close();

    }

}

