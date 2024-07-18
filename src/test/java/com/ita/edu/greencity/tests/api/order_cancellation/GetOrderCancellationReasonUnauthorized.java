package com.ita.edu.greencity.tests.api.order_cancellation;

import com.ita.edu.greencity.api.clients.ubs.client.OrderClient;
import com.ita.edu.greencity.tests.api.ApiTestRunner;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetOrderCancellationReasonUnauthorized extends ApiTestRunner {
    private OrderClient orderClient;

    @BeforeClass
    public void beforeClass() throws IOException {
        orderClient = new OrderClient();
    }

    @Test
    @Description("[API] Check unauthorized getting of order cancellation reason")
    public void unauthorizedGetOrderCancellationReasonTest() {
        Response response = orderClient.getOrderCancellationReason(1);
        Assert.assertEquals(response.getStatusCode(), 401);
    }
}
