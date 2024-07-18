package com.ita.edu.greencity.api.models.ubs.order.adress;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Coordinates {
    private double latitude;
    private double longitude;

    public Coordinates() {
    }

}
