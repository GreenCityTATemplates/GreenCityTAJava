package com.ita.edu.greencity.tests.api.order_history;

import com.ita.edu.greencity.api.clients.ubs.client.OrderClient;
import com.ita.edu.greencity.api.clients.user.sign_in.Authorization;
import com.ita.edu.greencity.api.models.ubs.client.error_status_code.Forbidden;
import com.ita.edu.greencity.tests.api.ApiTestRunner;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class GetOrderHistoryForbiddenTest extends ApiTestRunner {
    private final int orderId = 89;
    private final int languageIdEn = 2;
    private Authorization authorization;
    private OrderClient orderClient;

    @BeforeClass
    public void beforeClass() throws IOException {
        authorization = new Authorization(provider.getEmail(), provider.getPassword());
        orderClient = new OrderClient(authorization.getToken());
    }

    @Test
    @Description("[API] Check forbidden getting of order history by orderId")
    public void successGetOrderHistory() {
        Response response = orderClient.getOrderHistory(orderId, languageIdEn);
        Forbidden message = response.as(Forbidden.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 403, "Status code isn't right!");
        softAssert.assertEquals(message.getMessage(), "Cannot access another user's event history", "The message of response isn't right!");
        softAssert.assertAll();
    }
}
