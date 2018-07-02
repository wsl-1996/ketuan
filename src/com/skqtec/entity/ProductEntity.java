package com.skqtec.entity;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT", schema = "ketuanDB", catalog = "")
public class ProductEntity {
    private String id;
    private int ownerType;
    private String productInfo;
    private double price;
    private String userId;
    private String merchantId;
    private String imagesAddress;
    private double starLevel;
    private String packStand;
    private String afterSale;
    private String evaluateLabel;
    private double productCost;
    private String productLabel;
    private String productClassifyCode;
    private String productFistImg;
    private String productSlideImg;
    private String productProduceProvince;
    private String productProduceCity;
    private String productProduceAddress;
    private String productName;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
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
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "MERCHANT_id")
    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
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
    public double getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(double starLevel) {
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

    @Basic
    @Column(name = "product_cost")
    public double getProductCost() {
        return productCost;
    }

    public void setProductCost(double productCost) {
        this.productCost = productCost;
    }

    @Basic
    @Column(name = "product_label")
    public String getProductLabel() {
        return productLabel;
    }

    public void setProductLabel(String productLabel) {
        this.productLabel = productLabel;
    }

    @Basic
    @Column(name = "product_classify_code")
    public String getProductClassifyCode() {
        return productClassifyCode;
    }

    public void setProductClassifyCode(String productClassifyCode) {
        this.productClassifyCode = productClassifyCode;
    }

    @Basic
    @Column(name = "product_fist_img")
    public String getProductFistImg() {
        return productFistImg;
    }

    public void setProductFistImg(String productFistImg) {
        this.productFistImg = productFistImg;
    }

    @Basic
    @Column(name = "product_slide_img")
    public String getProductSlideImg() {
        return productSlideImg;
    }

    public void setProductSlideImg(String productSlideImg) {
        this.productSlideImg = productSlideImg;
    }

    @Basic
    @Column(name = "product_produce_province")
    public String getProductProduceProvince() {
        return productProduceProvince;
    }

    public void setProductProduceProvince(String productProduceProvince) {
        this.productProduceProvince = productProduceProvince;
    }

    @Basic
    @Column(name = "product_produce_city")
    public String getProductProduceCity() {
        return productProduceCity;
    }

    public void setProductProduceCity(String productProduceCity) {
        this.productProduceCity = productProduceCity;
    }

    @Basic
    @Column(name = "product_produce_address")
    public String getProductProduceAddress() {
        return productProduceAddress;
    }

    public void setProductProduceAddress(String productProduceAddress) {
        this.productProduceAddress = productProduceAddress;
    }

    @Basic
    @Column(name = "product_name")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity that = (ProductEntity) o;

        if (ownerType != that.ownerType) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (Double.compare(that.starLevel, starLevel) != 0) return false;
        if (Double.compare(that.productCost, productCost) != 0) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (productInfo != null ? !productInfo.equals(that.productInfo) : that.productInfo != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (merchantId != null ? !merchantId.equals(that.merchantId) : that.merchantId != null) return false;
        if (imagesAddress != null ? !imagesAddress.equals(that.imagesAddress) : that.imagesAddress != null)
            return false;
        if (packStand != null ? !packStand.equals(that.packStand) : that.packStand != null) return false;
        if (afterSale != null ? !afterSale.equals(that.afterSale) : that.afterSale != null) return false;
        if (evaluateLabel != null ? !evaluateLabel.equals(that.evaluateLabel) : that.evaluateLabel != null)
            return false;
        if (productLabel != null ? !productLabel.equals(that.productLabel) : that.productLabel != null) return false;
        if (productClassifyCode != null ? !productClassifyCode.equals(that.productClassifyCode) : that.productClassifyCode != null)
            return false;
        if (productFistImg != null ? !productFistImg.equals(that.productFistImg) : that.productFistImg != null)
            return false;
        if (productSlideImg != null ? !productSlideImg.equals(that.productSlideImg) : that.productSlideImg != null)
            return false;
        if (productProduceProvince != null ? !productProduceProvince.equals(that.productProduceProvince) : that.productProduceProvince != null)
            return false;
        if (productProduceCity != null ? !productProduceCity.equals(that.productProduceCity) : that.productProduceCity != null)
            return false;
        if (productProduceAddress != null ? !productProduceAddress.equals(that.productProduceAddress) : that.productProduceAddress != null)
            return false;
        if (productName != null ? !productName.equals(that.productName) : that.productName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + ownerType;
        result = 31 * result + (productInfo != null ? productInfo.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (merchantId != null ? merchantId.hashCode() : 0);
        result = 31 * result + (imagesAddress != null ? imagesAddress.hashCode() : 0);
        temp = Double.doubleToLongBits(starLevel);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (packStand != null ? packStand.hashCode() : 0);
        result = 31 * result + (afterSale != null ? afterSale.hashCode() : 0);
        result = 31 * result + (evaluateLabel != null ? evaluateLabel.hashCode() : 0);
        temp = Double.doubleToLongBits(productCost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (productLabel != null ? productLabel.hashCode() : 0);
        result = 31 * result + (productClassifyCode != null ? productClassifyCode.hashCode() : 0);
        result = 31 * result + (productFistImg != null ? productFistImg.hashCode() : 0);
        result = 31 * result + (productSlideImg != null ? productSlideImg.hashCode() : 0);
        result = 31 * result + (productProduceProvince != null ? productProduceProvince.hashCode() : 0);
        result = 31 * result + (productProduceCity != null ? productProduceCity.hashCode() : 0);
        result = 31 * result + (productProduceAddress != null ? productProduceAddress.hashCode() : 0);
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        return result;
    }
}
