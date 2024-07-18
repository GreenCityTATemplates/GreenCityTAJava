package com.ita.edu.greencity.api.models.ubs.order.process_order;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonalData {
    private String addressComment;
    private String email;
    private String firstName;
    private Long id;
    private String lastName;
    private String phoneNumber;
    private String senderEmail;
    private String senderFirstName;
    private String senderLastName;
    private String senderPhoneNumber;
    private Long ubsUserId;
}
