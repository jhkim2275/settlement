package com.example.settlement.controller;

import com.example.settlement.service.SettlementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/settlements")
public class SettlementController {

    private final SettlementService service;

    @GetMapping("/monthly")
    public Map<String, Object> getMonthly(
            @RequestParam Long creatorId,
            @RequestParam int year,
            @RequestParam int month) {

        return service.getMonthlySettlement(creatorId, year, month);
    }
}
