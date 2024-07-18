package com.ita.edu.greencity.tests.ui.pages.user_data;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.user_data.UserData;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EditPersonalDataTest extends TestRun {
    @BeforeMethod
    public void loginToUBS() {
        new HeaderSignedOutComponent(driver).clickSignIn()
                .inputEmail(provider.getEmailForUserData())
                .inputPassword(provider.getPasswordForUserData())
                .clickSignInAfterCallUpCourier()
                .chooseRegionByValue("Kyiv")
                .clickOnContinueButton();
    }

    @DataProvider(name = "personalDataProvider")
    public Object[][] personalDataProvider() {
        return new Object[][]{
                {"TestName", "TestSurname", "0970101011", "+380 (97) 010 10 11"},
                {"Name", "Surname", "0991234567", "+380 (99) 123 45 67"},

        };
    }

    @Test(dataProvider = "personalDataProvider")
    @Description("check the ability to edit all personal data")
    @Issue("95")
    @Severity(SeverityLevel.CRITICAL)
    public void editPersonalData(String newName, String newSurname, String newNumber, String expectedNumber) {
        new HeaderSignedInComponent(driver)
                .clickUserMenu()
                .clickUbsUser()
                .getUbsUserPage()
                .clickOnUserDataButton()
                .clickOnEditDataButton()
                .enterEditedName(newName)
                .enterEditedSurname(newSurname)
                .enterEditedPhone(newNumber)
                .clickOnSaveChangesButton();
        UserData userData = new UserData(driver);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(userData.getTextFromPhoneField(), expectedNumber, "error in PhoneField");
        softAssert.assertEquals(userData.getTextFromNameField(), newName, "error in NameField");
        softAssert.assertEquals(userData.getTextFromSurnameField(), newSurname, "error in SurnameField");
        softAssert.assertAll();
    }
}
