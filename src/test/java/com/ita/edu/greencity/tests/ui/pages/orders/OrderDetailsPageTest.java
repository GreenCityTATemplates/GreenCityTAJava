package com.ita.edu.greencity.tests.ui.pages.orders;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.tests.utils.TestHelpersUtils;
import com.ita.edu.greencity.ui.pages.orders.OrderDetailsPage;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;

public class OrderDetailsPageTest extends TestRun {


    @BeforeMethod
    public void beforeMethod(ITestContext iTestContext) {
        super.beforeMethod(iTestContext);
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        ubsHomePage.pressOrderCourierUnlogin()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignInAfterCallUpCourier()
                .clickOnContinueButton();
    }

    @Description("Checks crevices alerts")
    @Link("https://jira.softserve.academy/projects/GC?selectedItem=com.thed.zephyr.je:zephyr-tests-page#test-cycles-tab")
    @Test
    public void servicesAlertTest() {
        String MinimumOrderAmountAlertExpectedText = "The minimum order amount must be 500.00 UAH";
        String MinimumOrderContainsAlertExpectedText = "Please note that the minimum order must contain at least 2 packages of 120 liters";
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertFalse(orderDetailsPage.checkNextButtonStatus());
        orderDetailsPage
                .EnterNumberOfTextileWaste20lInput("1");
        softAssert.assertEquals(orderDetailsPage.getMinimumOrderAmountAlertText(), MinimumOrderAmountAlertExpectedText);
        softAssert.assertEquals(orderDetailsPage.getMinimumOrderContainsAlertText(), MinimumOrderContainsAlertExpectedText);
        softAssert.assertFalse(orderDetailsPage.checkNextButtonStatus());
        orderDetailsPage
                .EnterNumberOfTextileWaste20lInput("10");
        softAssert.assertEquals(orderDetailsPage.getMinimumOrderContainsAlertText(), MinimumOrderContainsAlertExpectedText);
        softAssert.assertFalse(orderDetailsPage.checkNextButtonStatus());
        orderDetailsPage
                .EnterNumberOfSafeWasteInput("2");
        softAssert.assertTrue(orderDetailsPage.checkNextButtonStatus());
        softAssert.assertAll();

    }

    @Description("Checks if comment saves when we go to 'Personal data' page and return to 'Order details' page")
    @Issue("88")
    @Link("https://jira.softserve.academy/projects/GC?selectedItem=com.thed.zephyr.je:zephyr-tests-page#test-cycles-tab")
    @Test
    public void messageTest() {
        String expected = TestHelpersUtils.generateRandomComment(20);
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
        String actual = orderDetailsPage
                .EnterNumberOfSafeWasteInput("20")
                .EnterNumberOfTextileWaste20lInput("1")
                .EnterNumberOfTextileWaste120lInput("1")
                .EnterCommentInput(expected)
                .clickOnNextButton()
                .clickOnBackButton()
                .getCommentInput();
        Assert.assertEquals(actual.trim(), expected);
    }


    @Description("Checks if 'Order amount' is counted properly")
    @Issue("89")
    @Test
    public void orderAmountTest() {
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
        orderDetailsPage
                .EnterNumberOfSafeWasteInput("20")
                .EnterNumberOfTextileWaste20lInput("1")
                .EnterNumberOfTextileWaste120lInput("1");
        float sumOfOfTextileWaste20l = Float.parseFloat(Arrays.stream(orderDetailsPage.getTextileWaste20lSum().split("\s")).toList().get(0));
        float sumOfOfTextileWaste120l = Float.parseFloat(Arrays.stream(orderDetailsPage.getTextileWaste120lSum().split("\s")).toList().get(0));
        float sumOfOfSumWaste = Float.parseFloat(Arrays.stream(orderDetailsPage.getSaveWasteSum().split("\s")).toList().get(0));
        float expectedSum = sumOfOfSumWaste + sumOfOfTextileWaste120l + sumOfOfTextileWaste20l;
        float actualSum = Float.parseFloat(Arrays.stream(orderDetailsPage.getOrderAmount().split("\s")).toList().get(0));
        Assert.assertEquals(actualSum, expectedSum);
    }

    @Description("Checks if 'Order amount' is counted properly with bonuses usage")
    @Link("https://jira.softserve.academy/projects/GC?selectedItem=com.thed.zephyr.je:zephyr-tests-page#test-cycles-tab")
    @Test
    public void orderAmountWithBonusesTest() {
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
        orderDetailsPage
                .EnterNumberOfSafeWasteInput("20")
                .EnterNumberOfTextileWaste20lInput("1")
                .EnterNumberOfTextileWaste120lInput("1")
                .ClickOnYesUseBonusesCheckmark();
        float bonusesNumber = Float.parseFloat(orderDetailsPage.getBonusesNumber());
        float actualAmount = Float.parseFloat(Arrays.stream(orderDetailsPage.getOrderAmount().split("\s")).toList().get(0));
        float actualAmountDue = Float.parseFloat(Arrays.stream(orderDetailsPage.getAmountDue().split("\s")).toList().get(0));
        float expectedAmountDue = actualAmount - bonusesNumber;
        Assert.assertEquals(actualAmountDue, expectedAmountDue);
    }

    @Description("Checks if orders from eco store saves when we go to 'Personal data' page and return to 'Order details' page")
    @Issue("117")
    @Test
    public void ecoStoreOrderTest() {
        String orderNumber1 = TestHelpersUtils.generateRandomOrderNumber();
        String orderNumber2 = TestHelpersUtils.generateRandomOrderNumber();
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
        String actual1 = orderDetailsPage
                .EnterNumberOfSafeWasteInput("20")
                .EnterNumberOfTextileWaste20lInput("1")
                .EnterNumberOfTextileWaste120lInput("1")
                .clickOnYesWaitingStoreOrderCheckmark()
                .EnterOrderNumberInputs(orderNumber1, 0)
                .clickOnAddAnotherNumberButton()
                .EnterOrderNumberInputs(orderNumber2, 1)
                .clickOnNextButton()
                .clickOnBackButton()
                .getOrderNumberInputs(0);
        String actual2 = orderDetailsPage.getOrderNumberInputs(1);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actual1, orderNumber1);
        softAssert.assertEquals(actual2, orderNumber2);
        softAssert.assertAll();
    }

    @Description("Checks if eco store alert works properly")
    @Link("https://jira.softserve.academy/projects/GC?selectedItem=com.thed.zephyr.je:zephyr-tests-page#test-cycles-tab")
    @Test
    public void ecoStoreOrderAlertTest() {
        String expected = "The number was entered incorrectly. Try again.";
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
        String actual = orderDetailsPage
                .EnterNumberOfSafeWasteInput("20")
                .EnterNumberOfTextileWaste20lInput("1")
                .EnterNumberOfTextileWaste120lInput("1")
                .clickOnYesWaitingStoreOrderCheckmark()
                .EnterOrderNumberInputs("2131", 0)
                .EnterCommentInput("")
                .getEcoStoreOrderAlert();
        Assert.assertEquals(actual, expected);

    }
}
