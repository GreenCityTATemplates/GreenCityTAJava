package com.ita.edu.greencity.api.models.ubs.order.get_courier_locations;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class CourierLocationsRoot {
    private ArrayList<CourierDto> courierDtos;
    private String courierLimit;
    private int courierLocationId;
    private List<LocationInfoDto> locationInfoDtos;
    private int maxAmountOfBigBags;
    private int maxPriceOfOrder;
    private int minAmountOfBigBags;
    private int minPriceOfOrder;

    public CourierLocationsRoot() {
    }
}
