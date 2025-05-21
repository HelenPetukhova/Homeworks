package com.itacademy.aqa.tests;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.config.Configuration;
import com.itacademy.aqa.elements.LeftMenuEnum;
import com.itacademy.aqa.pages.LoginPage;
import com.itacademy.aqa.pages.MediaPage;
import io.qameta.allure.Description;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WPMediaMore50MbCanNotBeSaved06Test {
    private static Logger logger = Logger.getLogger(WPMediaMore50MbCanNotBeSaved06Test.class);


    @BeforeMethod
    public void initialize() {
        Browser.initDriver();
        Browser.getWebDriver().get(Configuration.getBaseUrl());
    }


//TC06
    //1.Спрятать логин и пароль
    //2.Добавить логи
    //3.Добавить описание степов

    @Test // (dataProvider = "userRoleCredentialsPostCreators", dataProviderClass = UserRoleLeftMenuData.class)
    @Description("Test06: Media-file >50MB can not be added") //@Severity(SeverityLevel.CRITICAL)
    public void bigFileIsNotUploadedTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.doLogin("Admin","kladmin", "OZ%h*i5Bv*0w89%JgEugD$1V"); //("test-admin", "&2agnh5MyevReS8jhoYDTtbt");

        MediaPage mediaPage = new MediaPage();
        mediaPage.getLeftMenu().clickOnItem(LeftMenuEnum.MEDIA);

        Assert.assertFalse(mediaPage.uploadBigVideoFile(), "A file more 50 MB is uploaded");
        Assert.assertTrue(mediaPage.isErrorMessageDisplayed(), "Maximum size exceeding message is not displayed");
        Browser.saveScreenShot();
    }






    @AfterMethod
    public void tearDown() {
        Browser.close();

    }

}

