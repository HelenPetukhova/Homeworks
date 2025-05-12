package com.itacademy.aqa.pages;

import com.itacademy.aqa.config.Browser;
import com.itacademy.aqa.elements.LeftMenu;
import com.itacademy.aqa.elements.NameBar;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;

import java.nio.file.Path;

public class MediaPage extends BaseWPPage {
    LeftMenu leftMenu;
    private NameBar nameBar;

    private static final By MEDIA_LIBRARY_LOCATOR = By.xpath("//*[@class='wp-heading-inline'][contains(text(),'Media Library')]");
    private static final By ADD_NEW_MEDIA_FILE_BUTTON_LOCATOR = By.xpath("//a[contains(@class,'page-title-action')]");
    private static final By SELECT_FILES_BUTTON_LOCATOR = By.xpath("//*[@class='upload-ui']/button");
    private static final By INPUT_FIELD_LOCATOR = By.xpath("//input[@type='file']");
    private static final By UPLOADED_FILE_LOCATOR = By.xpath("(//img[contains(@src,'camera')])[1]");
    private static final By BULK_SELECT_BUTTON_LOCATOR = By.xpath("//button[contains(text(),'Bulk select')]");
    private static final By UPLOADED_FILE_THUMBNAIL_LOCATOR = By.xpath("(//img[contains(@src,'camera')])[1]/ancestor::div[contains(@class,'thumbnail')]");
    private static final By FILE_CHECK_LOCATOR = By.xpath("//img[contains(@src,'camera')]/../../../../button[@class='check']");
    private static final By DELETE_PERMANENTLY_BUTTON_LINK = By.xpath("//*[@class='media-toolbar-secondary']/button[.='Delete permanently']");


    public MediaPage() {
        leftMenu = new LeftMenu();
        nameBar = new NameBar();

    }


    public LeftMenu getLeftMenu() {
        return leftMenu;
    }

    public NameBar getNameBar() {
        return nameBar;
    }


    @Override
    public boolean isPageOpened() {
        try {
            WebElement mediaLibraryTitle = Browser.waitForVisibilityOfElementLocatedAndFind(MEDIA_LIBRARY_LOCATOR);
            return mediaLibraryTitle.isDisplayed();
        } catch (RuntimeException ex) {
            return false;
        }

    }

    public void addNewMediaFileButtonClick() {
        WebElement addNewMediaFileButton = Browser.waitForElementToBeClickableAndFind(ADD_NEW_MEDIA_FILE_BUTTON_LOCATOR);
        addNewMediaFileButton.click();
    }

    public void selectFilesButtonClick() {
        WebElement selectFilesButton = Browser.waitForElementToBeClickableAndFind(SELECT_FILES_BUTTON_LOCATOR);
        selectFilesButton.click();
    }

    public boolean uploadNewFile() {
        addNewMediaFileButtonClick();

        WebElement fileInput = Browser.getWebDriver().findElement(INPUT_FIELD_LOCATOR);

        Path path = Path.of("camera.jpg");
        String absolutePath = path.toAbsolutePath().toString();

        fileInput.sendKeys(absolutePath);
        try {
            WebElement uploadedFileThumbnail = Browser.waitForVisibilityOfElementLocatedAndFind(UPLOADED_FILE_LOCATOR);
            return uploadedFileThumbnail.isDisplayed();
        } catch (RuntimeException ex) {
            return false;
        }
    }


    public void closeAlertIfPresent() {
        try {
            Alert alert = Browser.getWebDriver().switchTo().alert();
            alert.accept(); // или alert.dismiss()
        } catch (NoAlertPresentException e) {

        }
    }

    public void deleteUploadedFile() {
        WebElement bulkSelectButton = Browser.waitForElementToBeClickableAndFind(BULK_SELECT_BUTTON_LOCATOR);
        bulkSelectButton.click();
        WebElement uploadedFileThumbnail = Browser.waitForElementToBeClickableAndFind(UPLOADED_FILE_THUMBNAIL_LOCATOR);
        uploadedFileThumbnail.click();
        WebElement fileCheckMark = Browser.waitForVisibilityOfElementLocatedAndFind(FILE_CHECK_LOCATOR);
        WebElement deletePermanentlyButton = Browser.waitForElementToBeClickableAndFind(DELETE_PERMANENTLY_BUTTON_LINK);
        deletePermanentlyButton.click();
        closeAlertIfPresent();
    }
}

