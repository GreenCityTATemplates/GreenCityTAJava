package com.ita.edu.greencity.api.models.ubs.client;


import lombok.Data;

@Data
public class UbsCertificate {
    private String certificateDate;
    private Long certificatePoints;
    private String certificateStatus;
    private String code;
}
