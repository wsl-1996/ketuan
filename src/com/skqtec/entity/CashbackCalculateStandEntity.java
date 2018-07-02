package com.skqtec.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "CASHBACK_CALCULATE_STAND", schema = "ketuanDB", catalog = "")
public class CashbackCalculateStandEntity {
    private String id;
    private int suitLevel;
    private int suitType;
    private double calculateRate;
    private Timestamp periodDate;
    private double cashbackLimit;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "suit_level")
    public int getSuitLevel() {
        return suitLevel;
    }

    public void setSuitLevel(int suitLevel) {
        this.suitLevel = suitLevel;
    }

    @Basic
    @Column(name = "suit_type")
    public int getSuitType() {
        return suitType;
    }

    public void setSuitType(int suitType) {
        this.suitType = suitType;
    }

    @Basic
    @Column(name = "calculate_rate")
    public double getCalculateRate() {
        return calculateRate;
    }

    public void setCalculateRate(double calculateRate) {
        this.calculateRate = calculateRate;
    }

    @Basic
    @Column(name = "period_date")
    public Timestamp getPeriodDate() {
        return periodDate;
    }

    public void setPeriodDate(Timestamp periodDate) {
        this.periodDate = periodDate;
    }

    @Basic
    @Column(name = "cashback_limit")
    public double getCashbackLimit() {
        return cashbackLimit;
    }

    public void setCashbackLimit(double cashbackLimit) {
        this.cashbackLimit = cashbackLimit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CashbackCalculateStandEntity that = (CashbackCalculateStandEntity) o;

        if (suitLevel != that.suitLevel) return false;
        if (suitType != that.suitType) return false;
        if (Double.compare(that.calculateRate, calculateRate) != 0) return false;
        if (Double.compare(that.cashbackLimit, cashbackLimit) != 0) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (periodDate != null ? !periodDate.equals(that.periodDate) : that.periodDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + suitLevel;
        result = 31 * result + suitType;
        temp = Double.doubleToLongBits(calculateRate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (periodDate != null ? periodDate.hashCode() : 0);
        temp = Double.doubleToLongBits(cashbackLimit);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
