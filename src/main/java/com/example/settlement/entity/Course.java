package com.example.settlement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Course {
    @Id
    private String id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Creator creator;
}
