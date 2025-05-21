package com.itacademy.aqa.tests;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.config.Configuration;
import org.apache.log4j.Logger;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class WPCommentStatuses12Test {
    private static Logger logger = Logger.getLogger(WPCommentStatuses12Test.class);


    @BeforeMethod
    public void initialize() {
        Browser.initDriver();
        Browser.getWebDriver().get(Configuration.getBaseUrl());
    }


//    @Test
//    public void commentColorTest() {
//        LoginPage loginPage = new LoginPage();
//        loginPage.doLogin("Admin", "kladmin", "OZ%h*i5Bv*0w89%JgEugD$1V"); //("test-admin", "&2agnh5MyevReS8jhoYDTtbt");
//
//        CommentsPage commentsPage = new CommentsPage();
//        commentsPage.getLeftMenu().clickOnItem(LeftMenuEnum.COMMENTS);
//
//        commentsPage.takeRowColor();
//        Assert.assertTrue(commentsPage.takeRowColor().contains("252, 249, 232"), "Wrong color for not approved comment");
//
//    }






    @AfterMethod
    public void tearDown() {
        Browser.close();

    }

}

