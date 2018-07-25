package com.skqtec.repository;

import com.skqtec.entity.CommentEntity;

import java.util.List;
public interface CommentRepository extends DomainRepository<CommentEntity,String> {
    public String getDegereeOfPraise(String groupId);
    public List<CommentEntity>query(String page,String productId);
}
