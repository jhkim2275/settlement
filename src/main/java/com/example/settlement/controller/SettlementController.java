package com.example.settlement.controller;

import com.example.settlement.service.SettlementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.settlement.dto.SettlementResponse;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/creators/{creatorId}/settlements")
public class SettlementController {

    private final SettlementService service;

    @GetMapping
    public SettlementResponse getMonthly(
            @PathVariable String creatorId,
            @RequestParam int year,
            @RequestParam int month) {

        return service.getMonthlySettlement(creatorId, year, month);
    }
}
