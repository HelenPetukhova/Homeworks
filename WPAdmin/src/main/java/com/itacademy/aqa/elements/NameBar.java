package com.itacademy.aqa.elements;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.enums.NameBarOptionEnum;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class NameBar {
    private static final Logger logger = Logger.getLogger(NameBar.class);

    private static final By NAME_BAR_LOCATOR = By.xpath("//*[@id='wp-admin-bar-my-account']/a");
    private static final String NAME_BAR_OPTION_TEMPLATE_LOCATOR = "//*[@id='wp-admin-bar-my-account']//li[@id='%s']/a";

    public NameBar() {
    }


    @Step("Click 'Log Out' option in 'Name' bar")
    public void clickLogOut() {
        logger.info("User is logging out through Name bar");
        clickOnItem(NameBarOptionEnum.LOG_OUT);

    }

    @Step("Log Out using Name Bar")
    public void clickOnItem(NameBarOptionEnum nameBarOptionEnum) {
        logger.info("Clicking of Name Bar option " + nameBarOptionEnum);
        logger.debug("Finding Name bar by locator: " + NAME_BAR_LOCATOR);
        WebElement nameBar = Browser.waitForElementToBeClickableAndFind(NAME_BAR_LOCATOR);

        Actions actions = new Actions(Browser.getWebDriver());

        logger.info("Hovering over Name Bar");
        actions.moveToElement(nameBar).perform();

        String nameBarOptionLocator = String.format(NAME_BAR_OPTION_TEMPLATE_LOCATOR, nameBarOptionEnum.getValue());
        logger.debug("Creating locator for Name dropdown list option by locator: " + nameBarOptionLocator);
        By xPath = By.xpath(nameBarOptionLocator);

        WebElement nameBarOption = Browser.waitForElementToBeClickableAndFind(xPath);
        nameBarOption.click();
        logger.info("Name Bar option is clicked " + nameBarOptionEnum);
    }


}
