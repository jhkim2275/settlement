package com.example.settlement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
public class Refund {

    @Id
    private String id;

    private int amount;

    private OffsetDateTime refundedAt;

    private String saleId;

    @ManyToOne
    @JoinColumn(name = "sale_record_id")
    private SaleRecord saleRecord;
}
