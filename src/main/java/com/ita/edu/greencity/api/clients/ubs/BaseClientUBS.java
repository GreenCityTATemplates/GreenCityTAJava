package com.ita.edu.greencity.api.clients.ubs;

import com.ita.edu.greencity.api.clients.BaseClient;

import java.io.IOException;

public class BaseClientUBS extends BaseClient {
    public BaseClientUBS() throws IOException {
        super();
        this.baseApiURL = provider.getUbsApiURL();
    }
}
