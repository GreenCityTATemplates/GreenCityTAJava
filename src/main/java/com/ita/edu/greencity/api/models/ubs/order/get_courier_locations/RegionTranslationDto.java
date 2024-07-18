package com.ita.edu.greencity.api.models.ubs.order.get_courier_locations;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegionTranslationDto {
    private String languageCode;
    private String regionName;

    public RegionTranslationDto() {
    }
}
