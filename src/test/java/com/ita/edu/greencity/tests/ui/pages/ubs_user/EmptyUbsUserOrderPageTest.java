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

public class EmptyUbsUserOrderPageTest extends TestRun {

    @BeforeMethod
    public void beforeMethod(ITestContext iTestContext) {
        super.beforeMethod(iTestContext);
        HeaderSignedOutComponent headerSignedOutComponent = new HeaderSignedOutComponent(driver);
        headerSignedOutComponent.clickSignIn()
                .inputEmail(provider.getUserWithoutOrdersEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn();

        new HeaderSignedInComponent(driver).clickUserMenu()
                .clickUbsUser();
    }

    @DataProvider
    private Object[][] emptyOrderPageLabelAndButton() {
        return new Object[][]{
                {"ua", "Ви ще не зробили замовлень у нас.", "Нове замовлення"},
                {"en", "You haven't made any orders with us yet.", "New order"}
        };
    }

    @Description("test localization of label and new order button")
    @Issue("107")
    @Test(dataProvider = "emptyOrderPageLabelAndButton")
    public void verifyLabelAndButtonLocalization(String language, String label, String newOrderButtonText) {
        SoftAssert softAssert = new SoftAssert();

        new UbsUserOrders(driver).getHeader()
                .clickLanguageSwitcher()
                .languageChoose(language);

        softAssert.assertEquals(new UbsUserOrders(driver).getEmptyOrdersPageLabel(), label,
                "Order page isn't empty");
        softAssert.assertEquals(new UbsUserOrders(driver).getNewOrderButton().getText(), newOrderButtonText,
                "New order button has wrong text");
        softAssert.assertAll();
    }

    @Description("test presence of new order button")
    @Issue("107")
    @Test
    public void verifyNewOrderButtonOnUbsUserOrderPage() {

        Assert.assertTrue(new UbsUserOrders(driver).getNewOrderButton().isDisplayed(),
                "There is no New Order Button on the page");

    }

    @AfterMethod
    public void afterMethod() {
        super.afterMethod();
    }

}
