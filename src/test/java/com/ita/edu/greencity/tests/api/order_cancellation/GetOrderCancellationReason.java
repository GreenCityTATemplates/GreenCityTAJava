package com.ita.edu.greencity.tests.api.order_cancellation;

import com.ita.edu.greencity.api.clients.ubs.client.OrderClient;
import com.ita.edu.greencity.api.clients.user.sign_in.Authorization;
import com.ita.edu.greencity.api.models.ubs.client.order_cancellation.CancellationReason;
import com.ita.edu.greencity.api.models.ubs.client.order_cancellation.OrderCancellationReason;
import com.ita.edu.greencity.tests.api.ApiTestRunner;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class GetOrderCancellationReason extends ApiTestRunner {

    private Authorization authorization;
    private OrderClient orderClient;

    @BeforeClass
    public void beforeClass() throws IOException {
        authorization = new Authorization(provider.getEmail(), provider.getPassword());
        orderClient = new OrderClient(authorization.getToken());
    }

    @Test
    @Description("[API] Check success getting of order cancellation reason")
    public void successGetOrderCancellationReasonTest() {
        Response response = orderClient.getOrderCancellationReason(157);
        OrderCancellationReason orderCancellationReason = response.as(OrderCancellationReason.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200);
        softAssert.assertEquals(orderCancellationReason.getCancellationReason(), CancellationReason.valueOf("DELIVERED_HIMSELF"));
        softAssert.assertEquals(orderCancellationReason.getCancellationComment(), "string");
        softAssert.assertAll();
    }

    @Test
    @Description("[API] Check forbidden getting of order cancellation reason")
    public void forbiddenGetOrderCancellationReasonTest() {
        Response response = orderClient.getOrderCancellationReason(1);
        Assert.assertEquals(response.getStatusCode(), 403);
    }
}
