package com.itacademy.aqa;

import com.itacademy.aqa.elements.ManufactureFiltersEnum;
import com.itacademy.aqa.elements.SecondaryMainPageMenuEnum;
import com.itacademy.aqa.pages.MainPage;
import com.itacademy.aqa.pages.TVsPage;
import com.itacademy.aqa.webDriver.Browser;
import com.itacademy.aqa.webDriver.Configuration;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;


public class OnlinerTvCatalogPageObjectTest {


    @BeforeMethod
    public void initialize() {
        Browser.getWebDriver().get(Configuration.getBaseUrl());

    }

    @Test
    public void onlinerCanBeOpenedTest() {
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.isPageOpened(), "Onliner main page is not opened");
    }


    @Test
    public void TVCatalogPageCanBeOpenedTest() {

        MainPage mainPage = new MainPage();

        // нужно закрыть поп-ап Принять cookie
        mainPage.getCookiesPopup().closeCookiesPopup();

        // Найти Телевизоры линк, нажать

        mainPage.getSecondaryMainPageMenu().clickOnItem(SecondaryMainPageMenuEnum.TVS);
        TVsPage tVsPage = new TVsPage();

// Проверить, что TV Catalog открыт
        Assert.assertTrue(tVsPage.isPageOpened(), "TV Catalog is not opened");

    }

    @Test
    public void filterByManufactureTest() {
        MainPage mainPage = new MainPage();

// нужно закрыть поп-ап Принять cookie

        mainPage.getCookiesPopup().closeCookiesPopup();

// Найти Телевизоры линк, нажать

        mainPage.getSecondaryMainPageMenu().clickOnItem(SecondaryMainPageMenuEnum.TVS);
        TVsPage tVsPage = new TVsPage();


// Ждем, пока TV Catalog будет открыт
        Assert.assertTrue(tVsPage.isPageOpened(), "TV Catalog is not opened");

// Проверить, что есть фильтр с производителями
        Assert.assertTrue(tVsPage.isManufacturesFilterDisplayed(), "Manufacture Filter is displayed");

// Выбрать LG из списка
        tVsPage.getManufacturesFilter().selectOption(ManufactureFiltersEnum.LG);
// Получаем список названий телевизоров
        List<String> allTvTitles = tVsPage.getAllTvTitlesOnAllPages();
// Проверяем, что
        for (String element : allTvTitles) {
            Assert.assertTrue(element.contains(ManufactureFiltersEnum.LG.getValue()), "Not all items are LG TV sets.");

        }

    }
    @AfterMethod
    public void tearDown() {
        Browser.close();
    }
}

