package com.ita.edu.greencity.tests.api.order_client;

import com.ita.edu.greencity.api.clients.ubs.client.OrderClient;
import com.ita.edu.greencity.api.clients.user.sign_in.Authorization;
import com.ita.edu.greencity.api.models.ubs.client.SuccessDeleteOrderAddress;
import com.ita.edu.greencity.api.models.ubs.client.error_status_code.Forbidden;
import com.ita.edu.greencity.api.models.ubs.client.error_status_code.NotFound;
import com.ita.edu.greencity.tests.api.ApiTestRunner;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;

public class DeleteAddressByIdTest extends ApiTestRunner {
    private Authorization authorization;
    private OrderClient orderClient;

    final Long NOT_FOUND_ID_ADDRESS = 12L;
    final Long ACTUAL_ID_ADDRESS = 207L;
    final Long ANOTHER_CLIENT_ACTUAL_ID_ADDRESS = 62L;


    @BeforeClass
    public void beforeClass() throws IOException {
        authorization = new Authorization(provider.getEmail(), provider.getPassword());
        orderClient = new OrderClient(authorization.getToken());
    }

    @Description("[API] Check the receipt of information with trying delete address with poor id")
    @Test
    public void notFoundIdAddress() {
        Response response = orderClient.deleteOrderAddressById(NOT_FOUND_ID_ADDRESS);
        NotFound notFoundIdAddress = response.as(NotFound.class);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_NOT_FOUND, "Status code not equals");
        softAssert.assertEquals(notFoundIdAddress.getMessage(), "Not found address with such id: " + NOT_FOUND_ID_ADDRESS);
        softAssert.assertAll();
    }

    @Description("[API] Verify success delete address by id")
    @Test
    public void successDeleteAddressById() {
        Response response = orderClient.deleteOrderAddressById(ACTUAL_ID_ADDRESS);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_OK, "Status code not equals");
        orderClient.deleteOrderAddressById(ACTUAL_ID_ADDRESS);
        softAssert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_BAD_REQUEST, "Status code not equals");
        softAssert.assertAll();
    }

    @Description("[API] Checks for inability to delete addresses when unauthorized")
    @Test
    public void unauthorizedDeleteAddressByIdTest() throws IOException {
        orderClient = new OrderClient();
        Response response = orderClient.deleteOrderAddressById(ACTUAL_ID_ADDRESS);
        Assert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_UNAUTHORIZED, "Status code not equals");
        orderClient.deleteOrderAddressById(NOT_FOUND_ID_ADDRESS);
        Assert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_UNAUTHORIZED, "Status code not equals");
    }

    @Description("[API] Checks for inability to delete addresses another client")
    @Test
    public void deleteAnotherClientAddressById() {
        Response response = orderClient.deleteOrderAddressById(ANOTHER_CLIENT_ACTUAL_ID_ADDRESS);
        Forbidden forbiddenMessage = response.as(Forbidden.class);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_FORBIDDEN, "Status code not equals");
        softAssert.assertEquals(forbiddenMessage.getMessage(), "Cannot delete another user's address");
        softAssert.assertAll();
    }
}
