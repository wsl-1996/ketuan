package com.skqtec.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ORDER", schema = "ketuanDB", catalog = "")
public class OrderEntity {
    private int id;
    private int productId;
    private Integer groupId;
    private int userId;
    private int state;
    private String sendName;
    private String sendAddress;
    private String sendZip;
    private String sendTel;
    private Timestamp orderTime;
    private String paymethod;
    private String meno;   //备忘
    private double totalPrice;
    private String trackCode;
    private int trackId;

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PRODUCT_id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "GROUP_id")
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "USER_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "state")
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Basic
    @Column(name = "send_name")
    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    @Basic
    @Column(name = "send_address")
    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress;
    }

    @Basic
    @Column(name = "send_zip")
    public String getSendZip() {
        return sendZip;
    }

    public void setSendZip(String sendZip) {
        this.sendZip = sendZip;
    }

    @Basic
    @Column(name = "send_tel")
    public String getSendTel() {
        return sendTel;
    }

    public void setSendTel(String sendTel) {
        this.sendTel = sendTel;
    }

    @Basic
    @Column(name = "order_time")
    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    @Basic
    @Column(name = "paymethod")
    public String getPaymethod() {
        return paymethod;
    }

    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }

    @Basic
    @Column(name = "meno")
    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    @Basic
    @Column(name = "total_price")
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Basic
    @Column(name = "track_code")
    public String getTrackCode() {
        return trackCode;
    }

    public void setTrackCode(String trackCode) {
        this.trackCode = trackCode;
    }

    @Basic
    @Column(name = "track_id")
    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (id != that.id) return false;
        if (productId != that.productId) return false;
        if (userId != that.userId) return false;
        if (state != that.state) return false;
        if (Double.compare(that.totalPrice, totalPrice) != 0) return false;
        if (trackId != that.trackId) return false;
        if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) return false;
        if (sendName != null ? !sendName.equals(that.sendName) : that.sendName != null) return false;
        if (sendAddress != null ? !sendAddress.equals(that.sendAddress) : that.sendAddress != null) return false;
        if (sendZip != null ? !sendZip.equals(that.sendZip) : that.sendZip != null) return false;
        if (sendTel != null ? !sendTel.equals(that.sendTel) : that.sendTel != null) return false;
        if (orderTime != null ? !orderTime.equals(that.orderTime) : that.orderTime != null) return false;
        if (paymethod != null ? !paymethod.equals(that.paymethod) : that.paymethod != null) return false;
        if (meno != null ? !meno.equals(that.meno) : that.meno != null) return false;
        if (trackCode != null ? !trackCode.equals(that.trackCode) : that.trackCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + productId;
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
        result = 31 * result + userId;
        result = 31 * result + state;
        result = 31 * result + (sendName != null ? sendName.hashCode() : 0);
        result = 31 * result + (sendAddress != null ? sendAddress.hashCode() : 0);
        result = 31 * result + (sendZip != null ? sendZip.hashCode() : 0);
        result = 31 * result + (sendTel != null ? sendTel.hashCode() : 0);
        result = 31 * result + (orderTime != null ? orderTime.hashCode() : 0);
        result = 31 * result + (paymethod != null ? paymethod.hashCode() : 0);
        result = 31 * result + (meno != null ? meno.hashCode() : 0);
        temp = Double.doubleToLongBits(totalPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (trackCode != null ? trackCode.hashCode() : 0);
        result = 31 * result + trackId;
        return result;
    }
}
