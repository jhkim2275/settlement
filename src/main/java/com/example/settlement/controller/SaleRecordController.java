package com.example.settlement.controller;

import com.example.settlement.entity.SaleRecord;
import com.example.settlement.repository.SaleRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sales")
public class SaleRecordController {

    private final SaleRecordRepository repo;

    @PostMapping
    public SaleRecord create(@RequestBody SaleRecord record) {
        record.setCreatedAt(LocalDateTime.now());
        return repo.save(record);
    }
}