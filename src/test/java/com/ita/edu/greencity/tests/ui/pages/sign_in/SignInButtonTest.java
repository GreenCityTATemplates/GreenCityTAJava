package com.ita.edu.greencity.tests.ui.pages.sign_in;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.sign_in.SignInComponent;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SignInButtonTest extends TestRun {

    @BeforeMethod
    public void beforeMethod(ITestContext testContext) {
        super.beforeMethod(testContext);
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        header.clickSignIn();
    }

    @Description("test whether 'show-hide password' button works properly")
    @Test
    public void showHidePasswordTest() {
        SignInComponent signin = new SignInComponent(driver);
        SoftAssert softAssert = new SoftAssert();

        String expected_password = "password";
        String expected_text = "text";
        String actual = signin.getPasswordType();

        softAssert.assertEquals(actual, expected_password, "verify if the password is hidden");

        actual = signin
                .clickShowHidePassword()
                .getPasswordType();
        softAssert.assertEquals(actual, expected_text, "verify if the password is shown");

        actual = signin
                .clickShowHidePassword()
                .getPasswordType();
        softAssert.assertEquals(actual, expected_password, "verify if the password is hidden");

        softAssert.assertAll();
    }

    @Description("test whether 'sign up' link works properly")
    @Test
    public void signUpLinkTest() {
        SignInComponent signin = new SignInComponent(driver);

        String expected = "Hello!\n" +
                "Please enter your details to sign up";
        String actual = signin
                .clickSignUp()
                .getTextOfTitle();

        Assert.assertEquals(actual, expected);
    }

    @Description("test whether 'forgot password' link works properly")
    @Test
    public void forgotPasswordLinkTest() {
        SignInComponent signin = new SignInComponent(driver);

        String expected = "Problems sign in?";

        String actual = signin
                .clickForgotPassword()
                .getTitle();

        Assert.assertEquals(actual, expected);
    }
}
