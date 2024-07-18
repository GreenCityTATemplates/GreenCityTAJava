package com.ita.edu.greencity.api.models.ubs.order.get_courier_locations;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CourierDto {
    private int courierId;
    private String courierStatus;
    private List<CourierTranslationDto> courierTranslationDtos;
    private String createDate;
    private String createdBy;

    public CourierDto() {
    }
}
