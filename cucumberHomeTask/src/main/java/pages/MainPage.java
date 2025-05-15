package pages;


import com.itacademy.aqa.webDriver.Browser;
import elements.CookiesPopup;
import elements.MainMenu;
import elements.MainMenuItemEnum;
import elements.SecondaryMainPageMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;


public class MainPage extends BaseOnlinerPage {

    private elements.SecondaryMainPageMenu secondaryMainPageMenu;
    private static final By MAIN_PAGE_LOCATOR = By.className("widget-item");
    private CookiesPopup cookiesPopup;
    private MainMenu mainMenu;


    public MainPage() {
        super();
        secondaryMainPageMenu = new SecondaryMainPageMenu();
        cookiesPopup = new CookiesPopup();
        mainMenu = new MainMenu();

    }


    public SecondaryMainPageMenu getSecondaryMainPageMenu() {
        return secondaryMainPageMenu;
    }

    public CookiesPopup getCookiesPopup() {
        return cookiesPopup;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    @Override
    public boolean isPageOpened() {
        try {
            return Browser.waitForElementToBeVisible(MAIN_PAGE_LOCATOR).isDisplayed();
        } catch (NotFoundException ex) {
            return false;
        }
    }



}
