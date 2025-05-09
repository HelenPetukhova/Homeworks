package com.itacademy.aqa.tests;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.config.Configuration;
import com.itacademy.aqa.data.UserRoleLeftMenuData;
import com.itacademy.aqa.elements.LeftMenuEnum;
import com.itacademy.aqa.pages.DashboardPage;
import com.itacademy.aqa.pages.LoginPage;
import com.itacademy.aqa.pages.PostsPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class WPPageUsersMenu01Test {
    private static final Logger log = LoggerFactory.getLogger(WPPageUsersMenu01Test.class);


    @BeforeMethod
    public void initialize() {
        Browser.initDriver();
        Browser.getWebDriver().get(Configuration.getBaseUrl());
    }


    @Test
    public void loginPageCanBeOpenedTest() {
        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(loginPage.isPageOpened(), "'Log In' button is not displayed");
    }


    @Test
    // Enter correct login/password of any user and verify that Dashboard/Home page is opened
    public void loginWithCorrectCredentialsTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.doLogin("test-admin", "&2agnh5MyevReS8jhoYDTtbt");
        DashboardPage dashboardPage = new DashboardPage();

        Assert.assertTrue(dashboardPage.isPageOpened(), "Dashboard page is not opened");
    }


    @Test
    //Select Posts in Left menu and check that it is opened
    public void allPostsPageCanBeOpenedTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.doLogin("test-admin", "&2agnh5MyevReS8jhoYDTtbt");

        PostsPage postsPage = new PostsPage();
        postsPage.getLeftMenu().clickOnItem(LeftMenuEnum.POSTS);

        Assert.assertTrue(postsPage.isPageOpened(), "Posts page is not opened");
    }

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
//    @Test
//    public void leftMenuItemsForEditor() {
//        List<String> expectedLeftMenuItems = Arrays.asList("Dashboard", "Posts", "Media", "Pages", "Comments", "Profile",
//                "Tools");
//
//        LoginPage loginPage = new LoginPage();
//        loginPage.doLogin("kltestuser", "BT905MYP)^3j2%zFxh@sc)kU");
//
//        DashboardPage dashboardPage = new DashboardPage();
//        System.out.println("Actual menu: " + dashboardPage.getLeftMenu().getLeftMenuItemsTitles());
//
//        Assert.assertEquals(dashboardPage.getLeftMenu().getLeftMenuItemsTitles(), expectedLeftMenuItems);
//    }
//
//
//    @Test
//    public void leftMenuItemsForAuthor() {
//        List<String> expectedLeftMenuItems = Arrays.asList("Dashboard", "Posts", "Media", "Comments", "Profile", "Tools");
//
//        LoginPage loginPage = new LoginPage();
//        loginPage.doLogin("klone", "C(17oLf9q)bRGC)4w&3Xkoin");
//
//        DashboardPage dashboardPage = new DashboardPage();
//        System.out.println("Actual menu: " + dashboardPage.getLeftMenu().getLeftMenuItemsTitles());
//
//        Assert.assertEquals(dashboardPage.getLeftMenu().getLeftMenuItemsTitles(), expectedLeftMenuItems);
//    }
//
//    @Test
//    public void leftMenuItemsForContributor() {
//        List<String> expectedLeftMenuItems = Arrays.asList("Dashboard", "Posts", "Comments", "Profile", "Tools");
//
//        LoginPage loginPage = new LoginPage();
//        loginPage.doLogin("klone", "C(17oLf9q)bRGC)4w&3Xkoin");
//
//        DashboardPage dashboardPage = new DashboardPage();
//        System.out.println("Actual menu: " + dashboardPage.getLeftMenu().getLeftMenuItemsTitles());
//
//        Assert.assertEquals(dashboardPage.getLeftMenu().getLeftMenuItemsTitles(), expectedLeftMenuItems);
//    }
//
//    @Test
//    public void leftMenuItemsForSubscriber() {
//        List<String> expectedLeftMenuItems = Arrays.asList("Dashboard", "Profile");
//
//        LoginPage loginPage = new LoginPage();
//        loginPage.doLogin("klone", "C(17oLf9q)bRGC)4w&3Xkoin");
//
//        DashboardPage dashboardPage = new DashboardPage();
//        System.out.println("Actual menu: " + dashboardPage.getLeftMenu().getLeftMenuItemsTitles());
//
//        Assert.assertEquals(dashboardPage.getLeftMenu().getLeftMenuItemsTitles(), expectedLeftMenuItems);
//    }

// TC01
    @Test(dataProvider = "userRoleLeftMenuData", dataProviderClass = UserRoleLeftMenuData.class)
    public void leftMenuDependsOnUserRolesTest(String username, String password, List<String> expectedLeftMenuItems) {

        LoginPage loginPage = new LoginPage();
        loginPage.doLogin(username, password);

        DashboardPage dashboardPage = new DashboardPage();
        System.out.println("Actual menu: " + dashboardPage.getLeftMenu().getLeftMenuItemsTitles());

        Assert.assertEquals(dashboardPage.getLeftMenu().getLeftMenuItemsTitles(), expectedLeftMenuItems);
    }


    @AfterMethod
    public void tearDown() {
        Browser.close();

    }

}

