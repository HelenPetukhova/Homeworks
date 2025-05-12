package com.itacademy.aqa.elements;

import com.itacademy.aqa.config.Browser;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



public class BulkActionsDdl {
    private static Logger logger = Logger.getLogger(BulkActionsDdl.class);

    public static final By BULK_ACTIONS_DDL_LOCATOR = By.id("bulk-action-selector-top");
    public static final String BULK_ACTIONS_OPTION_TEMPLATE_LOCATOR = "//*[@id='bulk-action-selector-top']/option[@value='%s']";

    public BulkActionsDdl() {
    }


    public void selectBulkAction(BulkActionsEnum bulkActionsEnum) {
        logger.info("Selecting an option in Bulk Actions dropdown list: " + bulkActionsEnum);

        logger.debug("Finding clickable Bulk Actions dropdown list with locator " + BULK_ACTIONS_DDL_LOCATOR);
        WebElement bulkActionsDdl = Browser.waitForElementToBeClickableAndFind(BULK_ACTIONS_DDL_LOCATOR);
        bulkActionsDdl.click();
        logger.info("Bulk Actions dropdown list is expanded");


        By BULK_ACTIONS_OPTION_LOCATOR = By.xpath(String.format(BULK_ACTIONS_OPTION_TEMPLATE_LOCATOR, bulkActionsEnum.getValue()));
        logger.debug("Creating locator for option of Bulk Actions ddl: " + BULK_ACTIONS_OPTION_LOCATOR);
        WebElement bulkActionOption = Browser.waitForElementToBeClickableAndFind(BULK_ACTIONS_OPTION_LOCATOR);

        bulkActionOption.click();
        logger.info(bulkActionsEnum + " is selected in Bulk Actions dropdown list");
    }


}
