package com.example.settlement.dto;

import com.example.settlement.entity.SaleRecord;
import java.util.List;

public class DataWrapper {

    private List<CreatorDTO> creators;
    private List<CourseDTO> courses;
    private List<SaleRecord> saleRecords;

    public List<CreatorDTO> getCreators() { return creators; }
    public void setCreators(List<CreatorDTO> creators) { this.creators = creators; }

    public List<CourseDTO> getCourses() { return courses; }
    public void setCourses(List<CourseDTO> courses) { this.courses = courses; }

    public List<SaleRecord> getSaleRecords() { return saleRecords; }
    public void setSaleRecords(List<SaleRecord> saleRecords) { this.saleRecords = saleRecords; }
}