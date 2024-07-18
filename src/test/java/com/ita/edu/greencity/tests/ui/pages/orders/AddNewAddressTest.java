package com.ita.edu.greencity.tests.ui.pages.orders;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.orders.AddNewAddress;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddNewAddressTest extends TestRun {

    final String NUMBER_OF_TEXTILE_WASTE_120 = "5";
    final String STREET = "Sevastopol's'ka Square";
    final String NUMBER_OF_HOUSE = "19";
    final int INDEX_CITY = 0;
    final int INDEX_DISTRICT = 4;
    final int INDEX_STREET = 0;


    @Description("In this test we will verify that button isn't clickable, when user don't fill mandatory fields, and after filling all mandatory fields the button is clickable")
    @Link("GC-2080")
    @Issue("32")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void checkAddNewAddressButton() {
        boolean addNewAddressIsEnabled = new UbsHomePage(driver).pressOrderCourierUnlogin()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignInAfterCallUpCourier()
                .clickOnContinueButton()
                .EnterNumberOfTextileWaste120lInput(NUMBER_OF_TEXTILE_WASTE_120)
                .clickOnNextButton()
                .clickOnAddAddressButton()
                .getAddAddressButton()
                .isEnabled();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(addNewAddressIsEnabled, "Address button is disabled");

        boolean addNewAddressIsDisabled = new AddNewAddress(driver)
                .addAddress(INDEX_CITY, INDEX_DISTRICT, STREET, INDEX_STREET, NUMBER_OF_HOUSE)
                .getAddAddressButton()
                .isEnabled();

        softAssert.assertTrue(addNewAddressIsDisabled, "Address button is enabled");
        softAssert.assertAll();
    }


    @DataProvider
    private Object[][] dataProvider() {
        final String expectedWithMaximumNumberOfCharacters = "Maximum number of characters exceeded.";
        final String expectedWithNotAllowedCharacters = "The field contains forbidden characters.";

        return new Object[][]{
                {"19ABCE", expectedWithMaximumNumberOfCharacters},
                {"!@#$", expectedWithNotAllowedCharacters}
        };
    }

    @Description("In this test we will verify that the user can see warning message of the fill data near the required fields. ")
    @Issue("GC-2068")
    @Issue("GC-2079")
    @Severity(SeverityLevel.NORMAL)
    @Test(dataProvider = "dataProvider")
    public void verifyWarningMessageOfTheFillDataNearTheRequiredFields(String value, String expectedErrorMessage) {
        AddNewAddress addNewAddress = new UbsHomePage(driver)
                .pressOrderCourierUnlogin()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignInAfterCallUpCourier()
                .clickOnContinueButton()
                .EnterNumberOfTextileWaste120lInput(NUMBER_OF_TEXTILE_WASTE_120)
                .clickOnNextButton()
                .clickOnAddAddressButton()
                .clickOnCityField()
                .chooseCity(INDEX_CITY)
                .chooseDistrict(INDEX_DISTRICT)
                .enterStreet(STREET)
                .chooseStreet(INDEX_STREET)
                .enterHouseNumber(value)
                .enterHouseCorpus(value)
                .enterEntranceNumber(value);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(addNewAddress.getTextFromHouseErrorMessage(), expectedErrorMessage, "Text from house error message is different");
        softAssert.assertEquals(addNewAddress.getTextFromCorpusErrorMessage(), expectedErrorMessage, "Text from corpus error message is different");
        softAssert.assertEquals(addNewAddress.getTextFromEntranceErrorMessage(), expectedErrorMessage, "Text from entrance error message is different");
        softAssert.assertAll();
    }

    @Description("This test case verifies that after user clicks on the button 'Cancel', the window will be closed and all the data on the pop-up shall be erased")
    @Issue("GC-2133")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void checkErasedDataFromPopUpAfterClosing() {
        boolean streetFieldAisEmpty = new UbsHomePage(driver)
                .pressOrderCourierUnlogin()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignInAfterCallUpCourier()
                .clickOnContinueButton()
                .EnterNumberOfTextileWaste120lInput(NUMBER_OF_TEXTILE_WASTE_120)
                .clickOnNextButton()
                .clickOnAddAddressButton()
                .addAddress(INDEX_CITY, INDEX_DISTRICT, STREET, INDEX_STREET, NUMBER_OF_HOUSE)
                .clickOnCancelButton()
                .clickOnAddAddressButton()
                .getStreetFieldAIsEmpty()
                .isEnabled();
        Assert.assertTrue(streetFieldAisEmpty);
    }
}
