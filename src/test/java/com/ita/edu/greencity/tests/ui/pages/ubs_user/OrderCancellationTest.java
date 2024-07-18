package com.ita.edu.greencity.tests.ui.pages.ubs_user;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.ubs_user.orders.CancelPopUp;
import com.ita.edu.greencity.ui.pages.ubs_user.orders.OrdersContainer;
import com.ita.edu.greencity.ui.pages.ubs_user.orders.UbsUserOrders;
import com.ita.edu.greencity.utils.jdbc.services.EcoNewsOrdersService;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class OrderCancellationTest extends TestRun {

    EcoNewsOrdersService ecoNewsOrdersService = new EcoNewsOrdersService();

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

    @AfterMethod
    public void afterMethod() {
        super.afterMethod();
    }


    @Description("test capability to cancel only orders with order status 'formed' and payment status 'unpaid'")
    @Issue("105")
    @Test()
    public void verifyThatOnlyFormedAndUnpaidOrdersCanBeCancelled() {

        UbsUserOrders ubsUserOrders = new UbsUserOrders(driver);
        SoftAssert softAssert = new SoftAssert();

        OrdersContainer neededElement = ubsUserOrders
                .getOrderByOrderAndPaymentStatuses("Formed", "Unpaid");

        String orderId = neededElement.getOrderId();

        softAssert.assertTrue(neededElement.getCancelButton().isDisplayed());

        softAssert.assertEquals(neededElement.clickOnCancelButton()
                .clickOnYesButton()
                .getOrderByNumber(orderId), null, "Order was not cancelled");

        softAssert.assertEquals(ecoNewsOrdersService.getById(Long.parseLong(orderId)), null,
                "Order was not deleted from DataBase");

        softAssert.assertAll();
    }

    @Description("test 'No' button in cancellation pop-up window")
    @Issue("105")
    @Test
    public void verifyThatFormedAndUnpaidOrdersAreNotCancelledByClickingNoInPopUpWindow() {
        UbsUserOrders ubsUserOrders = new UbsUserOrders(driver);
        SoftAssert softAssert = new SoftAssert();

        OrdersContainer neededElement = ubsUserOrders
                .getOrderByOrderAndPaymentStatuses("Formed", "Unpaid");

        String orderId = neededElement.getOrderId();

        softAssert.assertNotEquals(neededElement.clickOnCancelButton()
                .clickOnNoButton()
                .getOrderByNumber(orderId), null, "Order was cancelled");

        softAssert.assertNotEquals(ecoNewsOrdersService.getById(Long.parseLong(orderId)), null,
                "Order was deleted from DataBase");

        softAssert.assertAll();
    }

    @Description("test pop-up elements localization")
    @Issue("105")
    @Test()
    public void popUpElementsLocalization() {
        final String expectedWarningMessageEn = "If canceled, this order will be deleted. Do you really want to cancel the order?";
        final String expectedWarningMessageUa = "У разі скасування дане замовлення буде видалено. Чи дійсно Ви бажаєте скасувати замовлення?";

        SoftAssert softAssert = new SoftAssert();

        new UbsUserOrders(driver).getHeader()
                .clickLanguageSwitcher()
                .languageChoose("ua");

        CancelPopUp cancelPopUpUa = new UbsUserOrders(driver)
                .getOrderByOrderAndPaymentStatuses("Сформовано", "Не оплачено")
                .clickOnCancelButton();

        softAssert.assertEquals(cancelPopUpUa.getEnsuranceOfCancelingLabelText(), expectedWarningMessageUa,
                "Wrong label text");
        softAssert.assertEquals(cancelPopUpUa.getNoButton().getText(), "Ні", "Wrong 'No' button text");
        softAssert.assertEquals(cancelPopUpUa.getYesButton().getText(), "Так", "Wrong 'Yes' button text");

        cancelPopUpUa.clickOnNoButton();

        new UbsUserOrders(driver).getHeader()
                .clickLanguageSwitcher()
                .languageChoose("en");

        CancelPopUp cancelPopUpEn = new UbsUserOrders(driver)
                .getOrderByOrderAndPaymentStatuses("Formed", "Unpaid")
                .clickOnCancelButton();

        softAssert.assertEquals(cancelPopUpEn.getEnsuranceOfCancelingLabelText(), expectedWarningMessageEn,
                "Wrong label text");
        softAssert.assertEquals(cancelPopUpEn.getNoButton().getText(), "No", "Wrong 'No' button text");
        softAssert.assertEquals(cancelPopUpEn.getYesButton().getText(), "Yes", "Wrong 'Yes' button text");


        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] paymentStatuses() {
        return new Object[][]{
                {"Half paid"},
                {"Paid"}
        };
    }

    @Description("test impossibility cancellation of order with {paymentStatus} payment status")
    @Test(dataProvider = "paymentStatuses")
    public void verifyThatFormedAndDifferentFromUnpaidOrderCanNotBeCancelled(String paymentStatus) {
        UbsUserOrders ubsUserOrders = new UbsUserOrders(driver);

        boolean orderCanBeCancelled = ubsUserOrders
                .getOrderByOrderAndPaymentStatuses("Formed", paymentStatus)
                .isCancelButtonDisplayed();

        Assert.assertFalse(orderCanBeCancelled, "Order with " + paymentStatus + " has 'Cancel' button");
    }


}