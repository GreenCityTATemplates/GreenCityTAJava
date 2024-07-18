package com.ita.edu.greencity.api.clients.ubs.client;

import com.ita.edu.greencity.api.clients.ubs.BaseClientUBS;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.io.IOException;

public class ClientClient extends BaseClientUBS {

    private final String authToken;

    public ClientClient(String authToken) throws IOException {
        super();
        this.authToken = authToken;
        this.baseApiURL += "/client";

    }

    @Step("get request {this.baseApiURL}/users-pointsToUse ")
    public Response getUserBonuses() {
        return preparedRequest()
                .header("Authorization", String.format("Bearer %s", authToken))
                .log().all()
                .when()
                .get(String.format("%s/users-pointsToUse", baseApiURL));

    }
}
