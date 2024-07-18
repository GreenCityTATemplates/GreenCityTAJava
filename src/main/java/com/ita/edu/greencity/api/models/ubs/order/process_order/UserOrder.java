package com.ita.edu.greencity.api.models.ubs.order.process_order;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserOrder {
    private List<String> additionalOrders;
    private Long addressId;
    private List<Bag> bags;
    private List<String> certificates;
    private Long locationId;
    private String orderComment;
    private PersonalData personalData;
    private Long pointsToUse;
    private Boolean shouldBePaid;


}
