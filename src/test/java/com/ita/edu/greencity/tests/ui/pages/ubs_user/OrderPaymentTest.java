package com.ita.edu.greencity.tests.ui.pages.ubs_user;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedInComponent;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.ubs_user.orders.UbsUserOrders;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class OrderPaymentTest extends TestRun {

    @BeforeMethod
    public void beforeMethod(ITestContext iTestContext) {
        super.beforeMethod(iTestContext);
        HeaderSignedOutComponent headerSignedOutComponent = new HeaderSignedOutComponent(driver);
        headerSignedOutComponent.clickSignIn()
                .inputEmail(provider.getUserWithOrdersEmail())
                .inputPassword(provider.getPassword())
                .clickSignIn();

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new HeaderSignedInComponent(driver).clickUserMenu()
                .clickUbsUser();
    }

    @DataProvider
    public Object[][] paymentStatuses() {
        return new Object[][]{
                {"Half paid"},
                {"Unpaid"}
        };
    }

    @Test(dataProvider = "paymentStatuses")
    public void verifyCorrectnessOfPaymentScenarioWithUnpaidAndHalfPaidOrders(String paymentStatus) {
        Assert.assertTrue(new UbsUserOrders(driver)
                .getOrderByPaymentStatus(paymentStatus)
                .clickOnPayButton()
                .isPaymentPopUpOpened());
    }

    @AfterMethod
    public void afterMethod() {
        super.afterMethod();
    }
}
