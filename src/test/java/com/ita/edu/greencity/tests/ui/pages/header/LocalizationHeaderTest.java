package com.ita.edu.greencity.tests.ui.pages.header;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

import static java.util.Map.entry;

public class LocalizationHeaderTest extends TestRun {

    @DataProvider(name = "headerButtonsText")
    public Object[][] dataProviderHeaderButtonsText() {
        return new Object[][]{
                {"En", Map.ofEntries(
                        entry("aboutUsButtonText", "About-us"),
                        entry("sortingRulesButtonText", "Sorting rules"),
                        entry("ecoShopButtonText", "Eco-Shop"),
                        entry("greenCityButtonText", "Green City"),
                        entry("signInButtonText", "Sign in"),
                        entry("signUpButtonText", "Sign up")
                )},
                {"Ua", Map.ofEntries(
                        entry("aboutUsButtonText", "Про нас"),
                        entry("sortingRulesButtonText", "Правила сортування"),
                        entry("ecoShopButtonText", "Еко-магазин"),
                        entry("greenCityButtonText", "Green City"),
                        entry("signInButtonText", "Увійти"),
                        entry("signUpButtonText", "Зареєструватись")
                )}
        };
    }

    @Test(dataProvider = "headerButtonsText")
    @Description("Test to check localization of header buttons naming text")
    @Issue("96")
    public void localizationHeaderButtonsText(String language, Map<String, String> map) {
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        header.clickLanguageSwitcher().languageChoose(language);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(header.getAboutUsButtonText(), map.get("aboutUsButtonText"),
                "Incorrect localization for aboutUsButtonText");
        softAssert.assertEquals(header.getSortingRulesButtonText(), map.get("sortingRulesButtonText"),
                "Incorrect localization for sortingRulesButtonText");
        softAssert.assertEquals(header.getEcoShopButtonText(), map.get("ecoShopButtonText"),
                "Incorrect localization for ecoShopButtonText");
        softAssert.assertEquals(header.getGreenCityButtonText(), map.get("greenCityButtonText"),
                "Incorrect localization for greenCityButtonText");
        softAssert.assertEquals(header.getSignInButtonText(), map.get("signInButtonText"),
                "Incorrect localization for signInButtonText");
        softAssert.assertEquals(header.getSignUpButtonText(), map.get("signUpButtonText"),
                "Incorrect localization for signUpButtonText");
        softAssert.assertAll();
    }

    @DataProvider(name = "headerSignedButtonsText")
    public Object[][] dataProviderHeaderSignedButtonsText() {
        return new Object[][]{
                {"En", Map.ofEntries(
                        entry("settingsButtonText", "Settings"),
                        entry("signOutButtonText", "Sign out"),
                        entry("ubsUserButtonText", "UBS-user")
                )},
                {"Ua", Map.ofEntries(
                        entry("settingsButtonText", "Налаштування"),
                        entry("signOutButtonText", "Вийти"),
                        entry("ubsUserButtonText", "UBS-user")
                )}
        };
    }

    @Test(dataProvider = "headerSignedButtonsText")
    @Description("Test to check localization of header buttons naming text after user has logged in")
    @Issue("96")
    public void localizationHeaderSignedButtonsText(String language, Map<String, String> map) {
        new HeaderSignedOutComponent(driver).clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn();
        HeaderSignedInComponent header = new HeaderSignedInComponent(driver);
        header.clickLanguageSwitcher().languageChoose(language);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(header.getSettingsButtonText(), map.get("settingsButtonText"),
                "Incorrect localization for settingsButtonText");
        softAssert.assertEquals(header.getSignOutButtonText(), map.get("signOutButtonText"),
                "Incorrect localization for signOutButtonText");
        softAssert.assertEquals(header.getUbsUserButtonText(), map.get("ubsUserButtonText"),
                "Incorrect localization for ubsUserButtonText");
        softAssert.assertAll();
    }
}
