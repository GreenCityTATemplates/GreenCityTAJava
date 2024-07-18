package com.ita.edu.greencity.tests.ui.pages.sign_up;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.sign_in.SignInComponent;
import com.ita.edu.greencity.ui.pages.sign_up.SignUpComponent;
import com.ita.edu.greencity.utils.jdbc.entity.EcoNewsUsersEntity;
import com.ita.edu.greencity.utils.jdbc.entity.EcoNewsVerifyEmailsEntity;
import com.ita.edu.greencity.utils.jdbc.services.EcoNewsUsersService;
import com.ita.edu.greencity.utils.jdbc.services.EcoNewsVerifyEmailsService;
import io.qameta.allure.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SignUpPersonWithoutVerifyEmailTest extends TestRun {

    private final String userEmail = "registertesttest88@gmail.com";
    EcoNewsUsersService ecoNewsUsersService = new EcoNewsUsersService();
    EcoNewsVerifyEmailsService ecoNewsVerifyEmailsService = new EcoNewsVerifyEmailsService();

    @BeforeTest(description = "Delete user by email if it exists in database before registration")
    public void checkRegisteredUser() {
        EcoNewsUsersEntity user = ecoNewsUsersService.getByEmail(userEmail);
        if (user != null) {
            ecoNewsUsersService.deleteById(user.getId());
        }
    }

    @Test
    @Description("Verify that user is not registered if he didn’t confirm email address in the mailbox")
    @Issue("29")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://jira.softserve.academy/browse/GC-512")
    public void test() {
        SignUpComponent signUpComponent = new HeaderSignedOutComponent(driver).clickSignUp();
        String userPassword = "Tetsregistr_1";
        String userName = "Tetsregistr";
        signUpComponent.inputEmailIntoField(userEmail)
                .inputUserNameIntoField(userName)
                .inputPasswordIntoField(userPassword)
                .inputConfirmPasswordIntoField(userPassword)
                .clickOnSignUpButton()
                .getTextOfSuccessRegistrationAlert();
        SoftAssert softAssert = new SoftAssert();
        new HeaderSignedOutComponent(driver).clickSignIn()
                .inputEmail(userEmail)
                .inputPassword(userPassword)
                .loadData()
                .clickSignIn();
        String actual = new SignInComponent(driver).getErrorPasswordMessage();
        softAssert.assertEquals(actual, "Bad email or password");
        EcoNewsVerifyEmailsEntity recordInVerifyEmails = ecoNewsVerifyEmailsService.selectByUserId(userEmail);
        boolean isInDB = recordInVerifyEmails != null;
        softAssert.assertTrue(isInDB, "User is not in Data Base!");
        System.out.println(recordInVerifyEmails);
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
