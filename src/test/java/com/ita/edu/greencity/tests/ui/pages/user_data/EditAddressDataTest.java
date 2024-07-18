package com.ita.edu.greencity.tests.ui.pages.user_data;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.user_data.UserData;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class EditAddressDataTest extends TestRun {
    @BeforeMethod
    public void loginToUBS() {
        new HeaderSignedOutComponent(driver).clickSignIn()
                .inputEmail(provider.getEmailForUserData())
                .inputPassword(provider.getPasswordForUserData())
                .clickSignInAfterCallUpCourier()
                .chooseRegionByValue("Kyiv")
                .clickOnContinueButton();
    }

    @DataProvider(name = "dataProviderAddress")
    private Object[][] dataProviderAddress() {
        return new Object[][]{
                {"Address №2", "Київ", "Київська область", "Святошинський", "вулиця Львівська", "1", "2", "3"},
                {"Address №2", "Київ", "Київська область", "Печерський", "Деміївська вулиця", "6", "5", "4"},
        };
    }

    @Test(dataProvider = "dataProviderAddress")
    @Description("check the ability to edit all data related to the address")
    @Issue("94")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://jira.softserve.academy/browse/GC-2368")
    public void editAddress(String numberAddress, String city, String region, String district,
                            String street, String number, String corpus, String entrance) {
        new HeaderSignedInComponent(driver)
                .clickUserMenu()
                .clickUbsUser()
                .getUbsUserPage()
                .clickOnUserDataButton()
                .clickOnEditDataButton()
                .chooseAddress(numberAddress)
                .setCityAddress(city)
                .setRegionAddress(region)
                .setDistrictAddress(district)
                .setStreetAddress(street)
                .setHouseNumberAddress(number)
                .setHouseCorpusAddress(corpus)
                .setEntranceNumberAddress(entrance)
                .clickOnSaveChangesButton();

        UserData userData = new UserData(driver);
        SoftAssert asert = new SoftAssert();
        asert.assertEquals(userData.chooseAddressShow(numberAddress).getCity(), city, "assert in City");
        asert.assertEquals(userData.chooseAddressShow(numberAddress).getRegion(), region, "assert in Region");
        asert.assertEquals(userData.chooseAddressShow(numberAddress).getDistrict(), district, "assert in District");
        asert.assertEquals(userData.chooseAddressShow(numberAddress).getStreet(), street, "assert in Street");
        asert.assertEquals(userData.chooseAddressShow(numberAddress).getHouse(), number, "assert in House");
        asert.assertEquals(userData.chooseAddressShow(numberAddress).getCorpus(), corpus, "assert in Corpus");
        asert.assertEquals(userData.chooseAddressShow(numberAddress).getEntrance(), entrance, "assert in Entrance");
        asert.assertAll();

    }

}
