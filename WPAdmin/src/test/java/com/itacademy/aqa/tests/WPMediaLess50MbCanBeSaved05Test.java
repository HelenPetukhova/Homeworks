package com.itacademy.aqa.tests;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.config.Configuration;
import com.itacademy.aqa.elements.LeftMenuEnum;
import com.itacademy.aqa.pages.LoginPage;
import com.itacademy.aqa.pages.MediaPage;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WPMediaLess50MbCanBeSaved05Test {


    @BeforeMethod
    public void initialize() {
        Browser.initDriver();
        Browser.getWebDriver().get(Configuration.getBaseUrl());
    }


    @Test
    public void MediaPageCanBeOpenedTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.doLogin("kladmin", "OZ%h*i5Bv*0w89%JgEugD$1V"); //("test-admin", "&2agnh5MyevReS8jhoYDTtbt");

        MediaPage mediaPage = new MediaPage();
        mediaPage.getLeftMenu().clickOnItem(LeftMenuEnum.MEDIA);

        Assert.assertTrue(mediaPage.isPageOpened(), "Media page is not opened");
    }


    @Test
    public void AddNewMediaFileButtonExpendsUploadFilesSectionTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.doLogin("kladmin", "OZ%h*i5Bv*0w89%JgEugD$1V"); //("test-admin", "&2agnh5MyevReS8jhoYDTtbt");

        MediaPage mediaPage = new MediaPage();
        mediaPage.getLeftMenu().clickOnItem(LeftMenuEnum.MEDIA);


        Assert.assertTrue(mediaPage.uploadNewFile(), "A new file is not uploaded");
        mediaPage.deleteUploadedFile();
    }



    @AfterMethod
    public void tearDown() {
        Browser.close();

    }

}

