package com.ll.ticket.domain.order.dto;

import lombok.Data;

@Data
public class OrderPayInfoDto {
    private String customerName;
    private String customerMobilePhoneNumber;
    private String zipcode;
    private String address1;
    private String address2;
    private String recaptcha;
}
