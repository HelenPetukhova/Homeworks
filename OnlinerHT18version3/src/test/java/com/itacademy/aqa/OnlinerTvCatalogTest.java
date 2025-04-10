package com.itacademy.aqa;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class OnlinerTvCatalogTest {
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
    public void TVCatalogPageCanBeOpenedTest() {

        WebDriverWait wait = new WebDriverWait(webDriver, 10);

        // нужно закрыть поп-ап Принять cookie

        By popupAcceptCookiesButtonLocator = By.id("submit-button");
        wait.until(ExpectedConditions.elementToBeClickable(popupAcceptCookiesButtonLocator));
        WebElement popupAcceptCookiesButton = webDriver.findElement(popupAcceptCookiesButtonLocator);
        popupAcceptCookiesButton.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(popupAcceptCookiesButtonLocator));

        // Найти Телевизоры линк, нажать

        By tvsOnMainPageLocator = By.xpath("//*[@class='project-navigation__sign'][.='Телевизоры']");  // переменная, в которую сохранили локатор для TVCatalog
        wait.until(ExpectedConditions.elementToBeClickable(tvsOnMainPageLocator));
        WebElement tvsOnMainPageLink = webDriver.findElement(tvsOnMainPageLocator);  //  переменная - веб-элемент, найденый по локатору - Телевизоры линк
        tvsOnMainPageLink.click();


// Проверить, что TV Catalog открыт
        By tvHeaderLocator = By.xpath("//h1[contains(text(),'Телевизоры')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(tvHeaderLocator));
        WebElement tvHeader = webDriver.findElement(tvHeaderLocator);
        Assert.assertTrue(tvHeader.isDisplayed(), "TV Catalog is not opened");

    }

    @Test
    public void filterByManufactureTest() {

        WebDriverWait wait = new WebDriverWait(webDriver, 10);

// нужно закрыть поп-ап Принять cookie

        By popupAcceptCookiesButtonLocator = By.id("submit-button");
        wait.until(ExpectedConditions.elementToBeClickable(popupAcceptCookiesButtonLocator));
        WebElement popupAcceptCookiesButton = webDriver.findElement(popupAcceptCookiesButtonLocator);
        popupAcceptCookiesButton.click();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(popupAcceptCookiesButtonLocator));

// Найти Телевизоры линк, нажать

        By tvsOnMainPageLocator = By.xpath("//*[@class='project-navigation__sign'][.='Телевизоры']");  // переменная, в которую сохранили локатор для TVCatalog
        wait.until(ExpectedConditions.elementToBeClickable(tvsOnMainPageLocator));
        WebElement tvsOnMainPageLink = webDriver.findElement(tvsOnMainPageLocator);  //  переменная - веб-элемент, найденый по локатору - Телевизоры линк
        tvsOnMainPageLink.click();


// Ждем, пока TV Catalog будет открыт
        By tvHeaderLocator = By.xpath("//h1[contains(text(),'Телевизоры')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(tvHeaderLocator));

// Проверить, что есть фильтр с производителями
        By manufactureFilterTitleLocator = By.xpath("//*[@class='catalog-form__label-title'][.='Производитель']");
        wait.until(ExpectedConditions.elementToBeClickable(manufactureFilterTitleLocator));
        WebElement manufactureFilterTitle = webDriver.findElement(manufactureFilterTitleLocator);
        Assert.assertTrue(manufactureFilterTitle.isDisplayed(), "Manufacture Filter is displayed");


// Выбрать LG из списка
        By lgTvCheckboxLocator = By.xpath("//*[@class='catalog-form__label-title'][.='Производитель']/../../../following-sibling::div//li[.='LG']");
        // Проскролить, чтобы не мешал "Найдено... товаров" элемент
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", manufactureFilterTitle);

        wait.until(ExpectedConditions.elementToBeClickable(lgTvCheckboxLocator));
        WebElement lgTvCheckbox = webDriver.findElement(lgTvCheckboxLocator);
        lgTvCheckbox.click();

        // Проверяем, что страница перегрузилась и фильтр применен

        By tvLgHeaderLocator = By.xpath("//*[@class='catalog-form__title catalog-form__title_big-alter catalog-form__title_nocondensed'][contains(text(),'Телевизоры LG')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(tvLgHeaderLocator));

// Находим первый элемент из списка
        By tvTitle1Locator = By.xpath("(//*[@class='catalog-form__description catalog-form__description_primary catalog-form__description_base-additional catalog-form__description_font-weight_semibold catalog-form__description_condensed-other'])[1]");

// На всех страницах проверяем, что название выведеных товаров содержит LG
        while (true) {

            By tvAllTitlesOnPageLocator = By.xpath("//*[@class='catalog-form__description catalog-form__description_primary catalog-form__description_base-additional catalog-form__description_font-weight_semibold catalog-form__description_condensed-other']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(tvTitle1Locator));
            List<WebElement> tvAllTitlesOnPage = webDriver.findElements(tvAllTitlesOnPageLocator);
            WebElement firstElement = tvAllTitlesOnPage.get(0);
            for (WebElement element : tvAllTitlesOnPage) {
                Assert.assertTrue(element.getText().contains("LG"), "The item is not LG TVset:" + element.getText());
            }


            // Нажимаем на кнопку "Следующие 30 товаров", пока она появляется
            try {
                By next30ItemsButtonLocator = By.className("catalog-pagination__main");
                wait.until(ExpectedConditions.elementToBeClickable(next30ItemsButtonLocator));
                WebElement next30ItemsButton = webDriver.findElement(next30ItemsButtonLocator);
                next30ItemsButton.click();
                wait.until(ExpectedConditions.invisibilityOf(firstElement));

            } catch (NoSuchElementException | ElementClickInterceptedException ex) {
                break;
            }
        }

    }




    @AfterMethod
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();    // закрываем браузер
        }
    }
}
