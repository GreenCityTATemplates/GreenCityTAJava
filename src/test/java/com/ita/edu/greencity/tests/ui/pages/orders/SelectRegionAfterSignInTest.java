package com.ita.edu.greencity.tests.ui.pages.orders;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.orders.SelectRegion;
import jdk.jfr.Description;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.stream.Collectors;

public class SelectRegionAfterSignInTest extends TestRun {
    public final static String defaultRegion = "Kyiv";
    public final static String secondRegion = "Kyiv region";


    @Description("Verify equality of regions from pop-up and OrderDetailsPage")
    @Test
    public void changeRegionAfterSignIn() {
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        String actual = header.clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignInAfterCallUpCourier()
                .chooseRegionByValue(defaultRegion)
                .clickOnContinueButton().getLocationFromTitle().substring(11).trim();
        Assert.assertEquals(actual, defaultRegion);
    }

    @Description("Verify correct redirection from pop-up to OderDetails page after pressing Continue button")
    @Test
    public void checkRedirectionAfterContinueButton() {
        String expected = "https://ita-social-projects.github.io/GreenCityClient/#/ubs/order";
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        header.clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignInAfterCallUpCourier()
                .clickOnContinueButton();
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }

    @Description("Verify correct redirection from pop-up to HomePage page after pressing Back button")
    @Test
    public void checkRedirectionAfterBackButton() {
        String expected = "https://ita-social-projects.github.io/GreenCityClient/#/ubs";
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        header.clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignInAfterCallUpCourier()
                .clickOnBackButton();
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }

    @Description("Verify correct redirection from pop-up to HomePage page after pressing Close button")
    @Test
    public void checkRedirectionAfterCloseButton() {
        String expected = "https://ita-social-projects.github.io/GreenCityClient/#/ubs";
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        header.clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignInAfterCallUpCourier()
                .clickOnCloseButton();
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }

    @Description("Verify availability of correct options of dropdown menu and correctness of default value at pop-up")
    @Test
    public void checkCorrectDefaultDropdownValue() {
        SoftAssert softAssert = new SoftAssert();
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        SelectRegion selectRegion = header.clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignInAfterCallUpCourier();
        softAssert.assertEquals(selectRegion.getListOfRegions().get(0).getText(), defaultRegion, "Incorrect default value of dropdown");
        softAssert.assertTrue(selectRegion.getListOfRegions().stream().map(WebElement::getText).collect(Collectors.toList()).contains(secondRegion), "Dropdown do not have 'Kyiv region' option");
        softAssert.assertAll();
    }

}
