package com.ita.edu.greencity.tests.ui.pages.orders;

import com.ita.edu.greencity.tests.ui.pages.testrunners.TestRun;
import com.ita.edu.greencity.tests.utils.TestHelpersUtils;
import com.ita.edu.greencity.ui.pages.header.HeaderSignedOutComponent;
import com.ita.edu.greencity.ui.pages.orders.OrderDetailsPage;
import jdk.jfr.Description;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ValidationOfCommentTest extends TestRun {
    @BeforeMethod
    public void beforeMethod(ITestContext iTestContext) {
        super.beforeMethod(iTestContext);
        HeaderSignedOutComponent header = new HeaderSignedOutComponent(driver);
        header.clickSignIn()
                .inputEmail(provider.getEmail())
                .inputPassword(provider.getPassword())
                .clickSignInAfterCallUpCourier()
                .chooseRegionByValue("Kyiv")
                .clickOnContinueButton().EnterNumberOfTextileWaste120lInput("2");
    }

    @Description("Verify that you can not enter comment longer than 255 characters")
    @Test
    public void incorrectLenghtOfComment() {
        String expected = "You can't enter more than 255 characters.";
        SoftAssert softAssert = new SoftAssert();
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
        WebElement errorMessage = orderDetailsPage.EnterCommentInput(TestHelpersUtils.generateRandomComment(256))
                .getCommentErrorMessage();
        softAssert.assertTrue(errorMessage.isDisplayed(), "Comment error message is not displayed");
        softAssert.assertEquals(errorMessage.getText(), expected, "Error message is not as required");
        softAssert.assertFalse(orderDetailsPage.getNextButton().isEnabled(), "Next button is enable");
        softAssert.assertAll();
    }

    @DataProvider(name = "correctComment-provider")
    public Object[][] dataProviderMethod() {
        return new String[][]{{TestHelpersUtils.generateRandomComment(138)}, {""}};
    }

    @Description("Verify that you can enter comment shorter than 255 characters")
    @Test(dataProvider = "correctComment-provider")
    public void correctLengthOfComment(String comment) {
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(driver);
        boolean isEnabled = orderDetailsPage.EnterCommentInput(comment)
                .getNextButton()
                .isEnabled();
        Assert.assertTrue(isEnabled, "Next button is disabled for correct length of comment");
    }


}