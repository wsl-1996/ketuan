package com.skqtec.repository;

import com.skqtec.entity.ExpressageEntity;

import java.util.List;

public interface ExpressageRepository extends DomainRepository<ExpressageEntity,String> {
    //List<ExpressageEntity> query(String isAccomplish);
    List<ExpressageEntity> query(String page, String state);

}

