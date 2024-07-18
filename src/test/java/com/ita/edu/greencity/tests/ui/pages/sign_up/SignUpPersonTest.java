package com.ita.edu.greencity.tests.ui.pages.sign_up;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.sign_up.SignUpComponent;
import com.ita.edu.greencity.utils.jdbc.entity.EcoNewsUsersEntity;
import com.ita.edu.greencity.utils.jdbc.services.EcoNewsUsersService;
import io.qameta.allure.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class SignUpPersonTest extends TestRun {

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
    @Description("Check correct alert message about registration of user to system")
    @Issue("29")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://jira.softserve.academy/browse/GC-213")
    public void test() {
        SignUpComponent signUpComponent = new HeaderSignedOutComponent(driver).clickSignUp();
        String userPassword = "Tetsregistr_1";
        String userName = "Tetsregistr";
        signUpComponent.inputEmailIntoField(userEmail)
                .inputUserNameIntoField(userName)
                .inputPasswordIntoField(userPassword)
                .inputConfirmPasswordIntoField(userPassword)
                .clickOnSignUpButton();
        boolean isDisabled = signUpComponent.checkDisabledSignUpButton();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(isDisabled, "SignUp button is disabled!");
        String expectedAlert = signUpComponent.getTextOfSuccessRegistrationAlert();
        softAssert.assertEquals(expectedAlert, "Congratulations! You have successfully registered on the site. Please confirm your email address in the email box.", "Incorrect alert!");
        EcoNewsUsersEntity user = ecoNewsUsersService.getByEmail(userEmail);
        softAssert.assertEquals(user, null, "User is in Data Base!");
        softAssert.assertAll();
    }

    @AfterTest(description = "Delete user by email in database after registration")
    public void deleteRegisteredUser() {
        EcoNewsUsersEntity user = ecoNewsUsersService.getByEmail(userEmail);
        if (user != null) {
            ecoNewsUsersService.deleteById(user.getId());
        }
    }
}
