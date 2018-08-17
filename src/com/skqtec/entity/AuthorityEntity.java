package com.skqtec.entity;

import javax.persistence.*;

@Entity
@Table(name = "AUTHORITY", schema = "ketuanDB_test", catalog = "")
public class AuthorityEntity {
    private String authorityId;
    private String authorityName;
    private Integer addProduct;
    private Integer delProduct;
    private Integer searchProduct;
    private Integer updateProduct;
    private Integer publishProduct;
    private Integer offlineProduct;
    private Integer searchUser;
    private Integer updateUser;
    private Integer addUser;
    private Integer deteleUser;
    private Integer upgradeUser;
    private Integer downgradeUser;
    private Integer noticeUser;
    private Integer searchGroup;
    private Integer updateGroup;
    private Integer addGroup;
    private Integer deteleGroup;
    private Integer publishGroup;
    private Integer onlineGroup;
    private Integer offlineGroup;
    private Integer searchOrder;
    private Integer updateOrder;
    private Integer addOrder;
    private Integer deteleOrder;
    private Integer searchPromotionStyle;
    private Integer updatePromotionStyle;
    private Integer addPromotionStyle;
    private Integer detelePromotionStyle;

    @Id
    @Column(name = "authority_id")
    public String getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(String authorityId) {
        this.authorityId = authorityId;
    }

    @Basic
    @Column(name = "authority_name")
    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    @Basic
    @Column(name = "add_product")
    public Integer getAddProduct() {
        return addProduct;
    }

    public void setAddProduct(Integer addProduct) {
        this.addProduct = addProduct;
    }

    @Basic
    @Column(name = "del_product")
    public Integer getDelProduct() {
        return delProduct;
    }

    public void setDelProduct(Integer delProduct) {
        this.delProduct = delProduct;
    }

    @Basic
    @Column(name = "search_product")
    public Integer getSearchProduct() {
        return searchProduct;
    }

    public void setSearchProduct(Integer searchProduct) {
        this.searchProduct = searchProduct;
    }

    @Basic
    @Column(name = "update_product")
    public Integer getUpdateProduct() {
        return updateProduct;
    }

    public void setUpdateProduct(Integer updateProduct) {
        this.updateProduct = updateProduct;
    }

    @Basic
    @Column(name = "publish_product")
    public Integer getPublishProduct() {
        return publishProduct;
    }

    public void setPublishProduct(Integer publishProduct) {
        this.publishProduct = publishProduct;
    }

    @Basic
    @Column(name = "offline_product")
    public Integer getOfflineProduct() {
        return offlineProduct;
    }

    public void setOfflineProduct(Integer offlineProduct) {
        this.offlineProduct = offlineProduct;
    }

    @Basic
    @Column(name = "search_user")
    public Integer getSearchUser() {
        return searchUser;
    }

    public void setSearchUser(Integer searchUser) {
        this.searchUser = searchUser;
    }

    @Basic
    @Column(name = "update_user")
    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    @Basic
    @Column(name = "add_user")
    public Integer getAddUser() {
        return addUser;
    }

    public void setAddUser(Integer addUser) {
        this.addUser = addUser;
    }

    @Basic
    @Column(name = "detele_user")
    public Integer getDeteleUser() {
        return deteleUser;
    }

    public void setDeteleUser(Integer deteleUser) {
        this.deteleUser = deteleUser;
    }

    @Basic
    @Column(name = "upgrade_user")
    public Integer getUpgradeUser() {
        return upgradeUser;
    }

    public void setUpgradeUser(Integer upgradeUser) {
        this.upgradeUser = upgradeUser;
    }

    @Basic
    @Column(name = "downgrade_user")
    public Integer getDowngradeUser() {
        return downgradeUser;
    }

    public void setDowngradeUser(Integer downgradeUser) {
        this.downgradeUser = downgradeUser;
    }

    @Basic
    @Column(name = "notice_user")
    public Integer getNoticeUser() {
        return noticeUser;
    }

    public void setNoticeUser(Integer noticeUser) {
        this.noticeUser = noticeUser;
    }

    @Basic
    @Column(name = "search_group")
    public Integer getSearchGroup() {
        return searchGroup;
    }

    public void setSearchGroup(Integer searchGroup) {
        this.searchGroup = searchGroup;
    }

    @Basic
    @Column(name = "update_group")
    public Integer getUpdateGroup() {
        return updateGroup;
    }

    public void setUpdateGroup(Integer updateGroup) {
        this.updateGroup = updateGroup;
    }

    @Basic
    @Column(name = "add_group")
    public Integer getAddGroup() {
        return addGroup;
    }

