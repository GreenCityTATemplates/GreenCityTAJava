package com.ita.edu.greencity.api.models.ubs.client.order_history;

import lombok.Data;

@Data
public class SuccessOrderHistory {
    private long id;
    private String eventDate;
    private String eventName;
    private String authorName;
}
