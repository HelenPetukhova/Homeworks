package com.itacademy.aqa;

import com.itacademy.aqa.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class ActionsTest {

    @BeforeMethod
    public void initialize() {

    }


    @Test
    public void resizable() throws InterruptedException {
        Browser.getWebDriver().get("https://jqueryui.com/resizable/");
        WebElement frame = Browser.getWebDriver().findElement(By.className("demo-frame"));
        Browser.getWebDriver().switchTo().frame(frame);
        WebElement source = Browser.getWebDriver().findElement(By.id("resizable"));
        int initialWidth = source.getSize().getWidth();
        int initialHeight = source.getSize().getHeight();

        System.out.println("Initial width: " + source.getSize().getWidth());
        System.out.println("Initial height: " + source.getSize().getHeight());

        WebElement grip = Browser.getWebDriver().findElement(By.xpath("//*[@class='ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se']"));
        Actions actions = new Actions(Browser.getWebDriver());
        actions.clickAndHold(grip).moveByOffset(250, 50).release().perform();
        Thread.sleep(5000);
        System.out.println("Final width: " + source.getSize().getWidth());
        System.out.println("Final height: " + source.getSize().getHeight());
        Assert.assertEquals(source.getSize().getWidth(), initialWidth + 250, "Width is not correct");
        Assert.assertEquals(source.getSize().getHeight(), initialHeight + 50, "Height is not correct");
        Browser.getWebDriver().switchTo().defaultContent();

    }

    @Test
    public void selectable() throws InterruptedException {
        Browser.getWebDriver().get("https://jqueryui.com/selectable/");
        WebElement frame = Browser.getWebDriver().findElement(By.className("demo-frame"));
        Browser.getWebDriver().switchTo().frame(frame);
        WebElement item2 = Browser.getWebDriver().findElement(By.xpath("//*[@id='selectable']/li[.='Item 2']"));
        WebElement item3 = Browser.getWebDriver().findElement(By.xpath("//*[@id='selectable']/li[.='Item 3']"));
        WebElement item5 = Browser.getWebDriver().findElement(By.xpath("//*[@id='selectable']/li[.='Item 5']"));
        WebElement item7 = Browser.getWebDriver().findElement(By.xpath("//*[@id='selectable']/li[.='Item 7']"));
        Actions actions = new Actions(Browser.getWebDriver());
        actions.keyDown(Keys.CONTROL)
                .click(item2)
                .click(item3)
                .click(item5)
                .click(item7)
                .keyUp(Keys.CONTROL)
                .build()
                .perform();
        Thread.sleep(5000);

        List<String> titlesOfSelectedItems = Arrays.asList(item2.getText(), item3.getText(), item5.getText(), item7.getText());

        By ALL_SELECTED_ITEMS_LOCATOR = By.xpath("//li[@class='ui-widget-content ui-selectee ui-selected']");
        List<WebElement> allActualSelectedItems = Browser.getWebDriver().findElements(ALL_SELECTED_ITEMS_LOCATOR);
        List<String> allActualSelectedItemTitles = allActualSelectedItems.stream().map(WebElement::getText).toList();

        Assert.assertEquals(allActualSelectedItems.size(), 4, "The number of selected items is not correct");
        Assert.assertTrue(allActualSelectedItemTitles.containsAll(titlesOfSelectedItems), "Not all selected items are displayed as selected");

        actions.keyDown(Keys.CONTROL)
                .click(item2)
                .click(item5)
                .keyUp(Keys.CONTROL)
                .build()
                .perform();
        Thread.sleep(5000);

        List<String> titlesOfSelectedItems2 = Arrays.asList(item3.getText(), item7.getText());
        allActualSelectedItems = Browser.getWebDriver().findElements(ALL_SELECTED_ITEMS_LOCATOR);
        allActualSelectedItemTitles = allActualSelectedItems.stream().map(WebElement::getText).toList();

        Assert.assertEquals(allActualSelectedItems.size(), 2, "The number of selected items is not correct");
        Assert.assertTrue(allActualSelectedItemTitles.containsAll(titlesOfSelectedItems2), "Not all selected items are displayed as selected");

    }

    @Test(dataProvider = "expectedPositionTitleData")
    public void sortable(String numberLocator, String itemTitle) throws InterruptedException {
        Browser.getWebDriver().get("https://jqueryui.com/sortable/");
        WebElement frame = Browser.getWebDriver().findElement(By.className("demo-frame"));
        Browser.getWebDriver().switchTo().frame(frame);
        Thread.sleep(1000);
        By ALL_ITEMS_LOCATOR = By.xpath("//li[@class='ui-state-default ui-sortable-handle']");

       // List<WebElement> initialListItems = Browser.getWebDriver().findElements(ALL_ITEMS_LOCATOR);
        WebElement item2 = Browser.getWebDriver().findElement(By.xpath("//li[@class='ui-state-default ui-sortable-handle'][.='Item 2']"));
        //  WebElement target = Browser.getWebDriver().findElement(By.xpath("//li[@class='ui-state-default ui-sortable-handle'][6]"));
        //  WebElement item2arrow = Browser.getWebDriver().findElement(By.xpath("//ul[@id='sortable']/li[.='Item 2']/span[@class='ui-icon ui-icon-arrowthick-2-n-s']"));
        // moves to 6th element
        Actions actions = new Actions(Browser.getWebDriver());
        actions.
                clickAndHold(item2).pause(1000)
                .moveByOffset(0, 180).pause(1000).release().build().perform();
        Thread.sleep(5000);

        Assert.assertEquals(Browser.getWebDriver().findElement(By.xpath("//li[@class='ui-state-default ui-sortable-handle']["+numberLocator+"]")).getText(),
                itemTitle, "The order is wrong");

    }


    @AfterTest
    public void tearDown() {
        Browser.close();
    }


    @DataProvider
    public static Object[][] expectedPositionTitleData() {
        return new Object[][]{
                {"1", "Item 1"},
                {"2", "Item 3"},
                {"3", "Item 4"},
                {"4", "Item 5"},
                {"5", "Item 6"},
                {"6", "Item 2"},
                {"7", "Item 7"}

        };
    }
}

