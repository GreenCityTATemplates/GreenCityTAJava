package com.ita.edu.greencity.tests.api.order_client;

import com.ita.edu.greencity.api.clients.ubs.client.OrderClient;
import com.ita.edu.greencity.api.clients.user.sign_in.Authorization;
import com.ita.edu.greencity.api.models.ubs.order.adress.AddressListRoot;
import com.ita.edu.greencity.tests.api.ApiTestRunner;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetAllAddressesForOrderTest extends ApiTestRunner {
    private OrderClient orderClient;

    @BeforeClass
    public void beforeClass() throws IOException {
        Authorization authorization = new Authorization(provider.getEmail(), provider.getPassword());
        orderClient = new OrderClient(authorization.getToken());
    }

    @Test
    public void successfulGetAllAddressesForOrderTest() {
        Response response = orderClient.getAllAddressesForOrder();
        AddressListRoot addressList = response.then().extract().as(AddressListRoot.class);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
