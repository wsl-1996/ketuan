package com.skqtec.repository;

import com.alibaba.fastjson.JSONObject;
import com.skqtec.entity.CommentEntity;
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
    public class CommentRepositoryImpl implements CommentRepository {
        static Logger logger = Logger.getLogger(com.skqtec.repository.CommentRepositoryImpl.class.getName());
        @Autowired
        private SessionFactory sessionFactory;

        private Session getCurrentSession() {
            return this.sessionFactory.openSession();
        }

        public CommentEntity load(String id) {
            return (CommentEntity)getCurrentSession().load(CommentEntity.class,id);
        }

        public CommentEntity get(String id) {
            return (CommentEntity)getCurrentSession().get(CommentEntity.class,id);
        }

        public List<CommentEntity> findAll() {
            return getCurrentSession().createQuery("from "+CommentEntity.class.getSimpleName()).list();
        }

        public List<CommentEntity> query(JSONObject jsonObject) {
            Session s = null;
            List<CommentEntity> list = new ArrayList<CommentEntity>();
            try {
                s = getCurrentSession();
                Criteria c = s.createCriteria(CommentEntity.class);
                Iterator it = jsonObject.keySet().iterator();
                while (it.hasNext()){
                    String key = (String)it.next();
                    String value = jsonObject.getString(key);
                    c.add(Restrictions.eq(key, value));
                }
                list = c.list();
                for (CommentEntity Comment : list) {
                    //System.out.println(Comment.getName());
                }
            }catch (Exception e){
                logger.error(e);
            }
            finally {
                if (s != null)
                    s.close();
                return list;
            }
        }

        public List<CommentEntity> search(String key) {
            Session s = null;
            List<CommentEntity> list = new ArrayList<CommentEntity>();
            try {
                s = getCurrentSession();
                Query q = s.createSQLQuery("SELECT * FROM `COMMENT` as a where a.name like '%"+key+"%' and a.discription like '%"+key+"%'").addEntity(CommentEntity.class);
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
        public String getDegereeOfPraise(String groupId){
            Session s=null;
            double k=0;
            try {
                s = getCurrentSession();
                Query q1 = s.createSQLQuery("SELECT * FROM `COMMENT` as a where a.group_id="+groupId);
                double k1= q1.list().size();
                Query q2 = s.createSQLQuery("SELECT * FROM `COMMENT` as a where a.group_id="+groupId+" and a.star_level>3");
                double k2= q2.list().size();
                long k3=Double.doubleToLongBits(k1);
                if(k3!=0)
                    k=k2/k1;
                else
                    k=1;
            }
            catch(Exception e){
                logger.error(e.getMessage(),e);
            }
            finally {
                if (s != null)
                    s.close();
                return String.valueOf(k*100)+"%";
            }

        }
        public void persist(CommentEntity entity) {
            Session session = getCurrentSession();
            session.persist(entity);
        }

        public String save(CommentEntity entity){
            Session session=null;
            session=getCurrentSession();
            Transaction transaction = session.beginTransaction();
            Serializable pKey = session.save(entity);
            transaction.commit();
            return  (String)pKey;
        }

        public void saveOrUpdate(CommentEntity entity) {
            getCurrentSession().saveOrUpdate(entity);
        }

        public void delete(String id) {
            CommentEntity image = load(id);
            getCurrentSession().delete(image);
        }

        public void flush() {
            getCurrentSession().flush();
        }
    }


