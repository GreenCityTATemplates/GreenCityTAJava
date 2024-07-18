package com.ita.edu.greencity.tests.api.order_history;

import com.ita.edu.greencity.api.clients.ubs.client.OrderClient;
import com.ita.edu.greencity.tests.api.ApiTestRunner;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class GetOrderHistoryUnauthorizedTest extends ApiTestRunner {
    private final int orderId = 89;
    private final int languageIdEn = 2;
    private OrderClient orderClient;

    @BeforeClass
    public void beforeClass() throws IOException {
        orderClient = new OrderClient();
    }

    @Test
    @Description("[API] Check unauthorized getting of order history by orderId")
    public void getOrderHistoryUnauthorized() {
        Response response = orderClient.getOrderHistory(orderId, languageIdEn);
        String orderHistory = response.then().extract().body().asString();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 401, "Status code isn't right!");
        softAssert.assertTrue(orderHistory.contains("Error Page"), "There isn't error message!");
        softAssert.assertAll();
    }
}
