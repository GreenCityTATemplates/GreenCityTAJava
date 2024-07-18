package com.ita.edu.greencity.tests.ui.pages.sign_up;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.sign_up.SignUpComponent;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CloseSignUpPopUpTest extends TestRun {
    @Test
    @Description("Check closing of sign-up pop up by clicking on cross")
    @Issue("29")
    @Severity(SeverityLevel.NORMAL)
    @Link("https://jira.softserve.academy/browse/GC-482")
    public void checkEngLocalSignUpTitle() {
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        header.clickSignUp();
        String expected = "UBS Courier";
        UbsHomePage signUpComponent = new SignUpComponent(driver).clickOnExitButton();
        String actual = signUpComponent.getTitleH1Text();
        Assert.assertEquals(actual, expected);
    }
}
