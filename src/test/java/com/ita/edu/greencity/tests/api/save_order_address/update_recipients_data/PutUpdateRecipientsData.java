package com.ita.edu.greencity.tests.api.save_order_address.update_recipients_data;

import com.ita.edu.greencity.api.clients.ubs.client.OrderClient;
import com.ita.edu.greencity.api.clients.user.sign_in.Authorization;
import com.ita.edu.greencity.api.models.ubs.client.ReqUpdateRecipientsData;
import com.ita.edu.greencity.api.models.ubs.client.ResponseUpdateRecipientsData;
import com.ita.edu.greencity.api.models.ubs.client.SuccessReqSaveOrderAddress;
import com.ita.edu.greencity.api.models.ubs.client.SuccessSaveOrderAddress;
import com.ita.edu.greencity.tests.api.ApiTestRunner;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;

public class PutUpdateRecipientsData extends ApiTestRunner {

    SuccessSaveOrderAddress saveOrderAddress = new SuccessSaveOrderAddress();
    SuccessReqSaveOrderAddress successReqSaveOrderAddress = new SuccessReqSaveOrderAddress();
    ReqUpdateRecipientsData reqUpdateRecipientsData = new ReqUpdateRecipientsData();
    ResponseUpdateRecipientsData responseUpdateRecipientsData = new ResponseUpdateRecipientsData();
    private Authorization authorization;
    private OrderClient orderClient;

    @Description("[API] Check success save order address")
    @Test
    public void successUpdataRecipientsData() throws IOException {
        authorization = new Authorization("setupb1@ukr.net", "Test-123");
        orderClient = new OrderClient(authorization.getToken());
        System.out.println("fsdfasdf" + reqUpdateRecipientsData.upDataList);
        System.out.println(reqUpdateRecipientsData);
        ReqUpdateRecipientsData upDataList = new ReqUpdateRecipientsData();

        Response response = orderClient.reqUpdateRecipientsData(upDataList);
        System.out.println("++++++++++++++++++" + response.getStatusCode() + "  " + response.getBody().toString() + "----------------------");

        String responseBody = response.then().extract().body().toString();
        System.out.println(responseBody + "+++++++++");
        System.out.println(responseBody.getClass().getSimpleName() + "+++++++++");
        System.out.println(responseBody + "+++++++++");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_BAD_METHOD, "Status code is equals");
        softAssert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_OK, "Status code is equals");
        softAssert.assertAll();
        System.out.println(response.getStatusCode() + "  " + HttpURLConnection.HTTP_OK + "  " + HttpURLConnection.HTTP_CREATED);
    }
}
