package com.itacademy.aqa.pageObject.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ManufacturesFilter {

    WebDriver webDriver;

    By MANUFACTURES_FILTER_TITLE_LOCATOR = By.xpath("//*[@class='catalog-form__label-title'][.='Производитель']");
    private static final String ITEM_PATTERN_CHECK_BOX = "//*[@class='catalog-form__label-title'][.='Производитель']/../../../following-sibling::div//li[.='%s']";
    private static final String ITEM_PATTERN_HEADER = "//*[@class='catalog-form__title catalog-form__title_big-alter catalog-form__title_nocondensed'][contains(text(),'Телевизоры %s')]";


    public ManufacturesFilter(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

  public void selectOption(ManufactureFiltersEnum manufactureFiltersEnum){
      String xPath = String.format(ITEM_PATTERN_CHECK_BOX, manufactureFiltersEnum.getValue());
      By LG_CHECKBOX_LOCATOR = By.xpath(xPath);
      WebElement manufactureFilterTitle = webDriver.findElement(MANUFACTURES_FILTER_TITLE_LOCATOR);

      // Проскролить, чтобы не мешал "Найдено... товаров" элемент
      ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);",manufactureFilterTitle);

      WebDriverWait wait = new WebDriverWait(webDriver, 30);

      wait.until(ExpectedConditions.elementToBeClickable(LG_CHECKBOX_LOCATOR));
      WebElement lgTvCheckbox = webDriver.findElement(LG_CHECKBOX_LOCATOR);
      lgTvCheckbox.click();

      // Проверяем, что страница перегрузилась и фильтр применен
      String xPath2 = String.format(ITEM_PATTERN_HEADER, manufactureFiltersEnum.getValue());
      By TV_SELECTED_HEADER_LOCATOR = By.xpath(xPath2);
      wait.until(ExpectedConditions.visibilityOfElementLocated(TV_SELECTED_HEADER_LOCATOR));
  }

}
