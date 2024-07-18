package com.ita.edu.greencity.api.models.ubs.client;

import lombok.Data;

import java.util.List;

@Data
public class SuccessSaveOrderAddress {
    private List<SuccessSaveOrderAddress.AddressList> addressList;

    @Data
    static class AddressList {
        private Boolean actual;
        private String addressComment;
        private String city;
        private String cityEn;
        private SuccessSaveOrderAddress.AddressList.Coordinates coordinates;
        private String district;
        private String districtEn;
        private String entranceNumber;
        private String houseCorpus;
        private String houseNumber;
        private Long id;
        private String region;
        private String regionEn;
        private String street;
        private String streetEn;

        @Data
        static class Coordinates {
            private Double latitude;
            private Double longitude;
        }
    }

}
