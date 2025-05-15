package elements;

import com.itacademy.aqa.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class MainMenu {
    private static final String MAIN_MENU_ITEM_TEMPLATE_LOCATOR = "//*[@class='b-main-navigation__text'][text()='%s']";
    private static final By MAIN_MENU_SUBTITLES_LOCATOR = By.xpath("//*[contains(@class,'b-main-navigation__dropdown_visible')]//*[@class='b-main-navigation__dropdown-title-link']");
    private static final String SUB_MENU_ITEMS_TEMPLATE_LOCATOR = "//*[contains(@class,'b-main-navigation__dropdown_visible')]//*[@class='b-main-navigation__dropdown-title']//*[.='%s']//..//..//*[@class='b-main-navigation__dropdown-advert-sign']";
    public MainMenu(){

    }

//
//    public void hoverOverMainMenuItem(MainMenuItemEnum mainMenuItemEnum){
//        String xPath = String.format(MAIN_MENU_ITEM_TEMPLATE_LOCATOR, mainMenuItemEnum);
//        By MAIN_MENU_ITEM_LOCATOR = By.xpath(xPath);
//        WebElement mainMenuItem = Browser.waitForElementToBeVisible(MAIN_MENU_ITEM_LOCATOR);
//
//        Actions actions = new Actions(Browser.getWebDriver());
//        actions.moveToElement(mainMenuItem).perform();
//
//    }


    public void hoverOverMainMenuItem(String menuItemTitle){
        String xPath = String.format(MAIN_MENU_ITEM_TEMPLATE_LOCATOR, menuItemTitle);
        By MAIN_MENU_ITEM_LOCATOR = By.xpath(xPath);
        WebElement mainMenuItem = Browser.waitForElementToBeVisible(MAIN_MENU_ITEM_LOCATOR);

        Actions actions = new Actions(Browser.getWebDriver());
        actions.moveToElement(mainMenuItem).perform();

    }

    public List<String> getMainMenuSubTitles(){
        List<WebElement> mainMenuSubTitlesElements = Browser.getWebDriver().findElements(MAIN_MENU_SUBTITLES_LOCATOR);
        List<String> mainMenuSubTitlesList = new ArrayList<>();
        for (WebElement element : mainMenuSubTitlesElements) {
            mainMenuSubTitlesList.add(element.getText().trim());
            }
        return mainMenuSubTitlesList;
    }


    public List<String> getSubmenuItems(String subTitleOfSection){
        String xPath = String.format(SUB_MENU_ITEMS_TEMPLATE_LOCATOR, subTitleOfSection);
        By SUB_MENU_ITEMS_LOCATOR = By.xpath(xPath);
        List<WebElement> submenuItemsElements = Browser.getWebDriver().findElements(SUB_MENU_ITEMS_LOCATOR);
        List<String> submenuItemsList = new ArrayList<>();
        for (WebElement element : submenuItemsElements) {
            submenuItemsList.add(element.getText().trim());
        }
        return submenuItemsList;
    }
}
