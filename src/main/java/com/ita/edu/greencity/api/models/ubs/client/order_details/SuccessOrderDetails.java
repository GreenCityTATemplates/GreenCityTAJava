package com.ita.edu.greencity.api.models.ubs.client.order_details;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SuccessOrderDetails {
    private List<Bag> bags = new ArrayList<>();
    private long points;
}

@Data
class Bag {
    private long id;
    private String name;
    private long capacity;
    private long price;
    private String nameEng;
    private long locationId;

    Bag() {
    }
}
