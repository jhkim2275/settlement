package com.example.settlement.dto;

import com.example.settlement.entity.Refund;
import com.example.settlement.entity.SaleRecord;
import java.util.List;

public class DataWrapper {

    private List<CreatorDTO> creators;
    private List<CourseDTO> courses;
    private List<SaleRecord> saleRecords;
    private List<Refund> refunds;

    public List<CreatorDTO> getCreators() { return creators; }
    public void setCreators(List<CreatorDTO> creators) { this.creators = creators; }

    public List<CourseDTO> getCourses() { return courses; }
    public void setCourses(List<CourseDTO> courses) { this.courses = courses; }

    public List<SaleRecord> getSaleRecords() { return saleRecords; }
    public void setSaleRecords(List<SaleRecord> saleRecords) { this.saleRecords = saleRecords; }

    public List<Refund> getRefunds() { return refunds; }
    public void setRefunds(List<Refund> refunds) { this.refunds = refunds; }
}