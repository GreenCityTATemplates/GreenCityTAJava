package com.ita.edu.greencity.api.models.ubs.client;

import lombok.Data;

import java.util.List;

@Data
public class SuccessReqSaveOrderAddress {
    public static ReqUpdateRecipientsData AddressList;
    public List<AddressList> addressList;

    @Data
    public static class AddressList {
        public Boolean actual;
        public String addressComment = "";
        public String city = "Київ";
        public String cityEn = "Kyiv";
        public Coordinates coordinates;
        public String district = "Darnyts'kyi";
        public String districtEn = "Darnyts'kyi";
        public String entranceNumber = "2";
        public String houseCorpus = "2";
        public String houseNumber = "2";
        public Long id = 123L;
        public String region = "Kyiv region";
        public String regionEn = "Kyiv region";
        public String street = "Druzhby Narodiv Boulevard";
        public String streetEn = "Druzhby Narodiv Boulevard";

        @Data
        static class Coordinates {
            public Double latitude;
            public Double longitude;
        }
    }
}
