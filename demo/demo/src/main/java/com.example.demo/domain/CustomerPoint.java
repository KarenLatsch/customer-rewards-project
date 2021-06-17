package com.example.demo.domain;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;
// Customer Points 
public class CustomerPoint {

    private Long customerId;
    private Long yearMonth;
    private Long points;

    public CustomerPoint(Long customerId, Long yearMonth, Long points) {
        this.customerId = customerId;
        this.yearMonth = yearMonth;
        this.points = points;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getYearMonth() { return yearMonth; }

    public void setYearMonth(Long yearMonth) { this.yearMonth = yearMonth; }

    public Long getPoints() { return points; }

    public void setPoints(Long points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerPoint that = (CustomerPoint) o;
        return Objects.equals(customerId, that.customerId)
                && Objects.equals(yearMonth, that.yearMonth);
    }
}
