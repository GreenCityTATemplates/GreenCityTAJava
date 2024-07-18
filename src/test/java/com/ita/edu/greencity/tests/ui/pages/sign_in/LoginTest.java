package com.ita.edu.greencity.tests.ui.pages.sign_in;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.sign_in.SignInComponent;
import io.qameta.allure.Description;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest extends TestRun {

    @Description("go to 'sign in' form")
    @BeforeMethod
    public void beforeMethod(ITestContext testContext) {
        super.beforeMethod(testContext);
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        header.clickSignIn();
    }

    @Description("test whether user can login with valid credentials")
    @Test
    public void loginTest() {
        SignInComponent signin = new SignInComponent(driver);
        HeaderSignedInComponent header = new HeaderSignedInComponent(driver);
        SoftAssert softAssert = new SoftAssert();

        String expectedTitle = "It's even easier than before!";
        String expectedUserName = provider.getUserName();

        String actualTitle = signin
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn()
                .getHomePageTitle();

        String actualUserName = header.getUserName();

        softAssert.assertEquals(actualUserName, expectedUserName, "verify whether correct account is logined");
        softAssert.assertEquals(actualTitle, expectedTitle, "verify whether homepage is displayed");

        softAssert.assertAll();
    }
}
