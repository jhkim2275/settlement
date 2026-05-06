package com.example.settlement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter @Setter
public class Creator {

    @Id
    private String id;

    private String name;

    @OneToMany(mappedBy = "creator")
    private List<Course> courses;
}
