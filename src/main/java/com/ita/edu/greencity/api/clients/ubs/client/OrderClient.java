package com.ita.edu.greencity.api.clients.ubs.client;

import com.ita.edu.greencity.api.clients.ubs.BaseClientUBS;
import com.ita.edu.greencity.api.models.ubs.client.ReqUpdateRecipientsData;
import com.ita.edu.greencity.api.models.ubs.client.SuccessReqSaveOrderAddress;
import com.ita.edu.greencity.api.models.ubs.client.order_cancellation.OrderCancellationReason;
import com.ita.edu.greencity.api.models.ubs.order.process_order.UserOrder;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;

public class OrderClient extends BaseClientUBS {
    private final String authToken;


    public OrderClient() throws IOException {
        super();
        authToken = null;
    }

    public OrderClient(String authToken) throws IOException {
        super();
        this.authToken = authToken;
    }

    @Step("get request {this.baseApiURL}/order_history ")
    public Response getOrderHistory(int orderId, int languageId) {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .log()
                .all()
                .when()
                .get(String.format("%s/order_history/%d?lang=%d", baseApiURL, orderId, languageId));
    }

    @Step("get request {this.baseApiURL}/order-details ")
    public Response getOrderDetails() {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .log()
                .all()
                .when()
                .get(String.format("%s/order-details", baseApiURL));
    }

    @Step("post request {this.baseApiURL}/receiveLiqPayPayment")
    public Response receiveLiqPayPayment(String data, String signature) {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .contentType("multipart/form-data")
                .multiPart("data", data)
                .multiPart("signature", signature)
                .log().all()
                .when()
                .post(String.format("%s/receiveLiqPayPayment", baseApiURL));
    }


    @Step("post request {this.baseApiURL}/ubs/findAll-order-address")
    public Response getAllAddressesForOrder() {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .log().all()
                .when()

                .get(String.format("%s/findAll-order-address", baseApiURL));
    }

    @Step("post request {this.baseApiURL}/ubs/courier/{courierId}")
    public Response getAllCourierLocations(String courierId) {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .log().all()
                .when()
                .get(String.format("%s/courier/" + courierId, baseApiURL));
    }

    @Step("get request {this.baseApiURL}/order/{id}/cancellation ")
    public Response getOrderCancellationReason(int id) {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .log()
                .all()
                .when()
                .get(String.format("%s/order/%d/cancellation", baseApiURL, id));
    }

    @Step("post request {this.baseApiURL}/order/{id}/cancellation ")
    public Response postOrderCancellationReason(OrderCancellationReason orderCancellationReason, int id) {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .body(orderCancellationReason)
                .log()
                .all()
                .when()
                .post(String.format("%s/order/%d/cancellation/", baseApiURL, id));
    }

    @Step("get request {this.baseApiURL}/certificate ")
    public Response getCertificatesInfo(String certificate) {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .log()
                .all()
                .when()
                .get(String.format("certificate/%s", certificate));

    }

    @Step("post request {this.baseApiURL}/processOrder")
    public Response processUserOrder(UserOrder userOrder) {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .body(userOrder)
                .log().all()
                .when()
                .post(String.format("%s/processOrder", baseApiURL));
    }


    @Step("get request {this.baseApiURL}/delete-order-address ")
    public Response deleteOrderAddressById(Long id) {

        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .log()
                .all()
                .when()
                .post(String.format("%s/delete-order-address", id));

    }

    @Step("post request {this.baseApiURL}/save-order-address")
    public Response saveOrderAddressByRequest(SuccessReqSaveOrderAddress.AddressList addressList) {
        RequestSpecification requestSpecification = preparedRequest();
        if (authToken != null) {
            requestSpecification.header("Authorization", String.format("Bearer %s", authToken));
        }
        return requestSpecification
                .body(addressList)
                .post(String.format("%s/save-order-address", baseApiURL));
    }


    @Step("put request {this.baseApiURL}/update-recipients-data")
    public Response reqUpdateRecipientsData(ReqUpdateRecipientsData upDataList) {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .body(upDataList)
                .post(String.format("%s/update-recipients-data", baseApiURL));

    }

    @Step("get request {this.baseApiURL}/getFondyStatus")
    public Response getFondyStatus(int orderId) {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .log()
                .all()
                .when()
                .get(String.format("%s/getFondyStatus/%d", baseApiURL, orderId));
    }

    @Step("get request {this.baseApiURL}/getFondyStatus")
    public Response getFondyStatus(String orderId) {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .log()
                .all()
                .when()
                .get(String.format("%s/getFondyStatus/%s", baseApiURL, orderId));
    }

    @Step("get request {this.baseApiURL}/getLiqPayStatus")
    public Response getLiqPayStatus(int orderId) {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .log()
                .all()
                .when()
                .get(String.format("%s/getLiqPayStatus/%d", baseApiURL, orderId));
    }

    @Step("get request {this.baseApiURL}/getLiqPayStatus")
    public Response getLiqPayStatus(String orderId) {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .log()
                .all()
                .when()
                .get(String.format("%s/getLiqPayStatus/%s", baseApiURL, orderId));
    }
}