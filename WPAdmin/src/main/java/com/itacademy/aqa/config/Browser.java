package com.itacademy.aqa.config;

import io.qameta.allure.Attachment;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class Browser {
    public static WebDriver webDriver;    //можем обращаться к нему, не создавая новый объект

    public static final int TIME_OUT_IN_SECONDS = 20;
    public static final int DEFAULT_TIMEOUT = 10;
    private static final Logger logger = Logger.getLogger(Browser.class);

    //чтобы объект этого класса нельзя было создать, делаем приватный конструктор
    private Browser() {

    }


    public static WebDriver getWebDriver() {
        if (webDriver == null) {
            initDriver();
        }
        return webDriver;
    }


    public static void initDriver() {
        logger.info("WebDriver initializing");
        webDriver = BrowserFactory.createDriver(Configuration.getBrowserEnum());
        logger.info("WebDriver initialized");
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_TIMEOUT));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TIME_OUT_IN_SECONDS));
        webDriver.manage().timeouts().scriptTimeout(Duration.ofSeconds(DEFAULT_TIMEOUT));
        logger.info("Timeouts applied");
    }

    public static WebElement waitForElementToBeClickableAndFind(By locator) {
        logger.info("Waiting for element to be clickable: " + locator);
        WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(TIME_OUT_IN_SECONDS));
        wait.until(ExpectedConditions.elementToBeClickable(locator));

        WebElement webElement = Browser.getWebDriver().findElement(locator);
        return webElement;

    }


    public static List<WebElement> waitForPresenceOfAllElementsAndFind(By locator) {
        logger.info("Waiting for presence of elements: " + locator);
        WebDriverWait wait = new WebDriverWait(Browser.getWebDriver(), Duration.ofSeconds(TIME_OUT_IN_SECONDS));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

        List<WebElement> webElements = Browser.getWebDriver().findElements(locator);

        return webElements;

    }


    public static WebElement waitForVisibilityOfElementLocatedAndFind(By locator) {
        logger.info("Waiting for element to be visible: " + locator);
        WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(TIME_OUT_IN_SECONDS));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        WebElement webElement = Browser.getWebDriver().findElement(locator);
        return webElement;

    }


    public static boolean waitForInvisibilityOfElementLocated(By locator) {
        logger.info("Waiting for element to be invisible: " + locator);
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(TIME_OUT_IN_SECONDS));
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            logger.warn("Element did not become invisible within timeout: " + locator);
            return false;
        } catch (Exception e) {
            logger.error("Unexpected error while waiting for element to become invisible: " + locator);
            return false;
        }
    }

    public static void waitForElementToBeClickable(WebElement webElement) {
        logger.info("Waiting for web element to be clickable");

        WebDriverWait wait = new WebDriverWait(Browser.getWebDriver(), Duration.ofSeconds(TIME_OUT_IN_SECONDS));
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }


    @Attachment
    public static byte[] takeScreenShot(){
        saveScreenShot();
        return getScreenShotByBytes();
    }



    public static void saveScreenShot() {
        File screenShotsFolder = new File(Configuration.getScreenShotFolder());
        if (!screenShotsFolder.exists()) {
            screenShotsFolder.mkdirs();
            logger.info("Screenshots folder was created");
        }
        TakesScreenshot ts = (TakesScreenshot) Browser.getWebDriver();

        byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);

        Date date = new Date();
        SimpleDateFormat simpleDataFormat = new SimpleDateFormat("MM-dd-yyyy-h-mm-ss-SS--a");

        String formattedDate = simpleDataFormat.format(date);

        String fileName = Configuration.getBrowserEnum() + formattedDate + ".png";

        try {
            Files.write(new File(screenShotsFolder.getPath() + "/" + fileName).toPath(), screenshot, StandardOpenOption.CREATE);
            logger.info("Screenshot was saved");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Screenshot wasn't saved", e);
        }
    }

    @Attachment
    public static byte[] getScreenShotByBytes() {
        TakesScreenshot ts = (TakesScreenshot) Browser.getWebDriver();
        return ts.getScreenshotAs(OutputType.BYTES);
    }


    public static JavascriptExecutor getJavascriptExecutor(){

        return (JavascriptExecutor) getWebDriver();
    }

    public static void close() {
        if (webDriver != null) {
            webDriver.quit();
            logger.info("WebDriver was closed");
            webDriver = null;
            logger.info("WebDriver was cleared");
        }
    }
}
