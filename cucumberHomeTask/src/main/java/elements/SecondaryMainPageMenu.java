package elements;

import com.itacademy.aqa.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SecondaryMainPageMenu {

    public SecondaryMainPageMenu() {

    }


    private static final String ITEM_PATTERN = "//*[@class='project-navigation__sign'][.='%s']";

    public void clickOnItem(SecondaryMainPageMenuEnum secondaryMainPageMenuEnum) {

        String xPath = String.format(ITEM_PATTERN, secondaryMainPageMenuEnum.getValue());
        By SECONDARY_MENU_ITEM_LOCATION = By.xpath(xPath);

        WebElement secondaryMenuItem = Browser.waitForElementToBeClickable(SECONDARY_MENU_ITEM_LOCATION);  //  переменная - веб-элемент, найденый по локатору - Телевизоры линк
        secondaryMenuItem.click();
    }
}
