package com.skqtec.repository;

import com.alibaba.fastjson.JSONObject;
import com.skqtec.entity.GroupEntity;
import com.skqtec.entity.UserEntity;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class UserRepositoryImpl implements  UserRepository{
    static Logger logger = Logger.getLogger(UserRepositoryImpl.class.getName());
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }

    public UserEntity load(String id) {
        return (UserEntity)getCurrentSession().load(UserEntity.class,id);
    }

    public UserEntity get(String id) {
        return (UserEntity)getCurrentSession().get(UserEntity.class,id);
    }

    public List<UserEntity> findAll() {
        return getCurrentSession().createQuery("from "+UserEntity.class.getSimpleName()).list();
    }

    public List<UserEntity> query(JSONObject jsonObject) {
        Session s = null;
        List<UserEntity> list = new ArrayList<UserEntity>();
        try {
            s = getCurrentSession();
            Criteria c = s.createCriteria(UserEntity.class);
            Iterator it = jsonObject.keySet().iterator();
            while (it.hasNext()){
                String key = (String)it.next();
                String value = jsonObject.getString(key);
                c.add(Restrictions.eq(key, value));
            }
            list = c.list();
        } finally {
            if (s != null)
                s.close();
            return list;
        }
    }

    public List<UserEntity> search(String key) {
        Session s = null;
        List<UserEntity> list = new ArrayList<UserEntity>();
        try {
            s = getCurrentSession();
            Query q = s.createSQLQuery("SELECT * FROM ketuanDB.`USER` as a where a.nickname like '%"+key+"%' and a.email like '%"+key+"%' and a.phone like '%"+key+"%'").addEntity(GroupEntity.class);
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

    public void persist(UserEntity entity) {
        Session session = getCurrentSession();
        session.persist(entity);
    }

    public String save(UserEntity entity) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        String result = (String)session.save(entity);
        transaction.commit();
        return  result;
    }

    public void saveOrUpdate(UserEntity entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    public void delete(String id) {
        UserEntity user = load(id);
        getCurrentSession().delete(user);
    }

    public void flush() {
        getCurrentSession().flush();
    }
}
