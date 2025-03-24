package com.itacademy.aqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class OnlinerMenuTest {
    WebDriver webDriver;

    static {
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");
    }

    @BeforeMethod
    public void initialize(){
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.onliner.by");
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void catalogCanBeFindTest(){
        By catalogLocator = By.xpath("//nav[@class='b-top-navigation']//span[.='Каталог']");
        WebElement catalog = webDriver.findElement(catalogLocator);
        System.out.println("Found menu item: " + catalog.getText());
        Assert.assertTrue(catalog.isDisplayed(), "Каталог option of main menu is not displayed");
    }

    @Test
    public void newsCanBeFindTest(){
        By newsLocator = By.xpath("//nav[@class='b-top-navigation']//span[.='Новости']");
        WebElement news = webDriver.findElement(newsLocator);
        System.out.println("Found menu item: " + news.getText());
        Assert.assertTrue(news.isDisplayed(), "Новости option of main menu is not displayed");
    }

    @Test
    public void autoMarketCanBeFindTest(){
        By autoMarketLocator = By.xpath("//nav[@class='b-top-navigation']//span[.='Автобарахолка']");
        WebElement autoMarket = webDriver.findElement(autoMarketLocator);
        System.out.println("Found menu item: " + autoMarket.getText());
        Assert.assertTrue(autoMarket.isDisplayed(), "Автобарахолка option of main menu is not displayed");
    }

    @Test
    public void housesFlatsCanBeFindTest(){
        By housesFlatsLocator = By.xpath("//nav[@class='b-top-navigation']//span[.='Дома и квартиры']");
        WebElement housesFlats = webDriver.findElement(housesFlatsLocator);
        System.out.println("Found menu item: " + housesFlats.getText());
        Assert.assertTrue(housesFlats.isDisplayed(), "Дома и квартиры option of main menu is not displayed");
    }

    @Test
    public void servicesCanBeFindTest(){
        By servicesLocator = By.xpath("//nav[@class='b-top-navigation']//span[.='Услуги']");
        WebElement services = webDriver.findElement(servicesLocator);
        System.out.println("Found menu item: " + services.getText());
        Assert.assertTrue(services.isDisplayed(), "Услуги option of main menu is not displayed");
    }

    @Test
    public void fleaMarketCanBeFindTest(){
        By fleaMarketLocator = By.xpath("//nav[@class='b-top-navigation']//span[.='Барахолка']");
        WebElement fleaMarket = webDriver.findElement(fleaMarketLocator);
        System.out.println("Found menu item: " + fleaMarket.getText());
        Assert.assertTrue(fleaMarket.isDisplayed(), "Барахолка option of main menu is not displayed");
    }

    @Test
    public void forumCanBeFindTest(){
        By forumLocator = By.xpath("//nav[@class='b-top-navigation']//span[.='Форум']");
        WebElement forum = webDriver.findElement(forumLocator);
        System.out.println("Found menu item: " + forum.getText());
        Assert.assertTrue(forum.isDisplayed(), "Форум option of main menu is not displayed");
    }

    @AfterMethod
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
