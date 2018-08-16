package com.skqtec.repository;

import com.alibaba.fastjson.JSONObject;
import com.skqtec.entity.ProductclassifycodeEntity;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ProductClassifyCodeRepositoryImpl implements ProductClassifyCodeRepository {
    static Logger logger = Logger.getLogger(ProductClassifyCodeRepositoryImpl.class.getName());
    @Autowired
    SessionFactory sessionFactory;
    public ProductclassifycodeEntity load(String id) {
        return null;
    }

    public ProductclassifycodeEntity get(String id) {
        return null;
    }

    public List<ProductclassifycodeEntity> findAll() {
        Session session=sessionFactory.openSession();
        List<ProductclassifycodeEntity>list=session.createQuery("from "+ProductclassifycodeEntity.class.getSimpleName()).list();
        session.close();
        return list;
    }

    public List<ProductclassifycodeEntity> query(JSONObject jsonObject) {
        return null;
    }

    public List<ProductclassifycodeEntity> search(String key) {
        return null;
    }

    public void persist(ProductclassifycodeEntity entity) {

    }

    public String save(ProductclassifycodeEntity entity) {
        return null;
    }

    public void saveOrUpdate(ProductclassifycodeEntity entity) {

    }

    public void delete(String id) {

    }

    public void flush() {

    }
}
