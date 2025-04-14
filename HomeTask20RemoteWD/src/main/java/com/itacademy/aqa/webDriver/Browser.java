package com.itacademy.aqa.webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Browser {
    //Объявление статической переменной webDriver, чтобы использовать один и тот же webDriver везде
    private static WebDriver webDriver;
    // Константы (время для ожиданий)
    public static final int TIME_OUT_IN_SECONDS = 50;
    public static final long DEFAULT_TIMEOUT = 40L;


    //Приватный конструктор предотвращает создание экземпляров класса Browser.
    private Browser() {
    }

    //Возвращает экземпляр WebDriver, инициализируя его, если он еще не создан.
    public static WebDriver getWebDriver() {
        if (webDriver == null) {
            initDriver();
        }
        return webDriver;
    }

    //Инициализирует WebDriver, максимизирует окно браузера и устанавливает различные таймауты. Static потому что обращаемся к нему из статического метода
    public static void initDriver() {
        webDriver = BrowserFactory.createDriver(Configuration.getBrowserEnum());
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
    }

    // Метод для поиска web элемента
    public static WebElement findWebElement(By locator) {
        return new WebDriverWait(getWebDriver(), Duration.ofSeconds(TIME_OUT_IN_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(locator));

    }

    // Метод для поиска списка web элементов
    public static List<WebElement> findListOfElements(By locator) {
        return getWebDriver().findElements(locator);

    }


    //Эти методы используют WebDriverWait для ожидания, пока элемент не станет кликабельным или видимым.
    public static WebElement waitForElementToBeClickable(By locator) {

        WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(TIME_OUT_IN_SECONDS));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        WebElement element = getWebDriver().findElement(locator);
        return element;

    }

    public static WebElement waitForElementToBeVisible(By locator) {

        WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(TIME_OUT_IN_SECONDS));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        WebElement element = getWebDriver().findElement(locator);
        return element;

    }

    public static void waitForElementToBeInvisible(By locator) {

        WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(TIME_OUT_IN_SECONDS));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) Browser.getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void close() {
        if (webDriver != null) {
            webDriver.quit();    // закрываем браузер
            webDriver = null;
        }
    }
}