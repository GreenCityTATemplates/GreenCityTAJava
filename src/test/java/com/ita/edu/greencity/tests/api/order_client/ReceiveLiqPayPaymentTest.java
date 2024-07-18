package com.ita.edu.greencity.tests.api.order_client;

import com.ita.edu.greencity.api.clients.ubs.client.OrderClient;
import com.ita.edu.greencity.api.clients.user.sign_in.Authorization;
import com.ita.edu.greencity.api.models.ubs.order.SuccessLiqPayPayment;
import com.ita.edu.greencity.tests.api.ApiTestRunner;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;

public class ReceiveLiqPayPaymentTest extends ApiTestRunner {
    private OrderClient orderClient;

    @BeforeClass(description = "user authorization")
    public void beforeClass() throws IOException {
        Authorization authorization = new Authorization(provider.getEmail(), provider.getPassword());
        orderClient = new OrderClient(authorization.getToken());
    }

    @Description("[API] verify 302 status code for correct data and signature")
    @Test
    public void successfulRedirectionStatusCodeTest() {
        Response response = orderClient.receiveLiqPayPayment(provider.getCorrectData(), provider.getCorrectSignature());
        Assert.assertEquals(response.getStatusCode(), 302, "Mismatched status codes");
        Assert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_MOVED_TEMP, "Mismatched HTTP status codes");
    }

    @Description("[API] verify 400 status code for incorrect data and signature ")
    @Test
    public void unsuccessfulRedirectionStatusCodeTest() {
        Response response = orderClient.receiveLiqPayPayment(provider.getIncorrectData(), provider.getIncorrectSignature());
        Assert.assertEquals(response.getStatusCode(), 400, "Mismatched status codes");
        Assert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_BAD_REQUEST, "Mismatched HTTP status codes");
        SuccessLiqPayPayment successLiqPayPayment = response.then().extract().as(SuccessLiqPayPayment.class);
        Assert.assertEquals(successLiqPayPayment.getMessage(),"The received payment data is not valid.","Mismatched messages");
    }

}
