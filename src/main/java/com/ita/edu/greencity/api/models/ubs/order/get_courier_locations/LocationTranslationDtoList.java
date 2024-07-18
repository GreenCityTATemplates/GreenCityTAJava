package com.ita.edu.greencity.api.models.ubs.order.get_courier_locations;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationTranslationDtoList {
    private String languageCode;
    private String locationName;

    public LocationTranslationDtoList() {
    }
}
