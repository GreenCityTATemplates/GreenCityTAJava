package com.ita.edu.greencity.tests.ui.pages.orders;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.tests.utils.TestHelpersUtils;
import com.ita.edu.greencity.ui.pages.header.HeaderComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.orders.SelectRegion;
import jdk.jfr.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

import static java.util.Map.entry;

public class SelectRegionLocalizationTest extends TestRun {
    @DataProvider(name = "languageChanger-provider")
    public Object[][] dataProviderMethod() {
        return new String[][]{{"ua", "uk"}, {"en", "en"}};
    }

    @Description("Verify localization of text at pop-up Select Region")
    @Test(dataProvider = "languageChanger-provider")
    void ChangeRegionLocalization(String lang, String expected) {
        SoftAssert softAssert = new SoftAssert();
        new HeaderComponent(driver).clickLanguageSwitcher().languageChoose(lang);
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        SelectRegion selectRegion = header.clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignInAfterCallUpCourier();
        softAssert.assertEquals(TestHelpersUtils.getLanguage(selectRegion.getBottomText()), expected,
                "Incorrect language for BOTTOM text");
        softAssert.assertEquals(TestHelpersUtils.getLanguage(selectRegion.getTitleText()), expected,
                "Incorrect language for TITLE text");
        softAssert.assertAll();
    }

    @DataProvider(name = "buttonsName-provider")
    public Object[][] dataProviderButtons() {
        return new Object[][]{
                {"ua", Map.ofEntries(
                        entry("continueButtonText", "Продовжити"),
                        entry("backButtonText", "Назад")
                )},
                {"en", Map.ofEntries(
                        entry("continueButtonText", "Continue"),
                        entry("backButtonText", "Back")

                )}
        };
    }

    @Description("Verify localization of Buttons text at pop-up Select Region")
    @Test(dataProvider = "buttonsName-provider")
    void TipTextLocalization(String lang, Map<String, String> map) {
        SoftAssert softAssert = new SoftAssert();
        new HeaderComponent(driver).clickLanguageSwitcher().languageChoose(lang);
        SelectRegion selectRegion = new HeaderSignedOutComponent(driver)
                .clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignInAfterCallUpCourier();
        softAssert.assertEquals(selectRegion.getContinueButtonText(), map.get("continueButtonText"),
                "Incorrect localization for continueButtonText");
        softAssert.assertEquals(selectRegion.getBackButtonText(), map.get("backButtonText"),
                "Incorrect localization for backButtonText");
        softAssert.assertAll();
    }
}
