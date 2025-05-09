package com.itacademy.aqa.config;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Browser {
    public static WebDriver webDriver;    //можем обращаться к нему, не создавая новый объект

    public static final int TIME_OUT_IN_SECONDS = 20;
    public static final int DEFAULT_TIMEOUT = 10;


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
        webDriver = BrowserFactory.createDriver(Configuration.getBrowserEnum());
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_TIMEOUT));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(DEFAULT_TIMEOUT));
        webDriver.manage().timeouts().scriptTimeout(Duration.ofSeconds(DEFAULT_TIMEOUT));
    }

    public static WebElement waitForElementToBeClickableAndFind(By locator) {

        WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(TIME_OUT_IN_SECONDS));
        wait.until(ExpectedConditions.elementToBeClickable(locator));

        WebElement webElement = Browser.getWebDriver().findElement(locator);
        return webElement;

    }


    public static List<WebElement> waitForPresenceOfAllElementsAndFind(By locator) {

        WebDriverWait wait = new WebDriverWait(Browser.getWebDriver(), Duration.ofSeconds(TIME_OUT_IN_SECONDS));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

        List<WebElement> webElements = Browser.getWebDriver().findElements(locator);

        return webElements;

    }


    public static WebElement waitForVisibilityOfElementLocatedAndFind(By locator) {

        WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(TIME_OUT_IN_SECONDS));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        WebElement webElement = Browser.getWebDriver().findElement(locator);
        return webElement;

    }


    public static void waitForElementToBeClickable(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(Browser.getWebDriver(), Duration.ofSeconds(TIME_OUT_IN_SECONDS));
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }


    public static void close() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }
}
