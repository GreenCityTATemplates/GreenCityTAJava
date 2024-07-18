package com.ita.edu.greencity.api.models.user;

import lombok.Data;

@Data
public class SuccessSignIn {
    private String accessToken;
    private String name;
    private Boolean ownRegistrations;
    private String refreshToken;
    private Long userId;
}
