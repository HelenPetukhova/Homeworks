package com.itacademy.aqa;

import com.itacademy.aqa.pageObject.elements.ManufactureFiltersEnum;
import com.itacademy.aqa.pageObject.elements.SecondaryMainPageMenuEnum;
import com.itacademy.aqa.pageObject.pages.MainPage;
import com.itacademy.aqa.pageObject.pages.TVsPage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class OnlinerTvCatalogPageObjectTest {
    WebDriver webDriver;

    static {
        System.setProperty("webdriver.chrome.driver", "c://chromedriver.exe");  // где искать driver
    }

    @BeforeMethod
    public void initialize() {
        webDriver = new ChromeDriver();  // создаем вебдрайвер хромдрайвер
        webDriver.manage().window().maximize();                    // окно открыли на весь экран
        webDriver.get("https://www.onliner.by/");                              // зашли на онлайнер
        webDriver.manage().timeouts().implicitlyWait(2L, TimeUnit.SECONDS);  //ожидание загрузки элементов
        webDriver.manage().timeouts().pageLoadTimeout(5L, TimeUnit.SECONDS); //ожидание загрузки страницы
        webDriver.manage().timeouts().setScriptTimeout(5L, TimeUnit.SECONDS);
    }


    @Test
    public void onlinerCanBeOpenedTest() {
        MainPage mainPage = new MainPage(webDriver);
        Assert.assertTrue(mainPage.isPageOpened(), "Onliner main page is not opened");
    }


    @Test
    public void TVCatalogPageCanBeOpenedTest() {

        MainPage mainPage = new MainPage(webDriver);

        // нужно закрыть поп-ап Принять cookie
        mainPage.getCookiesPopup().closeCookiesPopup();

        // Найти Телевизоры линк, нажать

        mainPage.getSecondaryMainPageMenu().clickOnItem(SecondaryMainPageMenuEnum.TVS);
        TVsPage tVsPage = new TVsPage(webDriver);

// Проверить, что TV Catalog открыт
        Assert.assertTrue(tVsPage.isPageOpened(), "TV Catalog is not opened");

    }

    @Test
    public void filterByManufactureTest() {
        MainPage mainPage = new MainPage(webDriver);
        WebDriverWait wait = new WebDriverWait(webDriver, 10);

// нужно закрыть поп-ап Принять cookie

        mainPage.getCookiesPopup().closeCookiesPopup();

// Найти Телевизоры линк, нажать

        mainPage.getSecondaryMainPageMenu().clickOnItem(SecondaryMainPageMenuEnum.TVS);
        TVsPage tVsPage = new TVsPage(webDriver);


// Ждем, пока TV Catalog будет открыт
        Assert.assertTrue(tVsPage.isPageOpened(), "TV Catalog is not opened");

// Проверить, что есть фильтр с производителями
         Assert.assertTrue(tVsPage.isManufacturesFilterDisplayed(), "Manufacture Filter is displayed");

// Выбрать LG из списка
        tVsPage.getManufacturesFilter().selectOption(ManufactureFiltersEnum.LG);

        Assert.assertTrue(tVsPage.isCorrectModelsAreDisplayed(ManufactureFiltersEnum.LG), "Not all items are LG TV sets.");

    }


    @AfterMethod
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();    // закрываем браузер
        }
    }
}

