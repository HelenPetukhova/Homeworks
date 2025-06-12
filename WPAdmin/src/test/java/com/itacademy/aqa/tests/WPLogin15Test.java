package com.itacademy.aqa.tests;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.config.Configuration;
import com.itacademy.aqa.data.UserRoleLeftMenuData;
import com.itacademy.aqa.enums.RolesEnum;
import com.itacademy.aqa.pages.DashboardPage;
import com.itacademy.aqa.pages.LoginPage;
import com.itacademy.aqa.utils.RandomUtil;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WPLogin15Test {
    private LoginPage loginPage;
    private static final Logger logger = Logger.getLogger(WPLogin15Test.class);

    @BeforeMethod(groups = {"smoke", "regression"})
    public void initialize() {
        Configuration.getProperties();
        System.out.println(Configuration.getProperties().get("adminPassword"));
        System.out.println(Configuration.getProperties().get("baseUrl"));
        System.out.println(Configuration.getProperties().get("browser"));
        System.out.println(System.getProperties().get("browser"));
        System.out.println(System.getProperties().get("env"));
        Browser.initDriver();
        Browser.getWebDriver().get(Configuration.getBaseUrl());
        logger.info("Starting Login test");
        loginPage = new LoginPage();

    }


    @Test(groups = {"smoke", "regression"})
    @Description("Test: Login page is available")
    @Severity(SeverityLevel.CRITICAL)
    public void loginPageCanBeOpenedTest() {
        logger.info("Checking that login page is opened");
        Assert.assertTrue(loginPage.isPageOpened(), "'Log In' button is not displayed");
        Browser.takeScreenShot();
    }


    @Test(dataProvider = "userRoleCredentials", dataProviderClass = UserRoleLeftMenuData.class, groups = {"smoke", "regression"})
    @Description("Test: User with valid credentials can log in WP Admin site")
    // Enter correct login/password of any user and verify that Dashboard/Home page is opened
    public void loginWithCorrectCredentialsTest(String role, String username, String password) {
        logger.info("Logging in as a user with " + role + "role");
        loginPage.doLogin(role, username, password);
        Browser.takeScreenShot();
        DashboardPage dashboardPage = new DashboardPage();
        Assert.assertTrue(dashboardPage.isPageOpened(), "Dashboard page is not opened");
    }


    @Test(groups = "regression")
    @Description("Test15: Login with invalid Credentials") @Severity(SeverityLevel.NORMAL)
    public void loginWithInvalidCredentialsTest() {
        logger.info("Starting test if error message appears when invalid credentials are used for login");
        Assert.assertTrue(loginPage.isPageOpened(), "'Log In' button is not displayed");

        logger.debug("Creating random userName, password, role");
        String randomUsername = "user_" + RandomUtil.generateRandomString(10);
        String randomPassword = "pass_" + RandomUtil.generateRandomString(10);
        RolesEnum randomRole = RandomUtil.generateRole();

        logger.info("Logging in as a user with randomly generated login and password");
        loginPage.doLogin(randomRole.getValue(), randomUsername, randomPassword);
        Browser.takeScreenShot();
        Assert.assertTrue(loginPage.isErrorMessageForInvalidCredentialsDisplayed(), "Error message is not displayed");
        System.out.println(loginPage.getErrorMessageText());
        Assert.assertEquals(loginPage.getErrorMessageText(), "Error: The username " + randomUsername + " is not registered on this site. If you are unsure of your username, try your email address instead.");

    }




    @AfterMethod(groups = {"smoke", "regression"})
    public void tearDown() {
        logger.info("Browser is closing");
        Browser.close();


    }
}
