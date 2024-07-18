package com.ita.edu.greencity.api.clients.user.sign_in;

import com.ita.edu.greencity.api.models.user.SuccessSignIn;
import com.ita.edu.greencity.api.models.user.UserCredentials;

import java.io.IOException;

public class Authorization {
    private final SuccessSignIn successSignIn;


    public Authorization(String email, String password) throws IOException {
        UserCredentials credentials = new UserCredentials(email, password);
        SignInClient client = new SignInClient();
        this.successSignIn = client
                .successSignInRequest(credentials)
                .then()
                .log()
                .all()
                .extract()
                .as(SuccessSignIn.class);
    }


    public String getToken() {
        return successSignIn.getAccessToken();
    }
}
