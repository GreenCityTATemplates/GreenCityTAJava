package com.ita.edu.greencity.api.models.ubs.order.process_order;

import lombok.Data;

@Data
public class SuccessfulOrder {
    private String link;
    private Long orderId;
}