    public void setAddGroup(Integer addGroup) {
        this.addGroup = addGroup;
    }

    @Basic
    @Column(name = "detele_group")
    public Integer getDeteleGroup() {
        return deteleGroup;
    }

    public void setDeteleGroup(Integer deteleGroup) {
        this.deteleGroup = deteleGroup;
    }

    @Basic
    @Column(name = "publish_group")
    public Integer getPublishGroup() {
        return publishGroup;
    }

    public void setPublishGroup(Integer publishGroup) {
        this.publishGroup = publishGroup;
    }

    @Basic
    @Column(name = "online_group")
    public Integer getOnlineGroup() {
        return onlineGroup;
    }

    public void setOnlineGroup(Integer onlineGroup) {
        this.onlineGroup = onlineGroup;
    }

    @Basic
    @Column(name = "offline_group")
    public Integer getOfflineGroup() {
        return offlineGroup;
    }

    public void setOfflineGroup(Integer offlineGroup) {
        this.offlineGroup = offlineGroup;
    }

    @Basic
    @Column(name = "search_order")
    public Integer getSearchOrder() {
        return searchOrder;
    }

    public void setSearchOrder(Integer searchOrder) {
        this.searchOrder = searchOrder;
    }

    @Basic
    @Column(name = "update_order")
    public Integer getUpdateOrder() {
        return updateOrder;
    }

    public void setUpdateOrder(Integer updateOrder) {
        this.updateOrder = updateOrder;
    }

    @Basic
    @Column(name = "add_order")
    public Integer getAddOrder() {
        return addOrder;
    }

    public void setAddOrder(Integer addOrder) {
        this.addOrder = addOrder;
    }

    @Basic
    @Column(name = "detele_order")
    public Integer getDeteleOrder() {
        return deteleOrder;
    }

    public void setDeteleOrder(Integer deteleOrder) {
        this.deteleOrder = deteleOrder;
    }

    @Basic
    @Column(name = "search_promotion_style")
    public Integer getSearchPromotionStyle() {
        return searchPromotionStyle;
    }

    public void setSearchPromotionStyle(Integer searchPromotionStyle) {
        this.searchPromotionStyle = searchPromotionStyle;
    }

    @Basic
    @Column(name = "update_promotion_style")
    public Integer getUpdatePromotionStyle() {
        return updatePromotionStyle;
    }

    public void setUpdatePromotionStyle(Integer updatePromotionStyle) {
        this.updatePromotionStyle = updatePromotionStyle;
    }

    @Basic
    @Column(name = "add_promotion_style")
    public Integer getAddPromotionStyle() {
        return addPromotionStyle;
    }

    public void setAddPromotionStyle(Integer addPromotionStyle) {
        this.addPromotionStyle = addPromotionStyle;
    }

    @Basic
    @Column(name = "detele_promotion_style")
    public Integer getDetelePromotionStyle() {
        return detelePromotionStyle;
    }

