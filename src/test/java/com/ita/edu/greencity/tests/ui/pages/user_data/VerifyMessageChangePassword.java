package com.ita.edu.greencity.tests.ui.pages.user_data;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class VerifyMessageChangePassword extends TestRun {
    @BeforeMethod
    public void loginToUBS() {
        new HeaderSignedOutComponent(driver).clickSignIn()
                .inputEmail(provider.getEmailForUserData())
                .inputPassword(provider.getPasswordForUserData())
                .clickSignInAfterCallUpCourier()
                .chooseRegionByValue("Kyiv")
                .clickOnContinueButton();
    }


    @DataProvider(name = "dataProviderToVerifyMessageWhenPasswordsNotMatch")
    private Object[][] dataProviderToVerifyMessageWhenPasswordsNotMatch() {
        return new Object[][]{
                {"1245367000Qw1/", "14567000Qw1/"},
                {"mypas3swoRd1+", "mypasswoRd1"},
        };
    }


    @Test
    @Description("check the error message when the new password is the same as the old one")
    @Issue("92")
    @Severity(SeverityLevel.TRIVIAL)
    public void verifyMessageWhenChangingPasswordToTheSame() {
        HeaderSignedInComponent header = new HeaderSignedInComponent(driver);
        String actual = header.clickUserMenu()
                .clickUbsUser()
                .getUbsUserPage()
                .clickOnUserDataButton()
                .clickOnChangePasswordButton()
                .enterOldPassword(provider.getPassword())
                .enterNewPassword(provider.getPassword())
                .enterRepeatNewPassword(provider.getPassword())
                .getErrorMessageTheSamePassword();
        String expectedTitle = "Enter the new password!";
        Assert.assertEquals(actual, expectedTitle);
    }

    @Test(dataProvider = "dataProviderToVerifyMessageWhenPasswordsNotMatch")
    @Description("check the error message when the data in 'the new password' and 'repeat the password' fields are different")
    @Issue("93")
    @Severity(SeverityLevel.TRIVIAL)
    public void verifyMessageWhenPasswordsNotMatch(String newPassword, String repeatPassword) {
        HeaderSignedInComponent header = new HeaderSignedInComponent(driver);
        String actual = header.clickUserMenu()
                .clickUbsUser()
                .getUbsUserPage()
                .clickOnUserDataButton()
                .clickOnChangePasswordButton()
                .enterOldPassword(provider.getPassword())
                .enterNewPassword(newPassword)
                .enterRepeatNewPassword(repeatPassword)
                .getErrorMessageDontMatchPassword();
        String expectedTitle = "Passwords do not match!";
        Assert.assertEquals(actual, expectedTitle);
    }

}
