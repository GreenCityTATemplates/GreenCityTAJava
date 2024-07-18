package com.ita.edu.greencity.api.models.ubs.order.adress;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class AddressListRoot {
    private ArrayList<AddressList> addressList;

    public AddressListRoot() {
    }
}
