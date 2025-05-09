package com.itacademy.aqa.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;

public class BrowserFactory {

    static {
        System.setProperty("webdriver.chrome.driver", "c:/chromedriver.exe");
    }

    public static WebDriver createDriver(BrowserEnum browserEnum) {
        WebDriver webDriver;

        switch (browserEnum) {
            case CHROME -> {
                ChromeOptions chromeOptions = new ChromeOptions();
//                Map<String, Object> prefs = new HashMap<>();
//                prefs.put("download.default_directory", "C:\\Users\\HP\\Downloads");
//                prefs.put("browser.helperApps.neverAsk.saveToDisk", "application/pdf:text/csv;application/octet-stream;application/x-msdownload");
//                chromeOptions.setExperimentalOption("prefs", prefs);
                webDriver = new ChromeDriver(chromeOptions);
            }
            case FIREFOX -> {
                webDriver = new FirefoxDriver();
            }
            default -> {
                throw new RuntimeException(browserEnum + " is not supported");
            }
        }

        return webDriver;
    }
}
