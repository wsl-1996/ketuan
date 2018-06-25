package com.skqtec.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "GROUP", schema = "ketuanDB", catalog = "")
@IdClass(GroupEntityPK.class)
public class GroupEntity {
    private int id;
    private Integer userId;
    private int productId;
    private int groupType;
    private Integer groupCount;
    private int offeredCount;
    private double groupPrice;
    private String offeredUserId;
    private int groupState;
    private Timestamp endTime;
    private String customerService;
    private String groupDiscription;
    private double productCost;

    public void setGroupType(int groupType) {
        this.groupType = groupType;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setGroupCount(int groupCount) {
        this.groupCount = groupCount;
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
    @Column(name = "USER_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "PRODUCT_id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "group_type")
    public int getGroupType() {
        return groupType;
    }

    public void setGroupType(byte groupType) {
        this.groupType = groupType;
    }

    @Basic
    @Column(name = "group_count")
    public Integer getGroupCount() {
        return groupCount;
    }

    public void setGroupCount(Integer groupCount) {
        this.groupCount = groupCount;
    }

    @Basic
    @Column(name = "offered_count")
    public int getOfferedCount() {
        return offeredCount;
    }

    public void setOfferedCount(int offeredCount) {
        this.offeredCount = offeredCount;
    }

    @Basic
    @Column(name = "group_price")
    public double getGroupPrice() {
        return groupPrice;
    }

    public void setGroupPrice(double groupPrice) {
        this.groupPrice = groupPrice;
    }

    @Basic
    @Column(name = "offered_user_id")
    public String getOfferedUserId() {
        return offeredUserId;
    }

    public void setOfferedUserId(String offeredUserId) {
        this.offeredUserId = offeredUserId;
    }

    @Basic
    @Column(name = "group_state")
    public int getGroupState() {
        return groupState;
    }

    public void setGroupState(int groupState) {
        this.groupState = groupState;
    }

    @Basic
    @Column(name = "end_time")
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "customer_service")
    public String getCustomerService() {
        return customerService;
    }

    public void setCustomerService(String customerService) {
        this.customerService = customerService;
    }

    @Basic
    @Column(name = "group_discription")
    public String getGroupDiscription() {
        return groupDiscription;
    }

    public void setGroupDiscription(String groupDiscription) {
        this.groupDiscription = groupDiscription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupEntity that = (GroupEntity) o;

        if (id != that.id) return false;
        if (productId != that.productId) return false;
        if (groupType != that.groupType) return false;
        if (offeredCount != that.offeredCount) return false;
        if (Double.compare(that.groupPrice, groupPrice) != 0) return false;
        if (groupState != that.groupState) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (groupCount != null ? !groupCount.equals(that.groupCount) : that.groupCount != null) return false;
        if (offeredUserId != null ? !offeredUserId.equals(that.offeredUserId) : that.offeredUserId != null)
            return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (customerService != null ? !customerService.equals(that.customerService) : that.customerService != null)
            return false;
        if (groupDiscription != null ? !groupDiscription.equals(that.groupDiscription) : that.groupDiscription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + productId;
        result = 31 * result + (int) groupType;
        result = 31 * result + (groupCount != null ? groupCount.hashCode() : 0);
        result = 31 * result + offeredCount;
        temp = Double.doubleToLongBits(groupPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (offeredUserId != null ? offeredUserId.hashCode() : 0);
        result = 31 * result + groupState;
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (customerService != null ? customerService.hashCode() : 0);
        result = 31 * result + (groupDiscription != null ? groupDiscription.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "product_cost")
    public double getProductCost() {
        return productCost;
    }

    public void setProductCost(double productCost) {
        this.productCost = productCost;
    }
}
