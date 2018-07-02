package com.skqtec.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "CASHBACK", schema = "ketuanDB", catalog = "")
public class CashbackEntity {
    private String id;
    private double sum;
    private String orderId;
    private String cashbackUserId;
    private String consumeUserId;
    private Timestamp date;
    private int paybacked;
    private String cashbackCalculateStandId;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
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
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "cashback_USER_id")
    public String getCashbackUserId() {
        return cashbackUserId;
    }

    public void setCashbackUserId(String cashbackUserId) {
        this.cashbackUserId = cashbackUserId;
    }

    @Basic
    @Column(name = "consume_USER_id")
    public String getConsumeUserId() {
        return consumeUserId;
    }

    public void setConsumeUserId(String consumeUserId) {
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
    public String getCashbackCalculateStandId() {
        return cashbackCalculateStandId;
    }

    public void setCashbackCalculateStandId(String cashbackCalculateStandId) {
        this.cashbackCalculateStandId = cashbackCalculateStandId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CashbackEntity that = (CashbackEntity) o;

        if (Double.compare(that.sum, sum) != 0) return false;
        if (paybacked != that.paybacked) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        if (cashbackUserId != null ? !cashbackUserId.equals(that.cashbackUserId) : that.cashbackUserId != null)
            return false;
        if (consumeUserId != null ? !consumeUserId.equals(that.consumeUserId) : that.consumeUserId != null)
            return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (cashbackCalculateStandId != null ? !cashbackCalculateStandId.equals(that.cashbackCalculateStandId) : that.cashbackCalculateStandId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        temp = Double.doubleToLongBits(sum);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        result = 31 * result + (cashbackUserId != null ? cashbackUserId.hashCode() : 0);
        result = 31 * result + (consumeUserId != null ? consumeUserId.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + paybacked;
        result = 31 * result + (cashbackCalculateStandId != null ? cashbackCalculateStandId.hashCode() : 0);
        return result;
    }
}
