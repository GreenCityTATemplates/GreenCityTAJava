package com.ita.edu.greencity.tests.api.order_client;

import com.ita.edu.greencity.api.clients.ubs.client.OrderClient;
import com.ita.edu.greencity.api.models.ubs.order.process_order.Bag;
import com.ita.edu.greencity.api.models.ubs.order.process_order.PersonalData;
import com.ita.edu.greencity.api.models.ubs.order.process_order.UserOrder;
import com.ita.edu.greencity.tests.api.ApiTestRunner;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class ProcessOrderNotAuthorizedTest extends ApiTestRunner {

    @BeforeClass(description = "create object of user order")
    public UserOrder beforeClass() throws IOException {
        return new UserOrder(
                new ArrayList<>(List.of("12345678")),
                161L,
                new ArrayList<>(List.of(new Bag(3L, 1L))),
                null,
                1L,
                null,
                new PersonalData(null,
                        provider.getEmail(),
                        "John", 12L, "Doe", "380631234499",
                        null, null, null, null,
                        199L),
                0L,
                true);
    }

    @Description("[API] verify 401 status code for unauthorized user")
    @Test()
    public void unauthorizedUserTest() throws IOException {
        OrderClient orderClient = new OrderClient();
        UserOrder userOrder = beforeClass();
        Response response = orderClient.processUserOrder(userOrder);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 401, "Mismatched status codes");
        softAssert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_UNAUTHORIZED, "Mismatched HTTP status codes");
        softAssert.assertEquals(response.then().extract().body().asString(), "<html><body><h2>Error Page</h2><div>Status code: <b>401</b></div><div>Exception Message: <b>N/A</b></div><body></html>", "Mismatched response strings");
        softAssert.assertAll();
    }

}
