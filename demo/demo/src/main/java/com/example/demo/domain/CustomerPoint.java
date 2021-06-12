package com.example.demo.domain;

import java.time.LocalDate;
import java.util.Objects;

public class CustomerPoint {
    private Long customerId;
    private LocalDate pointPeriod;
    private Long points;

    public LocalDate getPointPeriod() {
        return pointPeriod;
    }

    public void setPointPeriod(LocalDate pointPeriod) {
        this.pointPeriod = pointPeriod;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerPoint that = (CustomerPoint) o;
        return Objects.equals(customerId, that.customerId)
                && Objects.equals(pointPeriod, that.pointPeriod);
    }

}
