package com.ita.edu.greencity.tests.ui.pages.orders;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.orders.OrderPageConfirmation;
import com.ita.edu.greencity.ui.pages.orders.OrderPagePersonalData;
import com.ita.edu.greencity.ui.pages.orders.payment.SuccessfulOrderPage;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

public class PaymentByFondyTest extends TestRun {

    final int FIRST_INDEX_OF_SAVED_ADDRESSES = 0;
    final String TEXTILE_WASTE_120L_AMOUNT = "1";
    final String SAFE_WASTE_AMOUNT = "1";
    final String TEXTILE_WASTE_20l_AMOUNT = "2";
    final String USER_FIRST_NAME = "John";
    final String USER_LAST_NAME = "Doe";
    final String USER_PHONE_NUMBER = "+380 63 123 44 99";
    final int INDEX_OF_CITY = 0;
    final int INDEX_OF_DISTRICT = 4;
    final int INDEX_OF_STREET = 0;
    final String STREET_TO_ADD = "Sevastopol's'ka Square";
    final String HOUSE_NUMBER_TO_ADD = "19";
    final String CORPUS_TO_ADD = "2";
    final String ENTRANCE_TO_ADD = "3";


    @BeforeMethod()
    public void beforeMethod(ITestContext iTestContext) {
        super.beforeMethod(iTestContext);
        UbsHomePage ubsHomePage = new UbsHomePage(driver);
        ubsHomePage.pressOrderCourierUnlogin()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignInAfterCourierCallUp()
                .pressOrderCourierLogin()
                .clickOnContinueButton()
                .EnterNumberOfTextileWaste120lInput(TEXTILE_WASTE_120L_AMOUNT)
                .EnterNumberOfSafeWasteInput(SAFE_WASTE_AMOUNT)
                .EnterNumberOfTextileWaste20lInput(TEXTILE_WASTE_20l_AMOUNT)
                .clickOnNextButton()
                .enterFirstName(USER_FIRST_NAME)
                .entersurname(USER_LAST_NAME).enterEmail(provider.getEmail())
                .enterPhoneNumber(USER_PHONE_NUMBER);
    }

    @Description("Verify that order is made using existing address")
    @Issue("GC-2482")
    @Test
    public void paymentWIthExistingAddressTest() {
        String expectedMessage = "Your order is accepted";
        String actualMessage = new OrderPagePersonalData(driver)
                .clickOnChooseAddressButton(FIRST_INDEX_OF_SAVED_ADDRESSES)
                .clickOnNextButton()
                .choosePaymentMethod().clickOnOrderButton()
                .cardNumberInput(provider.getCardNumber())
                .expiryDateInput(provider.getExpiryDate())
                .CVV2Input(provider.getCVV2())
                .emailInput(provider.getEmail()).clickOnPayButton()
                .clickOnTheLink().clickOnContinueButton()
                .getTextFromSuccessfulOrderMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "Messages do not match");
    }

    @Description("Verify that order is made using new added address")
    @Issue("GC-2485")
    @Test
    public void paymentWIthAddingNewAddressTest() {
        String expectedMessage = "Your order is accepted";
        SuccessfulOrderPage successPage = new OrderPagePersonalData(driver)
                .clickOnAddAddressButton()
                .addFullAddress(INDEX_OF_CITY, INDEX_OF_DISTRICT, STREET_TO_ADD, INDEX_OF_STREET, HOUSE_NUMBER_TO_ADD, CORPUS_TO_ADD, ENTRANCE_TO_ADD)
                .clickOnNextButton()
                .choosePaymentMethod().clickOnOrderButton()
                .cardNumberInput(provider.getCardNumber())
                .expiryDateInput(provider.getExpiryDate())
                .CVV2Input(provider.getCVV2()).emailInput(provider.getEmail()).clickOnPayButton()
                .clickOnTheLink().clickOnContinueButton();
        String actualMessage = successPage.getTextFromSuccessfulOrderMessage();
        boolean resultOfDeletion =
                successPage.clickOnMakeOtherOrderButton().clickOnContinueButton().EnterNumberOfTextileWaste120lInput("4")
                .clickOnNextButton().clickOnDeleteCollectionAddressButton(1).checkAddressIsDeleted();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "Messages do not match");
        Assert.assertTrue(resultOfDeletion);
    }

    @Description("Verify order payment functionality by checking whether order with an appropriate number and " +
            "payment status appears in user cabinet")
    @Issue("GC-2503")
    @Test
    public void verifyPaymentApprovalThroughOrderNumberAndPaymentStatusTest() {
        String numberOfOrder = new OrderPagePersonalData(driver)
                .clickOnChooseAddressButton(FIRST_INDEX_OF_SAVED_ADDRESSES)
                .clickOnNextButton()
                .choosePaymentMethod().clickOnOrderButton()
                .cardNumberInput(provider.getCardNumber())
                .expiryDateInput(provider.getExpiryDate())
                .CVV2Input(provider.getCVV2())
                .emailInput(provider.getEmail()).clickOnPayButton()
                .clickOnTheLink().clickOnContinueButton()
                .getTextFromSuccessfulOrderMessage().substring(25, 28);

        String actualOrderNumber = new HeaderSignedInComponent(driver)
                .clickUserMenu()
                .clickUbsUser()
                .getOrderByNumber(numberOfOrder).getOrderId();
        String expectedPaymentStatus = "Paid";
        String actualPaymentStatus = new HeaderSignedInComponent(driver)
                .clickUserMenu()
                .clickUbsUser()
                .getOrderByNumber(numberOfOrder).getPaymentStatus();
        Assert.assertEquals(actualOrderNumber, numberOfOrder, "Order with such number does not exist");
        Assert.assertEquals(actualPaymentStatus, expectedPaymentStatus, "Payment statuses do not match");
    }

    @Description("Verify that the total sum of order in UBS is the same as in the payment system")
    @Issue("GC-2484")
    @Test
    public void orderAmountInUbsAndFondyIdentityTest() {
        final int FIRST_INDEX_OF_TOTAL_SUM = 0;
        OrderPageConfirmation orderPageConfirmation = new OrderPagePersonalData(driver)
                .clickOnChooseAddressButton(FIRST_INDEX_OF_SAVED_ADDRESSES).clickOnNextButton();
        double orderAmountInUbs = orderPageConfirmation
                .transformToDoubleValue(Arrays.stream(orderPageConfirmation
                        .getTotalSumWithCurrency(FIRST_INDEX_OF_TOTAL_SUM).split("\s")).toList().get(0));

        double orderAmountInFondy = orderPageConfirmation
                .transformToDoubleValue(orderPageConfirmation
                        .clickOnOrderButton()
                        .getTotalPay()
                        .replace(",", "."));
        Assert.assertEquals(orderAmountInUbs, orderAmountInFondy);
    }

    @Description("Verify make other order functionality")
    @Issue("GC-2481")
    @Test
    public void makeOtherOrderTest() {
        String actual = new OrderPagePersonalData(driver)
                .clickOnChooseAddressButton(FIRST_INDEX_OF_SAVED_ADDRESSES)
                .clickOnNextButton()
                .clickOnOrderButton()
                .cardNumberInput(provider.getCardNumber())
                .expiryDateInput(provider.getExpiryDate())
                .CVV2Input(provider.getCVV2())
                .emailInput(provider.getEmail())
                .clickOnPayButton().clickOnTheLink().clickOnContinueButton()
                .clickOnMakeOtherOrderButton().getTitleText();
        String expected = "Welcome to UBS!";
        Assert.assertTrue(actual.contains(expected));
    }

}
