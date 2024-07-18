package com.ita.edu.greencity.tests.api.order_client;

import com.ita.edu.greencity.api.clients.ubs.client.OrderClient;
import com.ita.edu.greencity.api.clients.user.sign_in.Authorization;
import com.ita.edu.greencity.api.models.ubs.order.process_order.*;
import com.ita.edu.greencity.tests.api.ApiTestRunner;
import io.qameta.allure.Description;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class ProcessOrderAuthorizedTest extends ApiTestRunner {
    private final int firstElementFromBadRequestList = 0;
    private OrderClient orderClient;

    @Description("creation of object with user personal data")
    public PersonalData createPersonalDataObject() {
        return new PersonalData(null,
                provider.getEmail(),
                "John", 12L, "Doe", "380631234499",
                null, null, null, null,
                199L);
    }

    @Description("creation of object with user order data")
    public UserOrder createUserOrderObject(PersonalData personalData) {
        return new UserOrder(
                new ArrayList<>(List.of("12345678")),
                161L,
                new ArrayList<>(List.of(new Bag(3L, 1L))),
                null,
                1L,
                null,
                personalData,
                0L,
                true);
    }

    @BeforeClass(description = "user authorization")
    public void beforeClass() throws IOException {
        Authorization authorization = new Authorization(provider.getEmail(), provider.getPassword());
        orderClient = new OrderClient(authorization.getToken());
    }

    @DataProvider(name = "additionalOrdersProvider")
    public Object[][] additionalOrdersProvider() {
        return new Object[][]{
                {"or__der", "0"}, {"-54", "11111111111111"}};
    }

    @DataProvider(name = "addressIdsProvider")
    public Object[][] addressIdsProvider() {
        return new Object[][]{
                {555L}, {0L}, {-77L}, {33333333L}};
    }

    @DataProvider(name = "certificatesProvider")
    public Object[][] certificatesProvider() {
        return new Object[][]{
                {"44444444"}, {"123"}, {"0"}, {"-456"}, {"or__der"}};
    }

    @DataProvider(name = "locationsProvider")
    public Object[][] locationsProvider() {
        return new Object[][]{
                {6L}, {0L}, {-783L}};
    }

    @DataProvider(name = "ubsUserIdsProvider")
    public Object[][] ubsUserIdsProvider() {
        return new Object[][]{
                {0L}, {-258L}, {888888888L}};
    }

    @Description("[API] verify 200 status code for successful user order")
    @Test
    public void successOrderTest() {
        UserOrder userOrder = createUserOrderObject(createPersonalDataObject());
        Response response = orderClient.processUserOrder(userOrder);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 200, "Mismatched status codes");
        softAssert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_OK, "Mismatched HTTP status codes");
        SuccessfulOrder successfulOrder = response.then().extract().as(SuccessfulOrder.class);
        softAssert.assertNotNull(successfulOrder.getOrderId(), "Order id is null");
        softAssert.assertTrue(successfulOrder.getLink().contains("https://pay.fondy.eu/merchants"), "Invalid link");
        softAssert.assertAll();
    }


    @Description("[API] verify additionalOrders array validation for letters and too long values")
    @Test(dataProvider = "additionalOrdersProvider")
    public void additionalOrderValuesValidationTest(String valuesForNumberFormat, String valuesForLength) {
        UserOrder userOrder = createUserOrderObject(createPersonalDataObject());

        userOrder.setAdditionalOrders(List.of(valuesForNumberFormat));
        Response responseForLettersValue = orderClient.processUserOrder(userOrder);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(responseForLettersValue.getStatusCode(), 400, "Mismatched status codes");
        softAssert.assertEquals(responseForLettersValue.getStatusCode(), HttpURLConnection.HTTP_BAD_REQUEST, "Mismatched HTTP status codes");
        List<BadRequest> listWithResponseForLettersValue = responseForLettersValue.as(new TypeRef<>() {});
        softAssert.assertEquals(listWithResponseForLettersValue
                .get(firstElementFromBadRequestList).getName(), "additionalOrders[]", "Mismatched letters value names");
        softAssert.assertEquals(listWithResponseForLettersValue
                .get(firstElementFromBadRequestList).getMessage(), "must match \"[0-9]+\"", "Mismatched letters value messages");

        userOrder.setAdditionalOrders(List.of(valuesForLength));
        Response responseForTooLongValue = orderClient.processUserOrder(userOrder);
        softAssert.assertEquals(responseForTooLongValue.getStatusCode(), 400, "Mismatched status codes");
        softAssert.assertEquals(responseForTooLongValue.getStatusCode(), HttpURLConnection.HTTP_BAD_REQUEST, "Mismatched HTTP status codes");
        List<BadRequest> listWithResponseTooLongValue = responseForTooLongValue.as(new TypeRef<>() {});
        softAssert.assertEquals(listWithResponseTooLongValue
                .get(firstElementFromBadRequestList).getName(), "additionalOrders[]", "Mismatched too long value names");
        softAssert.assertEquals(listWithResponseTooLongValue
                .get(firstElementFromBadRequestList).getMessage(), "length must be between 3 and 10", "Mismatched too long value messages");
        softAssert.assertAll();
    }

    @Description("[API] verify addressId field validation for positive, zero, negative and too long values")
    @Test(dataProvider = "addressIdsProvider")
    public void addressIdValuesValidationTest(Long addressId) {
        UserOrder userOrder = createUserOrderObject(createPersonalDataObject());
        userOrder.setAddressId(addressId);
        Response responseForIncorrectValue = orderClient.processUserOrder(userOrder);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(responseForIncorrectValue.getStatusCode(), 404, "Mismatched status codes");
        softAssert.assertEquals(responseForIncorrectValue.getStatusCode(), HttpURLConnection.HTTP_NOT_FOUND, "Mismatched HTTP status codes");
        BadRequest badRequestForIncorrectValue = responseForIncorrectValue.as(BadRequest.class);
        softAssert.assertEquals(badRequestForIncorrectValue.getMessage(), "Not found address with such id: " + addressId, "Mismatched messages");
        softAssert.assertAll();
    }


    @Description("[API] verify bags array fields validation for small, zero and too big values")
    @Test
    public void bagsValuesValidationTest() {
        UserOrder userOrder = createUserOrderObject(createPersonalDataObject());

        userOrder.setBags(List.of(new Bag(0L, 0L)));
        Response responseForZeroValues = orderClient.processUserOrder(userOrder);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(responseForZeroValues.getStatusCode(), 400, "Mismatched status codes");
        softAssert.assertEquals(responseForZeroValues.getStatusCode(), HttpURLConnection.HTTP_BAD_REQUEST, "Mismatched HTTP status codes");
        List<BadRequest> badRequestForZeroValues = responseForZeroValues.as(new TypeRef<>() {});
        softAssert.assertEquals(badRequestForZeroValues.get(firstElementFromBadRequestList)
                .getName(), "bags[0].amount","Mismatched amount field names");
        softAssert.assertEquals(badRequestForZeroValues.get(firstElementFromBadRequestList)
                .getMessage(), "must be between 1 and 999", "Mismatched amount field messages");
        int secondElementFromBadRequestList = 1;
        softAssert.assertEquals(badRequestForZeroValues.get(secondElementFromBadRequestList)
                .getName(), "bags[0].id","Mismatched id field names");
        softAssert.assertEquals(badRequestForZeroValues.get(secondElementFromBadRequestList)
                .getMessage(), "must be greater than or equal to 1", "Mismatched id field messages");

        userOrder.setBags(List.of(new Bag(3L, 99999L)));
        Response responseForTooBigValues = orderClient.processUserOrder(userOrder);
        softAssert.assertEquals(responseForTooBigValues.getStatusCode(), 404, "Mismatched status codes");
        softAssert.assertEquals(responseForTooBigValues.getStatusCode(), HttpURLConnection.HTTP_NOT_FOUND, "Mismatched HTTP status codes");
        BadRequest badRequestForTooBigValues = responseForTooBigValues.as(BadRequest.class);
        softAssert.assertEquals(badRequestForTooBigValues.getMessage(), "Bag does not exist by id: 99999", "Mismatched too big values messages");

        userOrder.setBags(List.of(new Bag(1L, 3L)));
        Response responseForSmallBags = orderClient.processUserOrder(userOrder);
        softAssert.assertEquals(responseForSmallBags.getStatusCode(), 400, "Mismatched status codes");
        softAssert.assertEquals(responseForSmallBags.getStatusCode(), HttpURLConnection.HTTP_BAD_REQUEST, "Mismatched HTTP status codes");
        BadRequest badRequestForSmallBags = responseForSmallBags.as(BadRequest.class);
        softAssert.assertEquals(badRequestForSmallBags.getMessage(), "Not enough big bags, minimal amount is:2", "Mismatched small bags messages");
        softAssert.assertAll();

    }


    @Description("[API] verify certificates array validation for positive, zero, negative, letters and too long values")
    @Test(dataProvider = "certificatesProvider")
    public void certificatesValuesValidationTest(String certificate) {
        UserOrder userOrder = createUserOrderObject(createPersonalDataObject());
        userOrder.setCertificates(List.of(certificate));
        Response response = orderClient.processUserOrder(userOrder);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 400, "Mismatched status codes");
        softAssert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_BAD_REQUEST, "Mismatched HTTP status codes");
        List<BadRequest> badRequests = response.as(new TypeRef<>() {});
        softAssert.assertEquals(badRequests.get(firstElementFromBadRequestList)
                .getName(), "certificates[]", "Mismatched names");
        softAssert.assertEquals(badRequests.get(firstElementFromBadRequestList)
                .getMessage(), "This certificate code is not valid", "Mismatched messages");
        softAssert.assertAll();
    }


    @Description("[API] verify locationId field validation for positive, zero and negative values")
    @Test(dataProvider = "locationsProvider")
    public void locationIdValidationTest(Long locationId) {
        UserOrder userOrder = createUserOrderObject(createPersonalDataObject());

        userOrder.setLocationId(locationId);
        Response response = orderClient.processUserOrder(userOrder);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 500, "Mismatched status codes");
        softAssert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_INTERNAL_ERROR, "Mismatched HTTP status codes");
        softAssert.assertEquals(response.then().extract().body().asString(), "<html><body><h2>Error Page</h2><div>Status code: <b>500</b></div><div>Exception Message: <b>Request processing failed; nested exception is java.lang.NullPointerException</b></div><body></html>", "Mismatched response links");
        softAssert.assertAll();
    }

    @Description("[API] verify firstName field validation for value with numbers ans special characters")
    @Test
    public void incorrectFirstNameTest() {
        PersonalData personalData = createPersonalDataObject();
        personalData.setFirstName("Test-User123");
        UserOrder userOrder = createUserOrderObject(personalData);
        Response response = orderClient.processUserOrder(userOrder);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 400, "Mismatched status codes");
        softAssert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_BAD_REQUEST, "Mismatched HTTP status codes");
        List<BadRequest> badRequests = response.as(new TypeRef<>() {});
        softAssert.assertEquals(badRequests.get(firstElementFromBadRequestList)
                .getName(), "personalData.firstName", "Mismatched names");
        softAssert.assertEquals(badRequests.get(firstElementFromBadRequestList)
                .getMessage(), "must match \"[ЁёІіЇїҐґЄєА-Яа-яA-Za-z-'\\s.]{1,30}\"", "Mismatched messages");
        softAssert.assertAll();
    }


    @Description("[API] verify lastName field validation for value with numbers ans special characters")
    @Test
    public void incorrectLastNameTest() {
        PersonalData personalData = createPersonalDataObject();
        personalData.setLastName("UserName_123");
        UserOrder userOrder = createUserOrderObject(personalData);
        Response response = orderClient.processUserOrder(userOrder);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 400, "Mismatched status codes");
        softAssert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_BAD_REQUEST, "Mismatched HTTP status codes");
        List<BadRequest> badRequests = response.as(new TypeRef<>() {});
        softAssert.assertEquals(badRequests.get(firstElementFromBadRequestList)
                .getName(), "personalData.lastName", "Mismatched names");
        softAssert.assertEquals(badRequests.get(firstElementFromBadRequestList)
                .getMessage(), "must match \"[ЁёІіЇїҐґЄєА-Яа-яA-Za-z\\s-'.]{1,30}\"", "Mismatched messages");
        softAssert.assertAll();
    }


    @Description("[API] verify id field validation for zero value ")
    @Test
    public void incorrectIdTest() {
        PersonalData personalData = createPersonalDataObject();
        personalData.setId(0L);
        UserOrder userOrder = createUserOrderObject(personalData);
        Response response = orderClient.processUserOrder(userOrder);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 400, "Mismatched status codes");
        softAssert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_BAD_REQUEST, "Mismatched HTTP status codes");
        List<BadRequest> badRequests = response.as(new TypeRef<>() {});
        softAssert.assertEquals(badRequests.get(firstElementFromBadRequestList)
                .getName(), "personalData.id", "Mismatched names");
        softAssert.assertEquals(badRequests.get(firstElementFromBadRequestList)
                .getMessage(), "must be greater than or equal to 1", "Mismatched messages");
        softAssert.assertAll();
    }


    @Description("[API] verify email field validation for incorrect formatted value")
    @Test
    public void incorrectEmailTest() {
        PersonalData personalData = createPersonalDataObject();
        personalData.setEmail("userEmail");
        UserOrder userOrder = createUserOrderObject(personalData);
        Response response = orderClient.processUserOrder(userOrder);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 400, "Mismatched status codes");
        softAssert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_BAD_REQUEST, "Mismatched HTTP status codes");
        List<BadRequest> badRequests = response.as(new TypeRef<>() {});
        softAssert.assertEquals(badRequests.get(firstElementFromBadRequestList)
                .getName(), "personalData.email", "Mismatched names");
        softAssert.assertEquals(badRequests.get(firstElementFromBadRequestList)
                .getMessage(), "must be a well-formed email address", "Mismatched messages");
        softAssert.assertAll();
    }


    @Description("[API] verify phoneNumber field validation for value with invalid format")
    @Test
    public void incorrectPhoneNumberTest() {
        PersonalData personalData = createPersonalDataObject();
        personalData.setPhoneNumber("123631234499");
        UserOrder userOrder = createUserOrderObject(personalData);
        Response response = orderClient.processUserOrder(userOrder);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 400, "Mismatched status codes");
        softAssert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_BAD_REQUEST, "Mismatched HTTP status codes");
        List<BadRequest> badRequests = response.as(new TypeRef<>() {});
        softAssert.assertEquals(badRequests.get(firstElementFromBadRequestList)
                .getName(), "personalData.phoneNumber", "Mismatched names");
        softAssert.assertEquals(badRequests.get(firstElementFromBadRequestList)
                .getMessage(), "Invalid phone number format.", "Mismatched messages");
        softAssert.assertAll();
    }


    @Description("[API] verify ubsUserId field validation for zero, negative and too long values")
    @Test(dataProvider = "ubsUserIdsProvider")
    public void ubsUserIdValuesValidationTest(Long ubsUserId) {
        PersonalData personalData = createPersonalDataObject();
        personalData.setUbsUserId(ubsUserId);
        UserOrder userOrder = createUserOrderObject(personalData);
        Response response = orderClient.processUserOrder(userOrder);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), 400, "Mismatched status codes");
        softAssert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_BAD_REQUEST, "Mismatched HTTP status codes");
        BadRequest badRequest = response.as(BadRequest.class);
        softAssert.assertEquals(badRequest
                .getMessage(), "The set of user data does not exist with id: " + ubsUserId, "Mismatched messages");
        softAssert.assertAll();
    }


    @Description("[API] verify pointsToUse field validation for too big and negative value")
    @Test
    public void pointsToUseValuesValidationTest() {
        UserOrder userOrder = createUserOrderObject(createPersonalDataObject());

        userOrder.setPointsToUse(999999L);
        Response responseForTooBigValue = orderClient.processUserOrder(userOrder);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(responseForTooBigValue.getStatusCode(), 400, "Mismatched status codes");
        softAssert.assertEquals(responseForTooBigValue.getStatusCode(), HttpURLConnection.HTTP_BAD_REQUEST, "Mismatched HTTP status codes");
        BadRequest badRequestForTooBigValue = responseForTooBigValue.as(BadRequest.class);
        softAssert.assertEquals(badRequestForTooBigValue
                .getMessage(), "User doesn't have enough bonus points.", "Mismatched messages");

        userOrder.setPointsToUse(-999L);
        Response responseForNegativeValue = orderClient.processUserOrder(userOrder);
        softAssert.assertEquals(responseForNegativeValue.getStatusCode(), 400, "Mismatched status codes");
        softAssert.assertEquals(responseForNegativeValue.getStatusCode(), HttpURLConnection.HTTP_BAD_REQUEST, "Mismatched HTTP status codes");
        List<BadRequest> badRequestForNegativeValue = responseForNegativeValue.as(new TypeRef<>() {});
        softAssert.assertEquals(badRequestForNegativeValue.get(firstElementFromBadRequestList)
                .getName(), "pointsToUse", "Mismatched names");
        softAssert.assertEquals(badRequestForNegativeValue.get(firstElementFromBadRequestList)
                .getMessage(), "must be greater than or equal to 0", "Mismatched messages");
        softAssert.assertAll();
    }


    @Description("[API] verify shouldBePaid field validation for true and false value")
    @Test
    public void shouldBePaidParameterValidationTest() {
        UserOrder userOrder = createUserOrderObject(createPersonalDataObject());
        userOrder.setShouldBePaid(true);
        Response responseForTrueValue = orderClient.processUserOrder(userOrder);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(responseForTrueValue.getStatusCode(), 200, "Mismatched status codes");
        softAssert.assertEquals(responseForTrueValue.getStatusCode(), HttpURLConnection.HTTP_OK, "Mismatched HTTP status codes");
        SuccessfulOrder successfulOrderForTrueValue = responseForTrueValue.then().extract().as(SuccessfulOrder.class);
        softAssert.assertTrue(successfulOrderForTrueValue.getLink().contains("https://pay.fondy.eu/merchants/b987e1aa765ebe6d5e76c027acb02cf7ba866d92/default/index.html"), "Invalid response link");

        userOrder.setShouldBePaid(false);
        Response responseForFalseValue = orderClient.processUserOrder(userOrder);
        softAssert.assertEquals(responseForFalseValue.getStatusCode(), 200, "Mismatched status codes");
        softAssert.assertEquals(responseForFalseValue.getStatusCode(), HttpURLConnection.HTTP_OK, "Mismatched HTTP status codes");
        SuccessfulOrder successfulOrderForFalseValue = responseForFalseValue.then().extract().as(SuccessfulOrder.class);
        softAssert.assertEquals(successfulOrderForFalseValue.getLink(), null, "Link is not null");
        softAssert.assertAll();
    }


}
