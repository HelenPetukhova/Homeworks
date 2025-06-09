package com.itacademy.aqa.config;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserFactory {
    private static final Logger logger = Logger.getLogger(BrowserFactory.class);

    static {
        System.setProperty("webdriver.chrome.driver", "c:/chromedriver.exe");
    }

    public static WebDriver createDriver(BrowserEnum browserEnum) {
        logger.info("Creating webDriver for browser: " + browserEnum);
        WebDriver webDriver;

        switch (browserEnum) {

            case CHROME -> {
                ChromeOptions chromeOptions = new ChromeOptions();
//                Map<String, Object> prefs = new HashMap<>();
//                prefs.put("download.default_directory", "C:\\Users\\HP\\Downloads");
//                prefs.put("browser.helperApps.neverAsk.saveToDisk", "application/pdf:text/csv;application/octet-stream;application/x-msdownload");
//                chromeOptions.setExperimentalOption("prefs", prefs);
                webDriver = new ChromeDriver(chromeOptions);
                logger.info("ChromeDriver created");
            }
            case FIREFOX -> {
                webDriver = new FirefoxDriver();
                logger.info("FireDriver created");
            }
            case REMOTE_CHROME -> {
                try {ChromeOptions chromeOptions = new ChromeOptions();
                    webDriver = new RemoteWebDriver(new URL(Configuration.getRemoteDriverUrl()), chromeOptions);
                } catch (MalformedURLException e) {
                    logger.error("Invalid URL for RemoveWebDriver: " + Configuration.getRemoteDriverUrl());
                    throw new RuntimeException("Failed to create RemoveWebDriver");
                }
            }
            case REMOTE_FIREFOX -> {
                try {
                    webDriver = new RemoteWebDriver(new URL(Configuration.getRemoteDriverUrl()), new FirefoxOptions());
                } catch (MalformedURLException e) {
                    logger.error("Invalid URL for RemoveWebDriver: " + Configuration.getRemoteDriverUrl());
                    throw new RuntimeException("Failed to create RemoveWebDriver");
                }
            }
            default -> {
                logger.error(browserEnum + " browser is not supported");
                throw new RuntimeException(browserEnum + " is not supported");

            }
        }
        logger.info(browserEnum + " webDriver was created");
        return webDriver;
    }
}