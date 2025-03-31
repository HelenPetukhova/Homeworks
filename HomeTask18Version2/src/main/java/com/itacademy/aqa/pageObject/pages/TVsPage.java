package com.itacademy.aqa.pageObject.pages;

import com.itacademy.aqa.pageObject.elements.ManufactureFiltersEnum;
import com.itacademy.aqa.pageObject.elements.ManufacturesFilter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;

public class TVsPage {
    private WebDriver webDriver;
    private static final By TV_HEADER_LOCATOR = By.xpath("//h1[contains(text(),'Телевизоры')]");
    private ManufacturesFilter manufacturesFilter;
    By MANUFACTURES_FILTER_TITLE_LOCATOR = By.xpath("//*[@class='catalog-form__label-title'][.='Производитель']");
    By NEXT_30_ITEMS_BUTTON_LOCATOR = By.className("catalog-pagination__main");
    By TV_TITLE_1_LOCATOR = By.xpath("(//*[@class='catalog-form__description catalog-form__description_primary catalog-form__description_base-additional catalog-form__description_font-weight_semibold catalog-form__description_condensed-other'])[1]");
    By TV_ALL_TITLES_ON_PAGE_LOCATOR = By.xpath("//*[@class='catalog-form__description catalog-form__description_primary catalog-form__description_base-additional catalog-form__description_font-weight_semibold catalog-form__description_condensed-other']");

    public TVsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        manufacturesFilter = new ManufacturesFilter(webDriver);
    }

    public ManufacturesFilter getManufacturesFilter() {
        return manufacturesFilter;
    }

    public boolean isPageOpened() {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(TV_HEADER_LOCATOR));
            WebElement tvHeader = webDriver.findElement(TV_HEADER_LOCATOR);
            return tvHeader.isDisplayed();
        } catch (NotFoundException e) {
            return false;
        }
    }

    public boolean isManufacturesFilterDisplayed() {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        manufacturesFilter = new ManufacturesFilter(webDriver);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(MANUFACTURES_FILTER_TITLE_LOCATOR));
            WebElement manufactureFilterTitle = webDriver.findElement(MANUFACTURES_FILTER_TITLE_LOCATOR);
            return manufactureFilterTitle.isDisplayed();
        } catch (NotFoundException e) {
            return false;
        }
    }

    public List<WebElement> getAllTvTitlesOnPage() {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(TV_TITLE_1_LOCATOR));
        return webDriver.findElements(TV_ALL_TITLES_ON_PAGE_LOCATOR);
    }

    public void clickNext30ItemsButton() {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(NEXT_30_ITEMS_BUTTON_LOCATOR));
        WebElement next30ItemsButton = webDriver.findElement(NEXT_30_ITEMS_BUTTON_LOCATOR);
        next30ItemsButton.click();
    }


    public boolean isCorrectModelsAreDisplayed(ManufactureFiltersEnum manufactureFiltersEnum) {

        // Находим первый элемент из списка

// На всех страницах проверяем, что название выведеных товаров содержит LG
        while (true) {
            WebDriverWait wait = new WebDriverWait(webDriver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(TV_TITLE_1_LOCATOR));
            List<WebElement> tvAllTitlesOnPage = getAllTvTitlesOnPage();
            WebElement firstElement = tvAllTitlesOnPage.get(0);

            for (WebElement element : tvAllTitlesOnPage) {
                if (!element.getText().contains(manufactureFiltersEnum.getValue())) {
                    return false;
                }

            }

            // Нажимаем на кнопку "Следующие 30 товаров", пока она появляется
            try {
                clickNext30ItemsButton();
                wait.until(ExpectedConditions.invisibilityOf(firstElement));

            } catch (NoSuchElementException | ElementClickInterceptedException ex) {
                break;
            }
        }

        return true;
    }

}

