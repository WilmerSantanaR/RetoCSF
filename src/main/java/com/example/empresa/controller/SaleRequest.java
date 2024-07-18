package com.example.empresa.controller;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class SaleRequest {

    private Date saleDate;
    private Long customerId;
    private List<SaleDetailRequest> saleDetails;

    @Getter
    @Setter
    public static class SaleDetailRequest {
        private Long productId;
        private int quantity;
    }
}
