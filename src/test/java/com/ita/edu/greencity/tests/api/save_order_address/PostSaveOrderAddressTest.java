package com.ita.edu.greencity.tests.api.save_order_address;

import com.ita.edu.greencity.api.clients.ubs.client.OrderClient;
import com.ita.edu.greencity.api.clients.user.sign_in.Authorization;
import com.ita.edu.greencity.api.models.ubs.client.SuccessReqSaveOrderAddress;
import com.ita.edu.greencity.api.models.ubs.client.SuccessSaveOrderAddress;
import com.ita.edu.greencity.tests.api.ApiTestRunner;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;

public class PostSaveOrderAddressTest extends ApiTestRunner {
    SuccessSaveOrderAddress saveOrderAddress = new SuccessSaveOrderAddress();
    SuccessReqSaveOrderAddress successReqSaveOrderAddress = new SuccessReqSaveOrderAddress();
    private Authorization authorization;
    private OrderClient orderClient;

    @Description("[API] Check success save order address")
    @Test
    public void successSaveAddressTest() throws IOException {
        authorization = new Authorization(provider.getEmail(), provider.getPassword());
        orderClient = new OrderClient(authorization.getToken());
        System.out.println("fsdfasdf" + successReqSaveOrderAddress.addressList);
        System.out.println(successReqSaveOrderAddress);
        SuccessReqSaveOrderAddress.AddressList addressList = new SuccessReqSaveOrderAddress.AddressList();

        Response response = orderClient.saveOrderAddressByRequest(addressList);
        SuccessSaveOrderAddress saveOrderAddress = response.as(SuccessSaveOrderAddress.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_CREATED, "Status code is equals");
        softAssert.assertAll();
        System.out.println(response.getStatusCode() + "  " + HttpURLConnection.HTTP_OK + "  " + HttpURLConnection.HTTP_CREATED);
    }

    @Description("[API] Checks if user not authorized before save order address")
    @Test
    public void unauthorizedSaveAddressTest() throws IOException {
        authorization = new Authorization(provider.getEmail(), provider.getPassword());
        orderClient = new OrderClient();
        SuccessReqSaveOrderAddress.AddressList addressList = new SuccessReqSaveOrderAddress.AddressList();
        Response response = orderClient.saveOrderAddressByRequest(addressList);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_UNAUTHORIZED, "Status code is equals");
        softAssert.assertAll();
        System.out.println(response.getStatusCode() + "  " + HttpURLConnection.HTTP_UNAUTHORIZED + "  " + HttpURLConnection.HTTP_CREATED);

    }
}
