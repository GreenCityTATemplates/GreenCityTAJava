package com.ita.edu.greencity.tests.api.order_history;

import com.ita.edu.greencity.api.clients.ubs.client.OrderClient;
import com.ita.edu.greencity.api.clients.user.sign_in.Authorization;
import com.ita.edu.greencity.api.models.ubs.client.order_history.SuccessOrderHistory;
import com.ita.edu.greencity.tests.api.ApiTestRunner;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class GetOrderHistorySuccessTest extends ApiTestRunner {

    private final int orderId = 140;
    private final int languageIdEn = 2;
    private final int firstNumOfOrderHistory = 0;
    private Authorization authorization;
    private OrderClient orderClient;

    @BeforeClass
    public void beforeClass() throws IOException {
        authorization = new Authorization(provider.getEmail(), provider.getPassword());
        orderClient = new OrderClient(authorization.getToken());
    }

    @Test
    @Description("[API] Check success getting of order history by orderId")
    public void successGetOrderHistory() {
        Response response = orderClient.getOrderHistory(orderId, languageIdEn);
        SuccessOrderHistory[] orderHistory = response.as(SuccessOrderHistory[].class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code isn't right!");
        softAssert.assertEquals(orderHistory.length, 1, "Size of records in response isn't right!");
        softAssert.assertEquals(orderHistory[firstNumOfOrderHistory].getId(), 633, "Id isn't right! ");
        softAssert.assertAll();
    }
}
