package com.example.settlement.controller;

import com.example.settlement.entity.SaleRecord;
import com.example.settlement.repository.SaleRecordRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleRecordController {

    private final SaleRecordRepository saleRecordRepository;
    public SaleRecordController(SaleRecordRepository saleRecordRepository) {
        this.saleRecordRepository = saleRecordRepository;
    }

    @PostMapping
    public SaleRecord createSale(@RequestBody SaleRecord saleRecord) {
        return saleRecordRepository.save(saleRecord);
    }
    @GetMapping
    public List<SaleRecord> getAllSales() {
        return saleRecordRepository.findAll();
    }
}