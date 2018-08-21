package com.skqtec.repository;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.skqtec.entity.ProductClassifyCodeEntity;
import java.util.List;
@Repository
public class ProductClassifyCodeRepositoryImpl implements ProductClassifyCodeRepository {
    static Logger logger = Logger.getLogger(ProductClassifyCodeRepositoryImpl.class.getName());
    @Autowired
    SessionFactory sessionFactory;
    public ProductClassifyCodeEntity load(String id) {
        return null;
    }

    public ProductClassifyCodeEntity get(String id) {
        return null;
    }

    public List<ProductClassifyCodeEntity> findAll() {
        Session session=sessionFactory.openSession();
        List<ProductClassifyCodeEntity>list=session.createQuery("from "+ProductClassifyCodeEntity.class.getSimpleName()).list();
        session.close();
        return list;
    }

    public List<ProductClassifyCodeEntity> query(JSONObject jsonObject) {
        return null;
    }

    public List<ProductClassifyCodeEntity> search(String key) {
        return null;
    }

    public void persist(ProductClassifyCodeEntity entity) {

    }

    public String save(ProductClassifyCodeEntity entity) {
        return null;
    }

    public void saveOrUpdate(ProductClassifyCodeEntity entity) {

    }

    public void delete(String id) {

    }

    public void flush() {

    }
}
