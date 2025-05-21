package com.itacademy.aqa.elements;

import com.itacademy.aqa.config.Browser;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class StatusPopUp {
    private static final By CLOSE_BUTTON_IN_STATUS_POPUP_LOCATOR = By.xpath("//*[@class='components-popover__content']//button");
    private static Logger logger = Logger.getLogger(StatusPopUp.class);

    public StatusPopUp(){

    }

public void changeItemStatusTo(ItemStatusEnum itemStatusEnum){
    String statusRadioButtonLocator = String.format("//*[@class='components-radio-control__option']//*[text()='%s']", itemStatusEnum.getValue());
    WebElement statusRadioButton = Browser.waitForElementToBeClickableAndFind(By.xpath(statusRadioButtonLocator));
    statusRadioButton.click();
    WebElement closeButtonInStatusPopUp = Browser.waitForElementToBeClickableAndFind(CLOSE_BUTTON_IN_STATUS_POPUP_LOCATOR);
    closeButtonInStatusPopUp.click();

}
}
