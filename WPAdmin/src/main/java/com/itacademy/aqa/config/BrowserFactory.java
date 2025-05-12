package com.itacademy.aqa.config;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;

public class BrowserFactory {
    private static Logger logger = Logger.getLogger(BrowserFactory.class);

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
            default -> {
                logger.error(browserEnum + " browser is not supported");
                throw new RuntimeException(browserEnum + " is not supported");

            }
        }
        logger.info(browserEnum + " webDriver was created");
        return webDriver;
    }
}