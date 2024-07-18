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

import java.io.IOException;

public class PostOrderCancellationReason extends ApiTestRunner {

    private Authorization authorization;
    private OrderClient orderClient;

    @BeforeClass
    public void beforeClass() throws IOException {
        authorization = new Authorization(provider.getEmail(), provider.getPassword());
        orderClient = new OrderClient(authorization.getToken());
    }

    @Test
    @Description("[API] Check success posting of order cancellation reason")
    public void successPostOrderCancellationReasonTest() {
        OrderCancellationReason orderCancellationReason = new OrderCancellationReason("thanks", CancellationReason.valueOf("MOVING_OUT"));
        Response response = orderClient.postOrderCancellationReason(orderCancellationReason, 417);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    @Description("[API] Check success posting of order cancellation reason")
    public void forbiddenPostOrderCancellationReasonTest() {
        OrderCancellationReason orderCancellationReason = new OrderCancellationReason("thanks", CancellationReason.valueOf("MOVING_OUT"));
        Response response = orderClient.postOrderCancellationReason(orderCancellationReason, 1);
        Assert.assertEquals(response.getStatusCode(), 403);
    }
}
