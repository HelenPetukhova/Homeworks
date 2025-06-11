package com.itacademy.aqa.tests;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.config.Configuration;
import com.itacademy.aqa.enums.LeftMenuEnum;
import com.itacademy.aqa.pages.LoginPage;
import com.itacademy.aqa.pages.MediaPage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WPMedia050607Test {
    private LoginPage loginPage;
    private MediaPage mediaPage;
    private static final Logger logger = Logger.getLogger(WPMedia050607Test.class);


    @BeforeMethod(groups = {"smoke", "regression"})
    public void initialize() {
        Configuration.getProperties();
        Browser.initDriver();
        Browser.getWebDriver().get(Configuration.getBaseUrl());
        logger.info("Starting test: Media file can be deleted");
        logger.info("Logging in as a user with admin role");
        loginPage = new LoginPage();
        loginPage.doLogin("Admin", Configuration.getProperties().getProperty("adminUserName"), Configuration.getProperties().getProperty("adminPassword")); //("test-admin", "&2agnh5MyevReS8jhoYDTtbt");

        mediaPage = new MediaPage();
        logger.info("Opening 'Media' page");
        mediaPage.getLeftMenu().clickOnItem(LeftMenuEnum.MEDIA);
    }


    @Test(groups = {"smoke", "regression"})
    @Description("Test05: Media-file <50MB can be added") @Severity(SeverityLevel.CRITICAL)
    public void AddNewMediaFileTest() {

        Assert.assertTrue(mediaPage.uploadNewImgFile(), "A new file is not uploaded");
        logger.info("Found uploaded file on 'Media' page");
        Browser.takeScreenShot();
        mediaPage.deleteUploadedFile();
        logger.info("Deleted the file");

    }


    @Test(groups = {"regression"})
    @Description("Test06: Media-file >50MB can not be added") @Severity(SeverityLevel.NORMAL)
    public void bigFileIsNotUploadedTest() {

        Assert.assertFalse(mediaPage.uploadBigVideoFile(), "A file more 50 MB is uploaded");
        logger.info("File more 50 MB is not uploaded");
        Assert.assertTrue(mediaPage.isErrorMessageDisplayed(), "Maximum size exceeding message is not displayed");
        logger.info("Maximum size exceeding message is displayed");
        Browser.takeScreenShot();

    }


    @Test(groups = {"smoke", "regression"})
    @Description("Test07: Media file can be deleted") @Severity(SeverityLevel.NORMAL)
    public void MediaFileCanBeDeletedTest() {

        logger.info("Uploading a file <50 MB");
        mediaPage.uploadNewImgFile();
        Browser.takeScreenShot();
        logger.info("Deleting uploaded file");
        mediaPage.deleteUploadedFile();
        mediaPage.getLeftMenu().clickOnItem(LeftMenuEnum.MEDIA);
        Assert.assertTrue(mediaPage.isMediaFileDeleted(), "Media file is not deleted");
        logger.info("The file is deleted");
        Browser.takeScreenShot();
    }

    @AfterMethod(groups = {"smoke", "regression"})
    public void tearDown() {
        mediaPage.getNameBar().clickLogOut();
        logger.info("User logged out");
        Browser.close();

    }

}
