package com.skqtec.repository;

import com.alibaba.fastjson.JSONObject;
import com.skqtec.entity.GroupEntity;

import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.skqtec.entity.ProductEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepositoryImpl  implements  ProductRepository{
    static Logger logger = Logger.getLogger(ProductRepositoryImpl.class.getName());
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }

    public ProductEntity load(String id) {
        return (ProductEntity)getCurrentSession().load(ProductEntity.class,id);
    }

    public ProductEntity get(String id) {
        return (ProductEntity)getCurrentSession().get(ProductEntity.class,id);
    }

    public List<ProductEntity> findAll() {
        return getCurrentSession().createQuery("from "+ProductEntity.class.getSimpleName()).list();
    }

    public List<ProductEntity> query(JSONObject jsonObject) {
        Session s = null;
        List<ProductEntity> list = new ArrayList<ProductEntity>();
        try {
            s = getCurrentSession();
            Criteria c = s.createCriteria(ProductEntity.class);
            Iterator it = jsonObject.keySet().iterator();
            while (it.hasNext()){
                String key = (String)it.next();
                String value = jsonObject.getString(key);
                c.add(Restrictions.eq(key, value));
            }
            list = c.list();
            for (ProductEntity image : list) {
                System.out.println(image.getProductName());
            }
        } finally {
            if (s != null)
                s.close();
            return list;
        }
    }

    public List<ProductEntity> search(String key) {
        Session s = null;
        List<ProductEntity> list = new ArrayList<ProductEntity>();
        try {
            s = getCurrentSession();
            Query q = s.createSQLQuery("SELECT * FROM ketuanDB.`PRODUCT` as a where a.product_name like '%"+key+"%' and a.product_produce_address like '%"+key+"%' and a.product_info like '%"+key+"%' and a.product_label like '%"+key+"%'").addEntity(GroupEntity.class);
            list = q.list();
        }
        catch(Exception e){
            logger.error(e.getMessage(),e);
        }
        finally {
            if (s != null)
                s.close();
            return list;
        }
    }

    public void persist(ProductEntity entity) {
        Session session = getCurrentSession();
        session.persist(entity);
    }

    public String save(ProductEntity entity) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        String result = (String)session.save(entity);
        transaction.commit();
        return  result;
    }

    public void saveOrUpdate(ProductEntity entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    public void delete(String id) {
        ProductEntity product = load(id);
        getCurrentSession().delete(product);
    }

    public void flush() {
        getCurrentSession().flush();
    }
}
