package com.ita.edu.greencity.tests.ui.pages.user_data;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.user_data.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

import static java.util.Map.entry;

public class LocalizationUserDataPageTest extends TestRun {
    @BeforeMethod
    public void loginToUBS() {
        new HeaderSignedOutComponent(driver).clickSignIn()
                .inputEmail(provider.getEmailForUserData())
                .inputPassword(provider.getPasswordForUserData())
                .clickSignInAfterCallUpCourier()
                .chooseRegionByValue("Kyiv")
                .clickOnContinueButton();
    }

    @DataProvider(name = "dataProviderPersonalInfo")
    public Object[][] dataProviderPersonalInfo() {
        return new Object[][]{
                {"en", Map.ofEntries(
                        entry("nameText", "Name"),
                        entry("surnameText", "Surname"),
                        entry("emailText", "email"),
                        entry("phoneText", "phone")

                )},
                {"ua", Map.ofEntries(
                        entry("nameText", "Ім'я"),
                        entry("surnameText", "Прізвище"),
                        entry("emailText", "Електронна скринька"),
                        entry("phoneText", "№ телефону")
                )}

        };
    }

    @DataProvider(name = "dataProviderAddressInfo")
    public Object[][] dataProviderAddressInfo() {
        return new Object[][]{
                {"Address №1", "en", Map.ofEntries(
                        entry("cityText", "City"),
                        entry("regionText", "Region"),
                        entry("districtText", "District"),
                        entry("streetText", "Street"),
                        entry("houseText", "House"),
                        entry("corpusText", "Corpus"),
                        entry("entranceText", "Entrance")

                )},
                {"Адреса №1", "ua", Map.ofEntries(
                        entry("cityText", "Місто"),
                        entry("regionText", "Область"),
                        entry("districtText", "Район"),
                        entry("streetText", "Вулиця"),
                        entry("houseText", "Будинок"),
                        entry("corpusText", "Корпус"),
                        entry("entranceText", "Під'їзд")
                )}

        };
    }

    @DataProvider(name = "dataProviderButtonsText")
    public Object[][] dataProviderButtonsText() {
        return new Object[][]{
                {"en", Map.ofEntries(
                        entry("editDataText", "Edit data"),
                        entry("changePasswordText", "Change password"),
                        entry("deleteProfileText", "Delete profile")


                )},
                {"ua", Map.ofEntries(
                        entry("editDataText", "Редагувати дані"),
                        entry("changePasswordText", "Змінити пароль"),
                        entry("deleteProfileText", "Видалити профіль")

                )}

        };
    }

    @Test(dataProvider = "dataProviderPersonalInfo")
    @Description("check whether personal information labels change when changing language")
    @Issue("102")
    @Severity(SeverityLevel.NORMAL)
    public void verifyLocalizationPersonalInfo(String lang, Map<String, String> map) {
        new HeaderComponent(driver).clickLanguageSwitcher().languageChoose(lang);
        UserData userData = new HeaderSignedInComponent(driver)
                .clickUserMenu()
                .clickUbsUser()
                .getUbsUserPage()
                .clickOnUserDataButton();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(userData.getTextFromNameLabel(), map.get("nameText"),
                "Incorrect localization for nameText");
        softAssert.assertEquals(userData.getTextFromSurnameLabel(), map.get("surnameText"),
                "Incorrect localization for surnameText");
        softAssert.assertEquals(userData.getTextFromEmailLabel(), map.get("emailText"),
                "Incorrect localization for emailText");
        softAssert.assertEquals(userData.getTextFromPhoneLabel(), map.get("phoneText"),
                "Incorrect localization for phoneText");
        softAssert.assertAll();
    }

    @Test(dataProvider = "dataProviderAddressInfo")
    @Description("check whether address information labels change when changing language")
    @Issue("102")
    @Severity(SeverityLevel.NORMAL)
    public void verifyLocalizationAddressInfo(String numberAddress, String lang, Map<String, String> map) {
        new HeaderComponent(driver).clickLanguageSwitcher().languageChoose(lang);
        UserData userData = new HeaderSignedInComponent(driver)
                .clickUserMenu()
                .clickUbsUser()
                .getUbsUserPage()
                .clickOnUserDataButton();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(userData.chooseAddressShow(numberAddress).getCityLabel(), map.get("cityText"),
                "Incorrect localization for cityText");
        softAssert.assertEquals(userData.chooseAddressShow(numberAddress).getRegionLabel(), map.get("regionText"),
                "Incorrect localization for regionText");
        softAssert.assertEquals(userData.chooseAddressShow(numberAddress).getDistrictLabel(), map.get("districtText"),
                "Incorrect localization for districtText");
        softAssert.assertEquals(userData.chooseAddressShow(numberAddress).getStreetLabel(), map.get("streetText"),
                "Incorrect localization for streetText");
        softAssert.assertEquals(userData.chooseAddressShow(numberAddress).getHouseLabel(), map.get("houseText"),
                "Incorrect localization for houseText");
        softAssert.assertEquals(userData.chooseAddressShow(numberAddress).getCorpusLabel(), map.get("corpusText"),
                "Incorrect localization for corpusText");
        softAssert.assertEquals(userData.chooseAddressShow(numberAddress).getEntranceLabel(), map.get("entranceText"),
                "Incorrect localization for entranceText");

        softAssert.assertAll();
    }

    @Test(dataProvider = "dataProviderButtonsText")
    @Description("check whether all button`s text are changed when changing language")
    @Issue("102")
    @Severity(SeverityLevel.NORMAL)
    public void verifyLocalizationButtonsText(String lang, Map<String, String> map) {
        new HeaderComponent(driver).clickLanguageSwitcher().languageChoose(lang);
        UserData userData = new HeaderSignedInComponent(driver)
                .clickUserMenu()
                .clickUbsUser()
                .getUbsUserPage()
                .clickOnUserDataButton();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(userData.getTextFromEditDataButton(), map.get("editDataText"),
                "Incorrect localization for editDataText");
        softAssert.assertEquals(userData.getTextFromChangePasswordButton(), map.get("changePasswordText"),
                "Incorrect localization for changePasswordText");
        softAssert.assertEquals(userData.getTextFromDeleteProfileButton(), map.get("deleteProfileText"),
                "Incorrect localization for deleteProfileText");
        softAssert.assertAll();

    }


}
