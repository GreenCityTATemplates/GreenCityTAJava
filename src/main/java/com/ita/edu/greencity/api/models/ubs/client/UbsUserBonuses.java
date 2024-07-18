package com.ita.edu.greencity.api.models.ubs.client;

import lombok.Data;

@Data
public class UbsUserBonuses {
    private long amount;
    private String dateOfEnrollment;
    private long numberOfOrder;
}
