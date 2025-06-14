package com.itacademy.aqa.pages;

import com.itacademy.aqa.elements.ManufactureFiltersEnum;
import com.itacademy.aqa.elements.ManufacturesFilter;
import com.itacademy.aqa.webDriver.Browser;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;

public class TVsPage extends BaseOnlinerPage{

    private static final By TV_HEADER_LOCATOR = By.xpath("//h1[contains(text(),'Телевизоры')]");
    private ManufacturesFilter manufacturesFilter;
    By MANUFACTURES_FILTER_TITLE_LOCATOR = By.xpath("//*[@class='catalog-form__label-title'][.='Производитель']");
    By NEXT_30_ITEMS_BUTTON_LOCATOR = By.className("catalog-pagination__main");
    By TV_TITLE_1_LOCATOR = By.xpath("(//*[@class='catalog-form__description catalog-form__description_primary catalog-form__description_base-additional catalog-form__description_font-weight_semibold catalog-form__description_condensed-other'])[1]");
    By TV_ALL_TITLES_ON_PAGE_LOCATOR = By.xpath("//*[@class='catalog-form__description catalog-form__description_primary catalog-form__description_base-additional catalog-form__description_font-weight_semibold catalog-form__description_condensed-other']");
    public static final long DEFAULT_TIMEOUT = 5L;


    public TVsPage() {

        manufacturesFilter = new ManufacturesFilter();
    }

    public ManufacturesFilter getManufacturesFilter() {

        return manufacturesFilter;
    }

    @Override
    public boolean isPageOpened() {

        try {
            WebElement tvHeader = Browser.waitForElementToBeVisible(TV_HEADER_LOCATOR);
            return tvHeader.isDisplayed();
        } catch (NotFoundException e) {
            return false;
        }
    }

    public boolean isManufacturesFilterDisplayed() {
        manufacturesFilter = new ManufacturesFilter();
        try {
            WebElement manufactureFilterTitle = Browser.waitForElementToBeClickable(MANUFACTURES_FILTER_TITLE_LOCATOR);
            return manufactureFilterTitle.isDisplayed();
        } catch (NotFoundException e) {
            return false;
        }
    }

    public List<WebElement> getAllTvTitlesOnPage() {
        WebDriverWait wait = new WebDriverWait(Browser.getWebDriver(), DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(TV_TITLE_1_LOCATOR));
        return Browser.getWebDriver().findElements(TV_ALL_TITLES_ON_PAGE_LOCATOR);
    }

    public void clickNext30ItemsButton() {
        WebElement next30ItemsButton = Browser.waitForElementToBeClickable(NEXT_30_ITEMS_BUTTON_LOCATOR);
        next30ItemsButton.click();
    }


    public boolean isCorrectModelsAreDisplayed(ManufactureFiltersEnum manufactureFiltersEnum) {

        // Находим первый элемент из списка

// На всех страницах проверяем, что название выведеных товаров содержит LG
        while (true) {
            WebDriverWait wait = new WebDriverWait(Browser.getWebDriver(), DEFAULT_TIMEOUT);
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

