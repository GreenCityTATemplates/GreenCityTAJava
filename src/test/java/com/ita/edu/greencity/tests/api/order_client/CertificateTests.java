package com.ita.edu.greencity.tests.api.order_client;

import com.ita.edu.greencity.api.clients.ubs.client.OrderClient;
import com.ita.edu.greencity.api.clients.user.sign_in.Authorization;
import com.ita.edu.greencity.api.models.ubs.client.UbsCertificate;
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

public class CertificateTests extends ApiTestRunner {
    final String USED_CERTIFICATE = "4444-4444";
    final Long USED_CERTIFICATE_POINT = 500L;
    final String USED_CERTIFICATE_STATUS = "USED";
    final String USED_CERTIFICATE_DATE = "2022-02-07";
    final String ACTIVE_CERTIFICATE = "2667-5351";
    final Long ACTIVE_CERTIFICATE_POINT = 500L;
    final String ACTIVE_CERTIFICATE_STATUS = "ACTIVE";
    final String ACTIVE_CERTIFICATE_DATE = "2022-11-11";
    final String EXPIRED_CERTIFICATE = "3003-1988";
    final Long EXPIRED_CERTIFICATE_POINT = 1000L;
    final String EXPIRED_CERTIFICATE_STATUS = "EXPIRED";
    final String EXPIRED_CERTIFICATE_DATE = "2022-02-17";
    final String NOT_FOUND_CERTIFICATE = "1234-4321";
    final String BAD_REQUEST_CERTIFICATE = "TEST-1234";
    private Authorization authorization;
    private OrderClient orderClient;

    @BeforeClass
    public void beforeClass() throws IOException {
        authorization = new Authorization(provider.getEmail(), provider.getPassword());
        orderClient = new OrderClient(authorization.getToken());
    }

    @Description("[API] Receive info about used certificate")
    @Test
    public void usedCertificateTest() {
        Response response = orderClient.getCertificatesInfo(USED_CERTIFICATE);
        UbsCertificate ubsCertificate = response.as(UbsCertificate.class);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_OK, "Status code not equals");
        softAssert.assertEquals(ubsCertificate.getCertificatePoints(), USED_CERTIFICATE_POINT, "Point of certificate not equals");
        softAssert.assertEquals(ubsCertificate.getCertificateStatus(), USED_CERTIFICATE_STATUS, "Status of certificate not equals");
        softAssert.assertEquals(ubsCertificate.getCertificateDate(), USED_CERTIFICATE_DATE, "Date of certificate not equals");
        softAssert.assertEquals(ubsCertificate.getCode(), USED_CERTIFICATE, "Code of certificate not equals");

        softAssert.assertAll();
    }

    @Description("[API] Receive info about active certificate")
    @Test
    public void activeCertificateTest() {
        Response response = orderClient.getCertificatesInfo(ACTIVE_CERTIFICATE);
        UbsCertificate ubsCertificate = response.as(UbsCertificate.class);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_OK, "Status code not equals");
        softAssert.assertEquals(ubsCertificate.getCertificatePoints(), ACTIVE_CERTIFICATE_POINT, "Point of certificate not equals");
        softAssert.assertEquals(ubsCertificate.getCertificateStatus(), ACTIVE_CERTIFICATE_STATUS, "Status of certificate not equals");
        softAssert.assertEquals(ubsCertificate.getCertificateDate(), ACTIVE_CERTIFICATE_DATE, "Date of certificate not equals");
        softAssert.assertEquals(ubsCertificate.getCode(), ACTIVE_CERTIFICATE, "Code of certificate not equals");

        softAssert.assertAll();
    }

    @Description("[API] Receive info about expired certificate")
    @Test
    public void expiredCertificateTest() {
        Response response = orderClient.getCertificatesInfo(EXPIRED_CERTIFICATE);
        UbsCertificate ubsCertificate = response.as(UbsCertificate.class);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_OK, "Status code not equals");
        softAssert.assertEquals(ubsCertificate.getCertificatePoints(), EXPIRED_CERTIFICATE_POINT, "Point of certificate not equals");
        softAssert.assertEquals(ubsCertificate.getCertificateStatus(), EXPIRED_CERTIFICATE_STATUS, "Status of certificate not equals");
        softAssert.assertEquals(ubsCertificate.getCertificateDate(), EXPIRED_CERTIFICATE_DATE, "Date of certificate not equals");
        softAssert.assertEquals(ubsCertificate.getCode(), EXPIRED_CERTIFICATE, "Code of certificate not equals");

        softAssert.assertAll();
    }

    @Description("[API] Check unauthorized receiving info about certificate")
    @Test
    public void unauthorizedCertificateTest() throws IOException {
        orderClient = new OrderClient();
        Response response = orderClient.getCertificatesInfo(ACTIVE_CERTIFICATE);
        SoftAssert softAssert = new SoftAssert();

        String unauthorizedMessage = response.then().extract().asString();
        softAssert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_UNAUTHORIZED, "Status code not equals");
        softAssert.assertEquals(unauthorizedMessage, "<html><body><h2>Error Page</h2><div>Status code: <b>401</b></div><div>Exception Message: <b>N/A</b></div><body></html>");

        softAssert.assertAll();
    }

    @Description("[API] Check the receipt of information about the incorrect certificate")
    @Test
    public void notFoundCertificateTest() {
        Response response = orderClient.getCertificatesInfo(NOT_FOUND_CERTIFICATE);
        NotFound notFoundCertificate = response.as(NotFound.class);
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_NOT_FOUND, "Status code not equals");
        softAssert.assertEquals(notFoundCertificate.getMessage(), "Certificate does not exist by this code: " + NOT_FOUND_CERTIFICATE);
        softAssert.assertAll();
    }

    @Description("[API] Check the receipt of information with bad request")
    @Test
    public void badRequestCertificateTest() {
        Response response = orderClient.getCertificatesInfo(BAD_REQUEST_CERTIFICATE);
        Assert.assertEquals(response.getStatusCode(), HttpURLConnection.HTTP_BAD_REQUEST, "Status code not equals");

    }
}
