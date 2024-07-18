package com.ita.edu.greencity.tests.ui.pages.header;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class HeaderSignedInTest extends TestRun {

    @BeforeMethod
    public void loginToUBS() {
        HeaderSignedOutComponent headerSignedOut = new HeaderSignedOutComponent(driver);
        headerSignedOut.clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn();
    }

    @Test
    @Description("Test if username displayed correctly")
    @Issue("96")
    @Link("https://jira.softserve.academy/browse/GC-2497")
    public void userNameCorrectnessTest() {
        String expected = provider.getUserName();
        String actual = new HeaderSignedInComponent(driver).getUserName();
        Assert.assertEquals(actual, expected);
    }

    @Test
    @Description("Test if Settings button redirects to correct page")
    @Issue("96")
    @Link("https://jira.softserve.academy/browse/GC-2500")
    public void settingsButtonTest() {
        new HeaderSignedInComponent(driver).clickUserMenu().clickSettings();
        String expected = "https://ita-social-projects.github.io/GreenCityClient/#/profile/29/edit";
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }

    @Test
    @Description("Test if after clicking on SignOut button there is redirection to home page")
    @Issue("96")
    @Link("https://jira.softserve.academy/browse/GC-2498")
    public void signOutButtonTest() {
        SoftAssert softAssert = new SoftAssert();
        new HeaderSignedInComponent(driver).clickUserMenu().clickSignOut();
        driver.navigate().refresh();
        String expected = provider.getUbsHomePageURL();
        String actual = driver.getCurrentUrl();
        softAssert.assertEquals(actual, expected);

        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        boolean signInButtonVisibility = header.checkSignInButtonIsVisible();
        boolean signUpButtonVisibility = header.checkSignUpButtonIsVisible();

        softAssert.assertTrue(signInButtonVisibility, "Button Sign In doesn't present on the header!");
        softAssert.assertTrue(signUpButtonVisibility, "Button Sign Up doesn't present on the header!");
        softAssert.assertAll();
    }
}