    public void setDetelePromotionStyle(Integer detelePromotionStyle) {
        this.detelePromotionStyle = detelePromotionStyle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthorityEntity that = (AuthorityEntity) o;

        if (authorityId != null ? !authorityId.equals(that.authorityId) : that.authorityId != null) return false;
        if (authorityName != null ? !authorityName.equals(that.authorityName) : that.authorityName != null)
            return false;
        if (addProduct != null ? !addProduct.equals(that.addProduct) : that.addProduct != null) return false;
        if (delProduct != null ? !delProduct.equals(that.delProduct) : that.delProduct != null) return false;
        if (searchProduct != null ? !searchProduct.equals(that.searchProduct) : that.searchProduct != null)
            return false;
        if (updateProduct != null ? !updateProduct.equals(that.updateProduct) : that.updateProduct != null)
            return false;
        if (publishProduct != null ? !publishProduct.equals(that.publishProduct) : that.publishProduct != null)
            return false;
        if (offlineProduct != null ? !offlineProduct.equals(that.offlineProduct) : that.offlineProduct != null)
            return false;
        if (searchUser != null ? !searchUser.equals(that.searchUser) : that.searchUser != null) return false;
        if (updateUser != null ? !updateUser.equals(that.updateUser) : that.updateUser != null) return false;
        if (addUser != null ? !addUser.equals(that.addUser) : that.addUser != null) return false;
        if (deteleUser != null ? !deteleUser.equals(that.deteleUser) : that.deteleUser != null) return false;
        if (upgradeUser != null ? !upgradeUser.equals(that.upgradeUser) : that.upgradeUser != null) return false;
        if (downgradeUser != null ? !downgradeUser.equals(that.downgradeUser) : that.downgradeUser != null)
            return false;
        if (noticeUser != null ? !noticeUser.equals(that.noticeUser) : that.noticeUser != null) return false;
        if (searchGroup != null ? !searchGroup.equals(that.searchGroup) : that.searchGroup != null) return false;
        if (updateGroup != null ? !updateGroup.equals(that.updateGroup) : that.updateGroup != null) return false;
        if (addGroup != null ? !addGroup.equals(that.addGroup) : that.addGroup != null) return false;
        if (deteleGroup != null ? !deteleGroup.equals(that.deteleGroup) : that.deteleGroup != null) return false;
        if (publishGroup != null ? !publishGroup.equals(that.publishGroup) : that.publishGroup != null) return false;
        if (onlineGroup != null ? !onlineGroup.equals(that.onlineGroup) : that.onlineGroup != null) return false;
        if (offlineGroup != null ? !offlineGroup.equals(that.offlineGroup) : that.offlineGroup != null) return false;
        if (searchOrder != null ? !searchOrder.equals(that.searchOrder) : that.searchOrder != null) return false;
        if (updateOrder != null ? !updateOrder.equals(that.updateOrder) : that.updateOrder != null) return false;
        if (addOrder != null ? !addOrder.equals(that.addOrder) : that.addOrder != null) return false;
        if (deteleOrder != null ? !deteleOrder.equals(that.deteleOrder) : that.deteleOrder != null) return false;
        if (searchPromotionStyle != null ? !searchPromotionStyle.equals(that.searchPromotionStyle) : that.searchPromotionStyle != null)
            return false;
        if (updatePromotionStyle != null ? !updatePromotionStyle.equals(that.updatePromotionStyle) : that.updatePromotionStyle != null)
            return false;
        if (addPromotionStyle != null ? !addPromotionStyle.equals(that.addPromotionStyle) : that.addPromotionStyle != null)
            return false;
        if (detelePromotionStyle != null ? !detelePromotionStyle.equals(that.detelePromotionStyle) : that.detelePromotionStyle != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = authorityId != null ? authorityId.hashCode() : 0;
        result = 31 * result + (authorityName != null ? authorityName.hashCode() : 0);
        result = 31 * result + (addProduct != null ? addProduct.hashCode() : 0);
        result = 31 * result + (delProduct != null ? delProduct.hashCode() : 0);
        result = 31 * result + (searchProduct != null ? searchProduct.hashCode() : 0);
        result = 31 * result + (updateProduct != null ? updateProduct.hashCode() : 0);
        result = 31 * result + (publishProduct != null ? publishProduct.hashCode() : 0);
        result = 31 * result + (offlineProduct != null ? offlineProduct.hashCode() : 0);
        result = 31 * result + (searchUser != null ? searchUser.hashCode() : 0);
        result = 31 * result + (updateUser != null ? updateUser.hashCode() : 0);
        result = 31 * result + (addUser != null ? addUser.hashCode() : 0);
        result = 31 * result + (deteleUser != null ? deteleUser.hashCode() : 0);
        result = 31 * result + (upgradeUser != null ? upgradeUser.hashCode() : 0);
        result = 31 * result + (downgradeUser != null ? downgradeUser.hashCode() : 0);
        result = 31 * result + (noticeUser != null ? noticeUser.hashCode() : 0);
        result = 31 * result + (searchGroup != null ? searchGroup.hashCode() : 0);
        result = 31 * result + (updateGroup != null ? updateGroup.hashCode() : 0);
        result = 31 * result + (addGroup != null ? addGroup.hashCode() : 0);
        result = 31 * result + (deteleGroup != null ? deteleGroup.hashCode() : 0);
        result = 31 * result + (publishGroup != null ? publishGroup.hashCode() : 0);
        result = 31 * result + (onlineGroup != null ? onlineGroup.hashCode() : 0);
        result = 31 * result + (offlineGroup != null ? offlineGroup.hashCode() : 0);
        result = 31 * result + (searchOrder != null ? searchOrder.hashCode() : 0);
        result = 31 * result + (updateOrder != null ? updateOrder.hashCode() : 0);
        result = 31 * result + (addOrder != null ? addOrder.hashCode() : 0);
        result = 31 * result + (deteleOrder != null ? deteleOrder.hashCode() : 0);
        result = 31 * result + (searchPromotionStyle != null ? searchPromotionStyle.hashCode() : 0);
        result = 31 * result + (updatePromotionStyle != null ? updatePromotionStyle.hashCode() : 0);
        result = 31 * result + (addPromotionStyle != null ? addPromotionStyle.hashCode() : 0);
        result = 31 * result + (detelePromotionStyle != null ? detelePromotionStyle.hashCode() : 0);
        return result;
    }
}
