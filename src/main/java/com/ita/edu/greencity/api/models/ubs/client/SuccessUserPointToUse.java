package com.ita.edu.greencity.api.models.ubs.client;

import lombok.Data;

import java.util.List;

@Data
public class SuccessUserPointToUse {
    private long userBonuses;
    private List<UbsUserBonuses> ubsUserBonuses;
}
