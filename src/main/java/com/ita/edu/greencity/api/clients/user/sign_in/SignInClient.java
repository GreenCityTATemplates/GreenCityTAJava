package com.ita.edu.greencity.api.clients.user.sign_in;

import com.ita.edu.greencity.api.clients.user.BaseClientUser;
import com.ita.edu.greencity.api.models.user.UserCredentials;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.io.IOException;

public class SignInClient extends BaseClientUser {

    public SignInClient() throws IOException {
        super();
        this.baseApiURL = provider.getUserApiURL();
    }

    @Step("get request {this.baseApiURL}ownSecurity/signIn credentials: {credentials}")
    public Response successSignInRequest(UserCredentials credentials) {

        return preparedRequest()
                .body(credentials)
                .when()
                .post(baseApiURL + "/ownSecurity/signIn");
    }
}
