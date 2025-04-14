package com.itacademy.aqa.elements;

import com.itacademy.aqa.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;



public class ManufacturesFilter {


    By MANUFACTURES_FILTER_TITLE_LOCATOR = By.xpath("//*[@class='catalog-form__label-title'][.='Производитель']");
    private static final String ITEM_PATTERN_CHECK_BOX = "//*[@class='catalog-form__label-title'][.='Производитель']/../../../following-sibling::div//li[.='%s']";
    private static final String ITEM_PATTERN_HEADER = "//*[@class='catalog-form__title catalog-form__title_big-alter catalog-form__title_nocondensed'][contains(text(),'Телевизоры %s')]";


    public ManufacturesFilter() {

    }

  public void selectOption(ManufactureFiltersEnum manufactureFiltersEnum){
      String xPath = String.format(ITEM_PATTERN_CHECK_BOX, manufactureFiltersEnum.getValue());
      By MODEL_TV_CHECKBOX_LOCATOR = By.xpath(xPath);
      WebElement manufactureFilterTitle = Browser.findWebElement(MANUFACTURES_FILTER_TITLE_LOCATOR);

      // Проскролить, чтобы не мешал "Найдено... товаров" элемент
      Browser.scrollToElement(manufactureFilterTitle);

      WebElement modelTvCheckbox = Browser.waitForElementToBeClickable(MODEL_TV_CHECKBOX_LOCATOR);
      modelTvCheckbox.click();

      // Проверяем, что страница перегрузилась и фильтр применен
      String xPath2 = String.format(ITEM_PATTERN_HEADER, manufactureFiltersEnum.getValue());
      By TV_SELECTED_HEADER_LOCATOR = By.xpath(xPath2);
      WebElement tvSelectedHeader = Browser.waitForElementToBeVisible(TV_SELECTED_HEADER_LOCATOR);
  }

}
