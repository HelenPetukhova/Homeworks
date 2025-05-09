package com.itacademy.aqa.elements;

import com.itacademy.aqa.config.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



public class BulkActionsDdl {
    public static final By BULK_ACTIONS_DDL_LOCATOR = By.id("bulk-action-selector-top");
    public static final String BULK_ACTIONS_OPTION_TEMPLATE_LOCATOR = "//*[@id='bulk-action-selector-top']/option[@value='%s']";

    public BulkActionsDdl() {
    }


    public void selectBulkAction(BulkActionsEnum bulkActionsEnum) {
        WebElement bulkActionsDdl = Browser.waitForElementToBeClickableAndFind(BULK_ACTIONS_DDL_LOCATOR);
        bulkActionsDdl.click();

        By BULK_ACTIONS_OPTION_LOCATOR = By.xpath(String.format(BULK_ACTIONS_OPTION_TEMPLATE_LOCATOR, bulkActionsEnum.getValue()));

        WebElement bulkActionOption = Browser.waitForElementToBeClickableAndFind(BULK_ACTIONS_OPTION_LOCATOR);

        bulkActionOption.click();

    }


}
