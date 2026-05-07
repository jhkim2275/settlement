package com.example.settlement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SettlementResponse {

    private int totalSale;
    private int totalRefund;
    private int net;
    private int fee;
    private int settlement;
}
