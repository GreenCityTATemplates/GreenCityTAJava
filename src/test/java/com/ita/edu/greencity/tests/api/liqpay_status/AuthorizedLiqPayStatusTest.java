package com.ita.edu.greencity.tests.api.liqpay_status;

import com.ita.edu.greencity.api.clients.ubs.client.OrderClient;
import com.ita.edu.greencity.api.clients.user.sign_in.Authorization;
import com.ita.edu.greencity.api.models.ubs.client.error_status_code.Forbidden;
import com.ita.edu.greencity.api.models.ubs.client.error_status_code.NotFound;
import com.ita.edu.greencity.tests.api.ApiTestRunner;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class AuthorizedLiqPayStatusTest extends ApiTestRunner {

    private final int NON_EXISTENT_ORDER_ID = 1111111111;
    private final int OTHERS_USER_ORDER_ID = 162;
    private final String INCORRECT_ORDER_ID = "order";
    private final String ERROR_404_MESSAGE = "Order with current id does not exist: ";
    private final String ERROR_403_MESSAGE = "Cannot access another user's payment status";

    private Authorization authorization;
    private OrderClient orderClient;

    @BeforeClass
    public void beforeClass() throws IOException {
        authorization = new Authorization(provider.getUserWithOrdersEmail(), provider.getPassword());
        orderClient = new OrderClient(authorization.getToken());
    }

    @Test
    @Description("[API] verify 404 error with non-existent order")
    public void unsuccessfulGettingOfLiqPayStatusWithNonExistentOrderTest() {
        Response response = orderClient.getLiqPayStatus(NON_EXISTENT_ORDER_ID);
        NotFound liqPayStatus = response.as(NotFound.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 404, "Status code is wrong!");
        softAssert.assertEquals(liqPayStatus.getMessage(), ERROR_404_MESSAGE, "Message is wrong!");
        softAssert.assertAll();
    }

    @Test
    @Description("[API] verify unsuccessful getting of liq pay status with another's user order")
    public void unsuccessfulGettingOfLiqPayStatusWithOthersUserOrderId() {
        Response response = orderClient.getLiqPayStatus(OTHERS_USER_ORDER_ID);
        Forbidden liqPayStatus = response.as(Forbidden.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 403, "Status code is wrong!");
        softAssert.assertEquals(liqPayStatus.getMessage(), ERROR_403_MESSAGE, "Message is wrong!");
        softAssert.assertAll();
    }

    @Test
    @Description("[API] verify 400 error with incorrect order id format")
    public void unsuccessfulGettingOfLiqPayStatusWithIncorrectOrderIdFormat() {
        Response response = orderClient.getLiqPayStatus(INCORRECT_ORDER_ID);
        Assert.assertEquals(response.getStatusCode(), 400, "Status code is wrong!");
    }
}
