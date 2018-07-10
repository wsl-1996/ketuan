package com.skqtec.repository;

import com.alibaba.fastjson.JSONObject;
import com.skqtec.entity.GroupEntity;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class GroupRepositoryImpl implements  GroupRepository{
    static Logger logger = Logger.getLogger(GroupRepositoryImpl.class.getName());
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }

    public GroupEntity load(String id) {
        return (GroupEntity)getCurrentSession().load(GroupEntity.class,id);
    }

    public GroupEntity get(String id) {
        return (GroupEntity)getCurrentSession().get(GroupEntity.class,id);
    }

    public List<GroupEntity> findAll() {
        return getCurrentSession().createQuery("from "+GroupEntity.class.getSimpleName()).list();
    }

    public List<GroupEntity> query(JSONObject jsonObject) {
        Session s = null;
        List<GroupEntity> list = new ArrayList<GroupEntity>();
        try {
            s = getCurrentSession();
            Criteria c = s.createCriteria(GroupEntity.class);
            Iterator it = jsonObject.keySet().iterator();
            while (it.hasNext()){
                String key = (String)it.next();
                String value = jsonObject.getString(key);
                c.add(Restrictions.eq(key, value));
            }
            list = c.list();
            for (GroupEntity group : list) {
                System.out.println(group.getGroupName());
            }
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
        }finally {
            if (s != null)
                s.close();
            return list;
        }
    }

    public List<GroupEntity> search(String key) {
        Session s = null;
        List<GroupEntity> list = new ArrayList<GroupEntity>();
        try {
            s = getCurrentSession();
            //list = s.createQuery("from "+ GroupEntity.class.getSimpleName()+" as a where a.groupName like '%"+key+"%' and a.groupDiscription like '%"+key+"%' and a.deliverAddress like '%"+key+"%'").list();
//            Criteria c = s.createCriteria(GroupEntity.class);
//            c.add(Restrictions.like("groupName", "%"+key+"%"));
//            c.add(Restrictions.like("groupDiscription", "%"+key+"%"));
//            c.add(Restrictions.like("deliverAddress", "%"+key+"%"));
//            list = c.list();
            Query q = s.createSQLQuery("SELECT * FROM ketuanDB.`GROUP` as a where a.group_name like '%"+key+"%' and a.group_discription like '%"+key+"%'").addEntity(GroupEntity.class);
            list = q.list();
            for (GroupEntity group : list) {
                System.out.println(group.getGroupName());
            }
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

    public void persist(GroupEntity entity) {
        Session session = getCurrentSession();
        session.persist(entity);
    }

    public String save(GroupEntity entity) {
        Session session = getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Serializable pKey = session.save(entity);

        transaction.commit();
        return  (String)pKey;
    }

    public void saveOrUpdate(GroupEntity entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    public void delete(String id) {
        GroupEntity group = load(id);
        getCurrentSession().delete(group);
    }

    public void flush() {
        getCurrentSession().flush();
    }
}
