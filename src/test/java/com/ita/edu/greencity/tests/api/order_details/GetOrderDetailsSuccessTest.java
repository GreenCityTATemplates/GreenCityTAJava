package com.ita.edu.greencity.tests.api.order_details;

import com.ita.edu.greencity.api.clients.ubs.client.OrderClient;
import com.ita.edu.greencity.api.clients.user.sign_in.Authorization;
import com.ita.edu.greencity.api.models.ubs.client.order_details.SuccessOrderDetails;
import com.ita.edu.greencity.tests.api.ApiTestRunner;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class GetOrderDetailsSuccessTest extends ApiTestRunner {
    private Authorization authorization;
    private OrderClient orderClient;

    @BeforeClass
    public void beforeClass() throws IOException {
        authorization = new Authorization(provider.getEmail(), provider.getPassword());
        orderClient = new OrderClient(authorization.getToken());
    }

    @Test
    @Description("[API] Check success getting of order details")
    public void successGetOrderHistory() {
        Response response = orderClient.getOrderDetails();
        SuccessOrderDetails orderDetails = response.as(SuccessOrderDetails.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code isn't right!");
        softAssert.assertEquals(orderDetails.getPoints(), 700, "Number of points in response isn't right!");
        softAssert.assertEquals(orderDetails.getBags().size(), 3, "Number of bags in response isn't right!");
        softAssert.assertAll();
    }
}
