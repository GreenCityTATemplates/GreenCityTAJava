package com.ita.edu.greencity.api.models.ubs.client;

import lombok.Data;

import java.util.List;

@Data
public class ReqUpdateRecipientsData {
    public List<ReqUpdateRecipientsData> upDataList;

    @Data
    static class UpDataList {
        private String recipientEmail = "setupb1@ukr.net";
        private Integer recipientId = 267;
        private String recipientName = "Yurii";
        private String recipientPhoneNumber = "0676714175";
        private String recipientSurName = "Yurii";
    }

}
