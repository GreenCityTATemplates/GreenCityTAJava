package com.ita.edu.greencity.tests.ui.pages.ubs_user;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.ubs_user.orders.UbsUserOrders;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class OrderPageTest extends TestRun {

    @BeforeMethod
    public void beforeMethod(ITestContext iTestContext) {
        super.beforeMethod(iTestContext);
        HeaderSignedOutComponent headerSignedOutComponent = new HeaderSignedOutComponent(driver);
        headerSignedOutComponent.clickSignIn()
                .inputEmail(provider.getUserWithOrdersEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn();

        new HeaderSignedInComponent(driver).clickUserMenu()
                .clickUbsUser();
    }

    @Description("test presence of 'current orders' and 'order history' tabs on the page")
    @Issue("106")
    @Test
    public void pageTabsPresenceTest() {
        UbsUserOrders ubsUserOrders = new UbsUserOrders(driver);
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(ubsUserOrders.getCurrentOrdersTabButton().isDisplayed(),
                "There is no current orders tab");
        softAssert.assertTrue(ubsUserOrders.getOrderHistoryTabButton().isDisplayed(),
                "There is no order history tab");
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] pageTabs() {
        return new Object[][]{
                {"ua", "Поточні замовлення", "Історія замовлень"},
                {"en", "Current orders", "Order history"}
        };
    }

    @Description("test order page elements localization")
    @Issue("106")
    @Test(dataProvider = "pageTabs")
    public void pageTabsLocalizationTest(String language, String firstTabText, String secondTabText) {
        SoftAssert softAssert = new SoftAssert();

        new UbsUserOrders(driver).getHeader()
                .clickLanguageSwitcher()
                .languageChoose(language);

        softAssert.assertEquals(new UbsUserOrders(driver).getCurrentOrdersTabButton().getText(), firstTabText,
                "Wrong current orders tab label text");
        softAssert.assertEquals(new UbsUserOrders(driver).getOrderHistoryTabButton().getText(), secondTabText,
                "Wrong order history tab label text");
        softAssert.assertAll();
    }

    @Description("test 'current orders' tab is selected by default")
    @Issue("106")
    @Test
    public void currentOrdersTabSelectedByDefaultTest() {
        UbsUserOrders ubsUserOrders = new UbsUserOrders(driver);

        Assert.assertTrue(Boolean.parseBoolean(ubsUserOrders.getCurrentOrdersTab().getAttribute("aria-selected")),
                "Current orders tab is not selected by default");
    }

    @AfterMethod
    public void afterMethod() {
        super.afterMethod();
    }
}
