package com.skqtec.entity;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT", schema = "ketuanDB", catalog = "")
public class ProductEntity {
    private int id;
    private int ownerType;
    private String productInfo;
    private double price;
    private Integer userId;
    private Integer merchantId;
    private String imagesAddress;
    private Double starLevel;
    private String packStand;
    private String afterSale;
    private String evaluateLabel;
    private double productCost;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public void setStarLevel(double starLevel) {
        this.starLevel = starLevel;
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
    @Column(name = "owner_type")
    public int getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(int ownerType) {
        this.ownerType = ownerType;
    }

    @Basic
    @Column(name = "product_info")
    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "USER_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "MERCHANT_id")
    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    @Basic
    @Column(name = "images_address")
    public String getImagesAddress() {
        return imagesAddress;
    }

    public void setImagesAddress(String imagesAddress) {
        this.imagesAddress = imagesAddress;
    }

    @Basic
    @Column(name = "star_level")
    public Double getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(Double starLevel) {
        this.starLevel = starLevel;
    }

    @Basic
    @Column(name = "pack_stand")
    public String getPackStand() {
        return packStand;
    }

    public void setPackStand(String packStand) {
        this.packStand = packStand;
    }

    @Basic
    @Column(name = "after_sale")
    public String getAfterSale() {
        return afterSale;
    }

    public void setAfterSale(String afterSale) {
        this.afterSale = afterSale;
    }

    @Basic
    @Column(name = "evaluate_label")
    public String getEvaluateLabel() {
        return evaluateLabel;
    }

    public void setEvaluateLabel(String evaluateLabel) {
        this.evaluateLabel = evaluateLabel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity that = (ProductEntity) o;

        if (id != that.id) return false;
        if (ownerType != that.ownerType) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (productInfo != null ? !productInfo.equals(that.productInfo) : that.productInfo != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (merchantId != null ? !merchantId.equals(that.merchantId) : that.merchantId != null) return false;
        if (imagesAddress != null ? !imagesAddress.equals(that.imagesAddress) : that.imagesAddress != null)
            return false;
        if (starLevel != null ? !starLevel.equals(that.starLevel) : that.starLevel != null) return false;
        if (packStand != null ? !packStand.equals(that.packStand) : that.packStand != null) return false;
        if (afterSale != null ? !afterSale.equals(that.afterSale) : that.afterSale != null) return false;
        if (evaluateLabel != null ? !evaluateLabel.equals(that.evaluateLabel) : that.evaluateLabel != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + ownerType;
        result = 31 * result + (productInfo != null ? productInfo.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (merchantId != null ? merchantId.hashCode() : 0);
        result = 31 * result + (imagesAddress != null ? imagesAddress.hashCode() : 0);
        result = 31 * result + (starLevel != null ? starLevel.hashCode() : 0);
        result = 31 * result + (packStand != null ? packStand.hashCode() : 0);
        result = 31 * result + (afterSale != null ? afterSale.hashCode() : 0);
        result = 31 * result + (evaluateLabel != null ? evaluateLabel.hashCode() : 0);
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
