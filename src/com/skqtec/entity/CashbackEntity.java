package com.skqtec.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "CASHBACK", schema = "ketuanDB", catalog = "")
public class CashbackEntity {
    private int id;
    private double sum;
    private int orderId;
    private int cashbackUserId;
    private int consumeUserId;
    private Timestamp date;
    private int paybacked;
    private int cashbackCalculateStandId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "sum")
    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    @Basic
    @Column(name = "ORDER_id")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "cashback_USER_id")
    public int getCashbackUserId() {
        return cashbackUserId;
    }

    public void setCashbackUserId(int cashbackUserId) {
        this.cashbackUserId = cashbackUserId;
    }

    @Basic
    @Column(name = "consume_USER_id")
    public int getConsumeUserId() {
        return consumeUserId;
    }

    public void setConsumeUserId(int consumeUserId) {
        this.consumeUserId = consumeUserId;
    }

    @Basic
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "paybacked")
    public int getPaybacked() {
        return paybacked;
    }

    public void setPaybacked(int paybacked) {
        this.paybacked = paybacked;
    }

    @Basic
    @Column(name = "CASHBACK_CALCULATE_STAND_id")
    public int getCashbackCalculateStandId() {
        return cashbackCalculateStandId;
    }

    public void setCashbackCalculateStandId(int cashbackCalculateStandId) {
        this.cashbackCalculateStandId = cashbackCalculateStandId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CashbackEntity that = (CashbackEntity) o;

        if (id != that.id) return false;
        if (Double.compare(that.sum, sum) != 0) return false;
        if (orderId != that.orderId) return false;
        if (cashbackUserId != that.cashbackUserId) return false;
        if (consumeUserId != that.consumeUserId) return false;
        if (paybacked != that.paybacked) return false;
        if (cashbackCalculateStandId != that.cashbackCalculateStandId) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(sum);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + orderId;
        result = 31 * result + cashbackUserId;
        result = 31 * result + consumeUserId;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + paybacked;
        result = 31 * result + cashbackCalculateStandId;
        return result;
    }
}
