package com.ita.edu.greencity.tests.ui.pages.sign_up;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.google_account.GoogleSignInInputEmailPopUp;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.sign_up.SignUpComponent;
import com.ita.edu.greencity.utils.jdbc.entity.EcoNewsUsersEntity;
import com.ita.edu.greencity.utils.jdbc.services.EcoNewsUsersService;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class SignUpWithGoogleTest extends TestRun {

    private final String userEmail = "registertesttest88@gmail.com";
    EcoNewsUsersService ecoNewsUsersService = new EcoNewsUsersService();

    @BeforeTest(description = "Delete user by email if it exists in database before registration")
    public void checkRegisteredUser() {
        EcoNewsUsersEntity user = ecoNewsUsersService.getByEmail(userEmail);
        if (user != null) {
            ecoNewsUsersService.deleteById(user.getId());
        }
    }

    @Test
    @Description("Check correct registration of user with Google")
    @Issue("29")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://jira.softserve.academy/browse/GC-218")
    public void test() {
        SignUpComponent signUpComponent = new HeaderSignedOutComponent(driver).clickSignUp();
        String userPassword = "Tetsregistr_1";
        signUpComponent.clickOnSignUpWithGoogleButton();
        new GoogleSignInInputEmailPopUp(driver).inputEmailIntoField(userEmail)
                .clickOnContinueButton()
                .inputPasswordIntoField(userPassword)
                .clickOnContinueButton();
        String actualUserName = new HeaderSignedInComponent(driver).getUserName();
        Assert.assertEquals(actualUserName, "Tetsregistr Tetsregistr");
    }

    @AfterTest(description = "Delete user by email in database after registration")
    public void deleteRegisteredUser() {
        EcoNewsUsersEntity user = ecoNewsUsersService.getByEmail(userEmail);
        if (user != null) {
            ecoNewsUsersService.deleteById(user.getId());
        }
    }
}
