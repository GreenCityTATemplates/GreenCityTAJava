package com.ita.edu.greencity.tests.ui.pages.orders;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.tests.utils.TestHelpersUtils;
import com.ita.edu.greencity.ui.pages.orders.OrderDetailsPage;
import com.ita.edu.greencity.ui.pages.ubs_homepage.UbsHomePage;
import com.ita.edu.greencity.utils.jdbc.services.EcoNewsCertificateService;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.util.Arrays;


public class OrderDetailsPageCertificateTest extends TestRun {
    private final String codeValueActive = TestHelpersUtils.generateRandomCertificateNumber();

    private final String statusValueActive = "ACTIVE";
    private final String expiration_dateValue = "2022-11-11 00:00:00";
    private final int pointsValue = 500;

    EcoNewsCertificateService ecoNewsCertificateService = new EcoNewsCertificateService();

    private String nonExistCertificate() {
        String value = TestHelpersUtils.generateRandomCertificateNumber();
        for (; ecoNewsCertificateService.checkIfCertificateExists(value); ) {
            value = TestHelpersUtils.generateRandomOrderNumber();
        }
        return value;
    }

    @BeforeTest
    public void AddCertificate() throws Exception {
        ecoNewsCertificateService.addCertificate(codeValueActive, statusValueActive, expiration_dateValue, pointsValue);
    }

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


    @DataProvider
    private Object[][] certificateDataProvider() {
        final String codeValueActive = this.codeValueActive;
        final String codeValueUsed = ecoNewsCertificateService.selectRandomUsedCertificate();
        final String codeValueNotExist = nonExistCertificate();
        return new Object[][]{
                {"Certificate for 500 UAH activated", codeValueActive},
                {"Certificate has already been used", codeValueUsed},
                {"Certificate not accepted, please try again", codeValueNotExist},
        };
    }

    @Description("Checks coupon alert")
    @Issue("90")
    @Link("https://jira.softserve.academy/projects/GC?selectedItem=com.thed.zephyr.je:zephyr-tests-page#test-cycles-tab")
    @Test(dataProvider = "certificateDataProvider")
    public void couponTest(String expected, String coupon) {
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
        String actual = orderDetailsPage
                .EnterNumberOfSafeWasteInput("20")
                .EnterNumberOfTextileWaste20lInput("1")
                .EnterNumberOfTextileWaste120lInput("1")
                .EnterCertificateInput(coupon)
                .clickOnActivateCertificateButton()
                .getCertificateAlertMessage();
        Assert.assertTrue(actual.contains(expected));
    }


    @DataProvider
    private Object[][] certificateButtonProvider() {
        final String randomCertificate = TestHelpersUtils.generateRandomCertificateNumber();
        return new Object[][]{
                {false, ""},
                {true, randomCertificate},
        };
    }

    @Description("Checks coupon activate button")
    @Issue("123")
    @Test(dataProvider = "certificateButtonProvider")
    public void couponActivateButtonTest(boolean expected, String coupon) {
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
        boolean isActive = orderDetailsPage
                .EnterNumberOfSafeWasteInput("20")
                .EnterNumberOfTextileWaste20lInput("1")
                .EnterNumberOfTextileWaste120lInput("1")
                .EnterCertificateInput(coupon)
                .getCertificateButtonStatus();
        Assert.assertEquals(isActive, expected);
    }


    @Description("Check does coupon cancel button work properly")
    @Issue("124")
    @Test
    public void couponCancelButtonTest() {
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
        String actual = orderDetailsPage
                .EnterNumberOfSafeWasteInput("20")
                .EnterNumberOfTextileWaste20lInput("1")
                .EnterNumberOfTextileWaste120lInput("1")
                .EnterCertificateInput(codeValueActive)
                .clickOnCancelCertificateButton()
                .getCertificateInput();
        Assert.assertEquals(actual, "");
    }

    @Description("Checks if 'Order amount' is counted properly with certificate usage")
    @Link("https://jira.softserve.academy/projects/GC?selectedItem=com.thed.zephyr.je:zephyr-tests-page#test-cycles-tab")
    @Test
    public void orderAmountWithCertificateTest() throws InterruptedException {
        String activeCertificatePoints = ecoNewsCertificateService.getCertificatePointsByCode(codeValueActive);
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
        orderDetailsPage
                .EnterNumberOfSafeWasteInput("20")
                .EnterNumberOfTextileWaste20lInput("1")
                .EnterNumberOfTextileWaste120lInput("1")
                .EnterCertificateInput(codeValueActive)
                .clickOnActivateCertificateButton();
        Thread.sleep(15000);
        float certificatePoints = Float.parseFloat(activeCertificatePoints);
        float actualAmount = Float.parseFloat(Arrays.stream(orderDetailsPage.getOrderAmount().split("\s")).toList().get(0));
        float actualAmountDue = Float.parseFloat(Arrays.stream(orderDetailsPage.getAmountDue().split("\s")).toList().get(0));
        float expectedAmountDue = TestHelpersUtils.checkIfNegative(actualAmount - certificatePoints);
        Assert.assertEquals(actualAmountDue, expectedAmountDue);
    }

    @AfterTest
    public void deleteCertificate() throws Exception {
        ecoNewsCertificateService.deleteCertificateByCode(codeValueActive);
    }

}
