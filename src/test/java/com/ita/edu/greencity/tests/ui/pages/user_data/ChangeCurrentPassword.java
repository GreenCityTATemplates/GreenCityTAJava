package com.ita.edu.greencity.tests.ui.pages.user_data;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import com.ita.edu.greencity.utils.jdbc.services.GreenCityOwnSecurityService;
import com.ita.edu.greencity.utils.jdbc.services.GreenCityUsersService;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChangeCurrentPassword extends TestRun {
    @BeforeMethod
    public void loginToUBS() {
        new HeaderSignedOutComponent(driver).clickSignIn()
                .inputEmail(provider.getEmailForChangePassw())
                .inputPassword(provider.getPasswordForChangePassw())
                .clickSignInHeaderComponent();

    }

    @Test
    @Description("test to change the current account password")
    @Issue("91")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://jira.softserve.academy/browse/GC-2450")
    public void changeCurrentPassword() {

        HeaderSignedInComponent header = new HeaderSignedInComponent(driver);
        header.clickUserMenu()
                .clickUbsUser()
                .getUbsUserPage()
                .clickOnUserDataButton()
                .clickOnChangePasswordButton()
                .enterOldPassword(provider.getPasswordForChangePassw())
                .enterNewPassword("0000000700Qw1/")
                .enterRepeatNewPassword("0000000700Qw1/")
                .clickOnChangePasswordButton();
        header.clickUserMenu().clickSignOut();
        driver.navigate().refresh();
        HeaderSignedOutComponent headerOut = new HeaderSignedOutComponent(driver);
        headerOut.clickSignIn()
                .inputEmail(provider.getEmailForChangePassw())
                .inputPassword("0000000700Qw1/")
                .clickSignInHeaderComponent();
        String actual = new UbsHomePage(driver).getHeaderText();
        String expectedTitle = "If you want to take care about our environment and " +
                "get rid of your wastes, but you are extremely busy and can't get " +
                "to the sorting station? UBS Courier will come to take your recyclable" +
                " materials! We provide emergency garbage assistance";
        Assert.assertEquals(actual, expectedTitle);
    }

    @AfterMethod
    public void changeToPastPassword() {
        int usersId = new GreenCityUsersService().selectUsersIdByEmail(provider.getEmailForChangePassw());
        new GreenCityOwnSecurityService().updatePasswordByID(provider.getPasswordHash(), usersId);
    }

}
