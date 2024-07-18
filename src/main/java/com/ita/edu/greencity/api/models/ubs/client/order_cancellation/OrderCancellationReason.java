package com.ita.edu.greencity.api.models.ubs.client.order_cancellation;

import lombok.Data;

@Data
public class OrderCancellationReason {
    private String cancellationComment;
    private CancellationReason cancellationReason;

    public OrderCancellationReason() {
    }

    public OrderCancellationReason(String cancellationComment, CancellationReason cancellationReason) {
        this.cancellationComment = cancellationComment;
        this.cancellationReason = cancellationReason;
    }
}

