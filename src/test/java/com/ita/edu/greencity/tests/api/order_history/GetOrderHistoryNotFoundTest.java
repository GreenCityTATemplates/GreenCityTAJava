package com.ita.edu.greencity.tests.api.order_history;

import com.ita.edu.greencity.api.clients.ubs.client.OrderClient;
import com.ita.edu.greencity.api.clients.user.sign_in.Authorization;
import com.ita.edu.greencity.api.models.ubs.client.error_status_code.NotFound;
import com.ita.edu.greencity.tests.api.ApiTestRunner;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class GetOrderHistoryNotFoundTest extends ApiTestRunner {
    private final int incorrectOrderId = -1;
    private final int languageIdEn = 2;
    private Authorization authorization;
    private OrderClient orderClient;

    @BeforeClass
    public void beforeClass() throws IOException {
        authorization = new Authorization(provider.getEmail(), provider.getPassword());
        orderClient = new OrderClient(authorization.getToken());
    }

    @Test
    @Description("[API] Check getting of order history by orderId that does not exist")
    public void successGetOrderHistory() {
        Response response = orderClient.getOrderHistory(incorrectOrderId, languageIdEn);
        NotFound orderHistory = response.as(NotFound.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 404, "Status code isn't right!");
        softAssert.assertEquals(orderHistory.getMessage(), "Order with current id does not exist: ", "Message isn't right! ");
        softAssert.assertAll();
    }
}
