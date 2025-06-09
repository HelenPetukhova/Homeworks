package com.itacademy.aqa.elements;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.enums.BulkActionsEnum;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



public class BulkActionsDdl {
    private static final Logger logger = Logger.getLogger(BulkActionsDdl.class);
    public static final By BULK_ACTIONS_DDL_LOCATOR = By.id("bulk-action-selector-top");
    public static final String BULK_ACTIONS_OPTION_TEMPLATE_LOCATOR = "//*[@id='bulk-action-selector-top']/option[@value='%s']";

    public BulkActionsDdl() {
    }


    @Step("Select the option in 'Bulk Action' ddl: '{bulkActionsEnum}'")
    public void selectBulkAction(BulkActionsEnum bulkActionsEnum) {
        logger.info("Selecting an option in Bulk Actions dropdown list: " + bulkActionsEnum);
        logger.debug("Finding clickable Bulk Actions dropdown list with locator " + BULK_ACTIONS_DDL_LOCATOR);

        WebElement bulkActionsDdl = Browser.waitForElementToBeClickableAndFind(BULK_ACTIONS_DDL_LOCATOR);
        Browser.getJavascriptExecutor().executeScript("arguments[0].scrollIntoView({block: 'center'});", bulkActionsDdl);
        bulkActionsDdl.click();
        logger.info("Bulk Actions dropdown list is expanded");


        By bulkActionsOptionLocation = By.xpath(String.format(BULK_ACTIONS_OPTION_TEMPLATE_LOCATOR, bulkActionsEnum.getValue()));
        logger.debug("Creating locator for option of Bulk Actions ddl: " + bulkActionsOptionLocation);
        WebElement bulkActionOption = Browser.waitForElementToBeClickableAndFind(bulkActionsOptionLocation);

        bulkActionOption.click();
        logger.info(bulkActionsEnum + " is selected in Bulk Actions dropdown list");
    }


}
