package com.ita.edu.greencity.tests.ui.pages.header;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderComponent;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HeaderTest extends TestRun {

    @Test
    @Description("Test redirection to UBS site by clicking on logo")
    @Issue("96")
    public void ubsLogoButtonTest() {
        HeaderComponent header = new HeaderComponent(driver);
        header.clickUbsLogo();
        String expected = provider.getUbsHomePageURL();
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }

    @Test
    @Description("Test redirection to site by clicking on Sorting Rules button")
    @Issue("96")
    @Link("https://jira.softserve.academy/browse/GC-2488")
    public void sortingRulesRedirectionTest() {
        HeaderComponent header = new HeaderComponent(driver);
        header.clickSortingRules().switchToNewTabWithoutClosing();
        String expected = "https://nowaste.com.ua/yak-sortyvaty-na-karantuni/";
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }

    @Test
    @Description("Test redirection to site by clicking on EcoShop button")
    @Issue("96")
    @Link("https://jira.softserve.academy/browse/GC-2489")
    public void ecoShopRedirectionTest() {
        HeaderComponent header = new HeaderComponent(driver);
        header.clickEcoShop().switchToNewTabWithoutClosing();
        String expected = "https://shop.nowaste.com.ua/";
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }

    @Test
    @Description("Test redirection to site by clicking on GreenCity button")
    @Issue("96")
    @Link("https://jira.softserve.academy/browse/GC-2490")
    public void greenCityRedirectionTest() {
        HeaderComponent header = new HeaderComponent(driver);
        header.clickGreenCity();
        String expected = "https://ita-social-projects.github.io/GreenCityClient/#/";
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }

    @Test
    @Description("Test to check switching btw languages and changing language at LocalStorage")
    @Issue("96")
    @Link("https://jira.softserve.academy/browse/GC-2494")
    public void languageSwitcherTest() {
        HeaderComponent header = new HeaderComponent(driver);
        String expectedUA = "ua";
        String expectedEN = "en";
        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        SoftAssert softAssert = new SoftAssert();

        header.clickLanguageSwitcher().languageChoose("ua");
        String actual = localStorage.getItem("language");
        softAssert.assertEquals(actual, expectedUA);

        header.clickLanguageSwitcher().languageChoose("en");
        actual = localStorage.getItem("language");
        softAssert.assertEquals(actual, expectedEN);

        softAssert.assertAll();
    }
}