package com.skqtec.timertask;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.skqtec.entity.ExpressageEntity;
import com.skqtec.repository.ExpressageRepository;
import com.skqtec.tools.KdniaoTrackQueryAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class updateExpressage {
    @Autowired
    private ExpressageRepository expressageRepository;
    @Scheduled(cron = "0 * * * * ? ")
    public void run(){
        try{
            KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
            List<ExpressageEntity>list=expressageRepository.query("0");
            for(ExpressageEntity expressage:list) {
                String expCode=KdniaoTrackQueryAPI.getCompanyCode(expressage.getExpressageName());
                String expNo=expressage.getId();
                String result = api.getOrderTracesByJson(expCode, expNo);
                JSONObject jsonObject=JSON.parseObject(result);
                String traces=(String)jsonObject.get("traces");
                expressage.setExpressageDetails(traces);
                expressageRepository.saveOrUpdate(expressage);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
