package com.ita.edu.greencity.tests.api.liqpay_status;

import com.ita.edu.greencity.api.clients.ubs.client.OrderClient;
import com.ita.edu.greencity.tests.api.ApiTestRunner;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class UnauthorizedLiqPayStatusTest extends ApiTestRunner {

    private final int ORDER_ID = 349;
    private final String ERROR_401_MESSAGE = "<html><body><h2>Error Page</h2><div>Status code: <b>401</b></div><div>Exception Message: <b>N/A</b></div><body></html>";

    private OrderClient orderClient;

    @BeforeClass
    public void beforeClass() throws IOException {
        orderClient = new OrderClient();
    }

    @Test
    @Description("[API] verify 401 error with unauthorized user")
    public void unsuccessfulGettingOfLiqPayStatusWithUnauthorizedUser() {
        Response response = orderClient.getLiqPayStatus(ORDER_ID);
        String liqPayStatus = response.then().extract().body().asString();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 401, "Status code is wrong!");
        softAssert.assertEquals(liqPayStatus, ERROR_401_MESSAGE, "Message is wrong!");
        softAssert.assertAll();
    }
}
