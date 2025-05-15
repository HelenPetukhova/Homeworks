package com.itacademy.aqa;

import com.itacademy.aqa.webDriver.Browser;
import com.itacademy.aqa.webDriver.Configuration;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.MainPage;

public class MyStepdefs {

    MainPage mainPage;

    @Given("Main page is opened")
    public void mainPageIsOpened() {

        Browser.getWebDriver().get(Configuration.getBaseUrl());

    }

    @When("User hover over {string} menu item")
    public void userHoverOverMenuItem(String menuItem) {
     mainPage = new MainPage();
     mainPage.getMainMenu().hoverOverMainMenuItem(menuItem);
    }


    @Then("Submenu titles contain {string} title")
    public void submenuTitlesContainTitle(String expectedSubmenuTitle) {

        Assert.assertTrue(mainPage.getMainMenu().getMainMenuSubTitles().contains(expectedSubmenuTitle));

    }



    @And("{string} submenu item is displayed in {string} section")
    public void submenuItemIsDisplayedInSection(String submenuItem, String subTitleOfSection) {
//        System.out.println(mainPage.getMainMenu().getSubmenuItems(subTitleOfSection));
//        System.out.println(submenuItem);
        Assert.assertTrue("Submenu '" + submenuItem + "' wasn't found in '" + subTitleOfSection + "' section",
                mainPage.getMainMenu().getSubmenuItems(subTitleOfSection).contains(submenuItem));
    }

    @After
    public void tearDown() {
        Browser.close();
    }
}

