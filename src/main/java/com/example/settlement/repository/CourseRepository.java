package com.example.settlement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.settlement.entity.Course;

public interface CourseRepository extends JpaRepository<Course, String> {
}
