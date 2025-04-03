package com.itacademy.aqa.webDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
    //Инициализация драйвера. Устанавливает системное свойство для указания пути к Хром драйверу
    static {
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
    }

    public static WebDriver createDriver(BrowserEnum browserEnum){

        WebDriver webDriver;

        switch (browserEnum){
            case CHROME -> {
                webDriver = new ChromeDriver();
            }
            case FIREFOX -> {
                webDriver = new FirefoxDriver();
            }
            default -> {
                throw new RuntimeException(browserEnum + "is not supported");
            }
        }
        return webDriver;
    }
}
