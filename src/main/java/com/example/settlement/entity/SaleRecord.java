package com.example.settlement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Getter @Setter
public class SaleRecord {

    @Id
    @GeneratedValue
    private Long id;

    private Long creatorId;
    private Long classId;
    private Long userId;

    private int amount;

    @Enumerated(EnumType.STRING)
    private Type type;

    private LocalDateTime createdAt;

    private Long originalSaleId;
}