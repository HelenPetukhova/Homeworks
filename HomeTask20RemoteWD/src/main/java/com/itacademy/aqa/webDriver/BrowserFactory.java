package com.itacademy.aqa.webDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class BrowserFactory {
    //Инициализация драйвера. Устанавливает системное свойство для указания пути к Хром драйверу
//    static {
//        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
//    }

    public static WebDriver createDriver(BrowserEnum browserEnum) {

        WebDriver webDriver;

        switch (browserEnum) {
            case CHROME -> {
                ChromeOptions chromeOptions = getChromeOptions();
                webDriver = new ChromeDriver(chromeOptions);


            }
            case IPHONE12_CHROME_EMULATOR -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                Map<String, Object> browserProperties = new HashMap<>();
                browserProperties.put("deviceName", "iPhone 12 Pro");

                chromeOptions.setExperimentalOption("mobileEmulation", browserProperties);
                webDriver = new ChromeDriver(chromeOptions);
            }
            case CHROME_EMULATOR -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                Map<String, Object> browserProperties = new HashMap<>();
                Map<String, Object> deviceMetrics = new HashMap<>();
                deviceMetrics.put("width", 360);
                deviceMetrics.put("height", 360);

                browserProperties.put("deviceMetrics", deviceMetrics);

                chromeOptions.setExperimentalOption("mobileEmulation", browserProperties);
                webDriver = new ChromeDriver(chromeOptions);
            }
            case REMOTE_CHROME -> {
                try {
                    webDriver = new RemoteWebDriver(new URL(Configuration.getRemoteDriverUrl()), getChromeOptions());
                } catch (MalformedURLException e) {
                    System.out.println("Can't create a driver by " + Configuration.getRemoteDriverUrl());
                    webDriver = null;
                }
            }
            case REMOTE_FIREFOX -> {
                try {
                    var options = new FirefoxOptions();
                    Map<String,Object> prefs = new HashMap<>();
//                    prefs.put("remote.active-protocols",3);
//                    options.addPreference("acceptInsecureCerts",true);
//                    options.addPreference("remote.active-protocols", 3);
                    options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
                    webDriver = new RemoteWebDriver(new URL(Configuration.getRemoteDriverUrl()),options);
                } catch (MalformedURLException e) {
                    System.out.println("can't create a driver by " + Configuration.getRemoteDriverUrl());
                    webDriver = null;
                }
            }
            case FIREFOX -> {
                webDriver = new FirefoxDriver();
            }
            case REMOTE_EDGE -> {
                try {
                    webDriver = new RemoteWebDriver(new URL(Configuration.getRemoteDriverUrl()), new EdgeOptions());
                } catch (MalformedURLException e) {
                    System.out.println("Can't create a driver by " + Configuration.getRemoteDriverUrl());
                    webDriver = null;
                }
            }
            default -> {
                throw new RuntimeException(browserEnum + "is not supported");
            }
        }
        return webDriver;
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("safebrowsing.enabled", true);   // без этого не скачивал (unverified downloading)
        prefs.put("download.default_directory", "C:\\Users\\HP\\Downloads");  //куда будут складываться скачанные файлы
        prefs.put("browser.helperApps.neverAsk.saveToDisk", "application/pdf:text/csv;application/octet-stream;application/x-msdownload"); // не спрашивая, сохранять
        chromeOptions.setExperimentalOption("prefs", prefs);  // добавляем свои дополнительные наполнительные настройки к ChromeOptions
         /*
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");

                //Подменяем user-agent на обычный браузер
                options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari

                 // Убираем флаги автоматизации
                 options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                 options.addArguments("--disable-blink-features=AutomationControlled");
                 prefs.put("excludeSwitches", new String[]{"enable-automation"});

                 // Отключить gpu
                 chromeOptions.addArguments("--disable-gpu");

                 chromeOptions.addArguments("--headless");

                 chromeOptions.addArguments("--ignore-certificate-errors");

                 // заблокировать загрузку картинок, чтобы загрузка шла быстрее
                 prefs.put("profile.managed_default_content_settings.images",2);
                 // инкогнито окно
                chromeOptions.addArguments("--incognito");
                // отключить расширения
                chromeOptions.addArguments("--disable-extensions");

                chromeOptions.addArguments("--no-sandbox");
                //посмотреть конкретный порт
                chromeOptions.addArguments("--remote-debugging-port-9222");
                //отключить куки
                prefs.put("profile.default_content_settings.cookies", 2); // Block all cookies
                //отключить историю
                    prefs.put("profile.default_content_settings.history", 0); // Disable history logging

                 */
        return chromeOptions;
    }
}