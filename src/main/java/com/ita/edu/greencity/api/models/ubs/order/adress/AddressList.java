package com.ita.edu.greencity.api.models.ubs.order.adress;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressList {
    private boolean actual;
    private String addressComment;
    private String city;
    private String cityEn;
    private Coordinates coordinates;
    private String district;
    private String districtEn;
    private String entranceNumber;
    private String houseCorpus;
    private String houseNumber;
    private int id;
    private String region;
    private String regionEn;
    private String street;
    private String streetEn;

    public AddressList() {
    }
}
