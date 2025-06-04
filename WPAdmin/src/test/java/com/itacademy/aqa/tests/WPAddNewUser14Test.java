package com.itacademy.aqa.tests;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.config.Configuration;
import com.itacademy.aqa.data.User;
import com.itacademy.aqa.data.UserBuilder;
import com.itacademy.aqa.enums.LeftMenuEnum;
import com.itacademy.aqa.pages.DashboardPage;
import com.itacademy.aqa.pages.LoginPage;
import com.itacademy.aqa.pages.NewUserPage;
import com.itacademy.aqa.pages.UsersPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WPAddNewUser14Test {
    private static Logger logger = Logger.getLogger(WPAddNewUser14Test.class);

    @BeforeMethod
    public void initialize() {
        Configuration.getProperties();
        Browser.initDriver();
        Browser.getWebDriver().get(Configuration.getBaseUrl());
    }


    // TC14
    @Test
    @Description("Test14: Create a new user")
    @Severity(SeverityLevel.NORMAL)
    public void createNewUserTest() {
        logger.info("Starting test: Create a new user");

        logger.debug("Creating user object with random credentials and role");
        User user = UserBuilder.builder().build();

        logger.info("Logging in WP Admin as an admin user");

        LoginPage loginPage = new LoginPage();
        loginPage.doLogin("Admin", Configuration.getProperties().getProperty("adminUserName"), Configuration.getProperties().getProperty("adminPassword")); //admin, editor, author(only his post)

        DashboardPage dashboardPage = new DashboardPage();
        logger.info("Opening 'Users' page");
        dashboardPage.getLeftMenu().clickOnItem(LeftMenuEnum.USERS);

        UsersPage usersPage = new UsersPage();
        Assert.assertTrue(usersPage.isPageOpened(), "Users page is not opened");

        logger.info("Adding a new user");
        usersPage.clickAddNewUser();

        NewUserPage newUserPage = new NewUserPage();
        newUserPage.feelAddNewUserForm(user);

        usersPage = new UsersPage();
        Assert.assertTrue(usersPage.getAllUsernames().contains(user.getUsername()), "User is not displayed in Users table");

        usersPage.getNameBar().clickLogOut();
        logger.info("Admin user is logged out");

        logger.info("Logging in as a new user");
        loginPage = new LoginPage();
        loginPage.doLogin(user.getRoleEnum().getValue(), user.getUsername(), user.getPassword());
        dashboardPage = new DashboardPage();
        Assert.assertTrue(dashboardPage.isPageOpened(), "New user cannot login");
        logger.info("The user is logged in");
        usersPage.getNameBar().clickLogOut();
        logger.info("The user is logged out");


        logger.info("Deleting the user that was created");
        loginPage = new LoginPage();
        loginPage.doLogin("Admin", Configuration.getProperties().getProperty("adminUserName"), Configuration.getProperties().getProperty("adminPassword"));
        dashboardPage = new DashboardPage();
        dashboardPage.getLeftMenu().clickOnItem(LeftMenuEnum.USERS);
        usersPage = new UsersPage();
        usersPage.deleteUser(user.getUsername());
        usersPage.getNameBar().clickLogOut();

    }


    @AfterMethod
    public void tearDown() {
        logger.info("Browser is closing");
        Browser.close();


    }

}
