package com.ita.edu.greencity.api.models.ubs.order.get_courier_locations;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class LocationInfoDto {
    private ArrayList<LocationsDto> locationsDto;
    private int regionId;
    private ArrayList<RegionTranslationDto> regionTranslationDtos;

    public LocationInfoDto() {
    }
}
