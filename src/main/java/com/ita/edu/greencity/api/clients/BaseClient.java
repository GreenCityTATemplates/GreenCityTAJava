package com.ita.edu.greencity.api.clients;

import com.ita.edu.greencity.utils.ValueProvider;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public abstract class BaseClient {
    protected static ValueProvider provider;
    protected String baseApiURL;
    protected ContentType contentType;

    public BaseClient() throws IOException {
        if (provider == null) {
            provider = new ValueProvider();
        }
        contentType = ContentType.JSON;
    }

    protected RequestSpecification preparedRequest() {

        return given()
                .baseUri(baseApiURL)
                .contentType(contentType)
                .accept(contentType);
    }
}
