package com.skqtec.entity;

import javax.persistence.*;

@Entity
@Table(name = "EXPRESSAGE", schema = "ketuanDB_test", catalog = "")
public class ExpressageEntity {
    private String id;
    private String expressageName;
    private String addressOfSevvice;
    private Integer expressagePrice;
    private String productId;
    private Integer isNew;
    private String shipAddress;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "expressage_name")
    public String getExpressageName() {
        return expressageName;
    }

    public void setExpressageName(String expressageName) {
        this.expressageName = expressageName;
    }

    @Basic
    @Column(name = "address_of_sevvice")
    public String getAddressOfSevvice() {
        return addressOfSevvice;
    }

    public void setAddressOfSevvice(String addressOfSevvice) {
        this.addressOfSevvice = addressOfSevvice;
    }

    @Basic
    @Column(name = "expressage_price")
    public Integer getExpressagePrice() {
        return expressagePrice;
    }

    public void setExpressagePrice(Integer expressagePrice) {
        this.expressagePrice = expressagePrice;
    }

    @Basic
    @Column(name = "product_id")
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "is_new")
    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    @Basic
    @Column(name = "ship_address")
    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExpressageEntity that = (ExpressageEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (expressageName != null ? !expressageName.equals(that.expressageName) : that.expressageName != null)
            return false;
        if (addressOfSevvice != null ? !addressOfSevvice.equals(that.addressOfSevvice) : that.addressOfSevvice != null)
            return false;
        if (expressagePrice != null ? !expressagePrice.equals(that.expressagePrice) : that.expressagePrice != null)
            return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        if (isNew != null ? !isNew.equals(that.isNew) : that.isNew != null) return false;
        if (shipAddress != null ? !shipAddress.equals(that.shipAddress) : that.shipAddress != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (expressageName != null ? expressageName.hashCode() : 0);
        result = 31 * result + (addressOfSevvice != null ? addressOfSevvice.hashCode() : 0);
        result = 31 * result + (expressagePrice != null ? expressagePrice.hashCode() : 0);
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (isNew != null ? isNew.hashCode() : 0);
        result = 31 * result + (shipAddress != null ? shipAddress.hashCode() : 0);
        return result;
    }
}
