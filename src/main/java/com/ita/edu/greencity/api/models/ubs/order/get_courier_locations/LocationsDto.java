package com.ita.edu.greencity.api.models.ubs.order.get_courier_locations;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LocationsDto {
    private int latitude;
    private int locationId;
    private String locationStatus;
    private List<LocationTranslationDtoList> locationTranslationDtoList;
    private int longitude;

    public LocationsDto() {
    }
}
