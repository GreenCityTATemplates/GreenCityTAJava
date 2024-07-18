package com.ita.edu.greencity.tests.api.order_details;

import com.ita.edu.greencity.api.clients.ubs.client.OrderClient;
import com.ita.edu.greencity.tests.api.ApiTestRunner;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class GetOrderDetailsUnauthorizedTest extends ApiTestRunner {
    private OrderClient orderClient;

    @BeforeClass
    public void beforeClass() throws IOException {
        orderClient = new OrderClient();
    }

    @Test
    @Description("[API] Check unauthorized getting of order details")
    public void successGetOrderHistory() {
        Response response = orderClient.getOrderDetails();
        String orderDetails = response.then().extract().body().asString();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 401, "Status code isn't right!");
        softAssert.assertTrue(orderDetails.contains("Error Page"), "There isn't error message!");
        softAssert.assertAll();
    }
}
