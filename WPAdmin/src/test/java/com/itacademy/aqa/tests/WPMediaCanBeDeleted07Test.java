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

public class WPMediaCanBeDeleted07Test {
    private static Logger logger = Logger.getLogger(WPMediaCanBeDeleted07Test.class);


    @BeforeMethod
    public void initialize() {
        Browser.initDriver();
        Browser.getWebDriver().get(Configuration.getBaseUrl());
    }


//TC07
    //1.Спрятать логин и пароль
    //2.Добавить логи
    //3.Добавить описание степов

    @Test // (dataProvider = "userRoleCredentialsPostCreators", dataProviderClass = UserRoleLeftMenuData.class)
    @Description("Test07: Media file can be deleted") //@Severity(SeverityLevel.CRITICAL)
    public void MediaFileCanBeDeletedTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.doLogin("Admin","kladmin", "OZ%h*i5Bv*0w89%JgEugD$1V"); //("test-admin", "&2agnh5MyevReS8jhoYDTtbt");

        MediaPage mediaPage = new MediaPage();
        mediaPage.getLeftMenu().clickOnItem(LeftMenuEnum.MEDIA);
        mediaPage.uploadNewImgFile();
        Browser.saveScreenShot();
        mediaPage.deleteUploadedFile();
      Assert.assertTrue(mediaPage.isMediaFileDeleted(), "Media file is not deleted");

    }




    @AfterMethod
    public void tearDown() {
        Browser.close();

    }

}

