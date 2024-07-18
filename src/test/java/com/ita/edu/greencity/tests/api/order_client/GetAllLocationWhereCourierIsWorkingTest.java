package com.ita.edu.greencity.tests.api.order_client;

import com.ita.edu.greencity.api.clients.ubs.client.OrderClient;
import com.ita.edu.greencity.api.clients.user.sign_in.Authorization;
import com.ita.edu.greencity.api.models.ubs.order.get_courier_locations.CourierLocationsRoot;
import com.ita.edu.greencity.api.models.ubs.order.get_courier_locations.ErrorMessage;
import com.ita.edu.greencity.tests.api.ApiTestRunner;
import com.ita.edu.greencity.tests.utils.TestHelpersUtils;
import com.ita.edu.greencity.utils.jdbc.services.UbsCourierService;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;

public class GetAllLocationWhereCourierIsWorkingTest extends ApiTestRunner {
    UbsCourierService ubsCourierService = new UbsCourierService();
    private OrderClient orderClient;
    private final int correctCourierId = Integer.parseInt(ubsCourierService.selectRandomUbsCourier());
    private final String wrongCourierId = String.valueOf(TestHelpersUtils.generateRandomWrongCourierIdNumber());

    @BeforeClass
    public void beforeClass() throws IOException {
        Authorization authorization = new Authorization(provider.getEmail(), provider.getPassword());
        orderClient = new OrderClient(authorization.getToken());
    }

    @Test
    public void successfulGetAllCourierAddressesTest() {
        Response response = orderClient.getAllCourierLocations("correctCourierId");
        List<CourierLocationsRoot> courierLocations = response.as(new TypeRef<>() {
        });//Тип данних є масивом тому робимо ліст і тайпреф
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void wrong400CourierIdGetAllCourierAddressesTest() {
        Response response = orderClient.getAllCourierLocations(wrongCourierId);
        ErrorMessage message = response.as(ErrorMessage.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 400);
        softAssert.assertEquals(message.getMessage(), "Couldn't found courier by id: " + wrongCourierId);
        softAssert.assertAll();
    }

}
