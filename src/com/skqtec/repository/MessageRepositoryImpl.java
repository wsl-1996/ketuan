package com.skqtec.repository;

import com.alibaba.fastjson.JSONObject;
import com.skqtec.entity.MessageEntity;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
@Repository
public class MessageRepositoryImpl implements MessageRepository{
    static Logger logger = Logger.getLogger(MessageRepositoryImpl.class.getName());
    @Autowired
    private SessionFactory sessionFactory;
    public MessageEntity load(String id) {
        return null;
    }
    private Session getCurrentSession() {
        return this.sessionFactory.openSession();
    }
    public MessageEntity get(String id) {
        return null;
    }

    public List<MessageEntity> findAll() {
        return null;
    }

    public List<MessageEntity> query(JSONObject jsonObject) {
        return null;
    }

    public List<MessageEntity> search(String key) {
        return null;
    }

    public void persist(MessageEntity entity) {

    }

    public String save(MessageEntity entity) {
        Session session = null;
        session=getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Serializable pKey = session.save(entity);
        transaction.commit();
        session.close();
        return  (String)pKey;
    }

    public void saveOrUpdate(MessageEntity entity) {

    }

    public void delete(String id) {

    }

    public void flush() {

    }
}
