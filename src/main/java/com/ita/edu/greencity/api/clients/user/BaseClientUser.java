package com.ita.edu.greencity.api.clients.user;

import com.ita.edu.greencity.api.clients.BaseClient;

import java.io.IOException;

public class BaseClientUser extends BaseClient {
    public BaseClientUser() throws IOException {
        super();
        this.baseApiURL = provider.getUserApiURL();
    }
}
