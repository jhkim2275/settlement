package com.example.settlement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Getter @Setter
public class SaleRecord {

    @Id
    private String id;

    private String studentId;
    private int amount;
    private OffsetDateTime paidAt;

    @Transient
    private String courseId;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "saleRecord", cascade = CascadeType.ALL)
    private List<Refund> refunds = new ArrayList<>();
}