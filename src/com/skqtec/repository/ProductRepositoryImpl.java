package com.skqtec.repository;

import com.alibaba.fastjson.JSONObject;
import com.skqtec.entity.ProductEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepositoryImpl  implements  ProductRepository{

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }

    public ProductEntity load(Integer id) {
        return (ProductEntity)getCurrentSession().load(ProductEntity.class,id);
    }

    public ProductEntity get(Integer id) {
        return (ProductEntity)getCurrentSession().get(ProductEntity.class,id);
    }

    public List<ProductEntity> findAll() {
        return null;
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
//            c.add(Restrictions.eq("aname", name));//eq是等于，gt是大于，lt是小于,or是或

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

    public void persist(ProductEntity entity) {
        Session session = getCurrentSession();
        session.persist(entity);
    }

    public Integer save(ProductEntity entity) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Integer result = (Integer) session.save(entity);
        transaction.commit();
        return  result;
    }

    public void saveOrUpdate(ProductEntity entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    public void delete(Integer id) {
        ProductEntity product = load(id);
        getCurrentSession().delete(product);
    }

    public void flush() {
        getCurrentSession().flush();
    }
}
