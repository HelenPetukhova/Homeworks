package com.itacademy.aqa.elements;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.enums.ItemStatusEnum;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class StatusPopUp {
    private static final By CLOSE_BUTTON_IN_STATUS_POPUP_LOCATOR = By.xpath("//*[@class='components-popover__content']//button");
    private static final By YEAR_INPUT_FIELD_LOCATOR = By.xpath("//input[@id='inspector-input-control-3']");
    private static final By STATUS_POPUP_SCROLL_LOCATOR = By.className("components-popover__content");

    private static Logger logger = Logger.getLogger(StatusPopUp.class);

    public StatusPopUp() {

    }

    public void changeItemStatusTo(ItemStatusEnum itemStatusEnum){
        logger.info("Selecting Status radio-button: " + itemStatusEnum);

        String statusRadioButtonLocator = String.format("//*[@class='components-radio-control__option']//*[text()='%s']", itemStatusEnum.getValue());
        WebElement statusRadioButton = Browser.waitForElementToBeClickableAndFind(By.xpath(statusRadioButtonLocator));
        statusRadioButton.click();

        if (itemStatusEnum.equals(ItemStatusEnum.SCHEDULED)) {
            logger.info("Setting date for page with scheduled status");
            WebElement statusPopUpScroll = Browser.waitForElementToBeClickableAndFind(STATUS_POPUP_SCROLL_LOCATOR);
            WebElement yearInputField = Browser.waitForElementToBeClickableAndFind(YEAR_INPUT_FIELD_LOCATOR);
            Browser.getJavascriptExecutor().executeScript("arguments[0].scrollIntoView({block: 'center'});", yearInputField);

            String currentYearString = yearInputField.getAttribute("value");
            Integer currentYear = Integer.valueOf(currentYearString);
            String scheduledYear = String.valueOf(currentYear + 1);
            //        yearInputField.clear();
            yearInputField.click(); // фокусируем
            yearInputField.sendKeys(Keys.chord(Keys.CONTROL, "a")); // выделить всё
            yearInputField.sendKeys(Keys.BACK_SPACE);

            logger.info("Setting year that is 1 year more than set by default");
            yearInputField.sendKeys(scheduledYear);
            System.out.println("Input value: " + yearInputField.getAttribute("value"));
            WebElement closeButtonInStatusPopUp = Browser.waitForElementToBeClickableAndFind(CLOSE_BUTTON_IN_STATUS_POPUP_LOCATOR);
            Browser.getJavascriptExecutor().executeScript("arguments[0].scrollIntoView({block: 'center'});", closeButtonInStatusPopUp);

        }
        logger.info("Closing 'Change status' pop-up");
        WebElement closeButtonInStatusPopUp = Browser.waitForElementToBeClickableAndFind(CLOSE_BUTTON_IN_STATUS_POPUP_LOCATOR);
        closeButtonInStatusPopUp.click();


    }
}
