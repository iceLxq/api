package com.api.service;

import com.api.dao.mapper.GzFollowMapper;
import com.api.domain.bean.GzFollow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by 李显琪 on 2021/2/12.
 */
@Component
public class GzFollowService {

    @Autowired
    private GzFollowMapper gzFollowMapper;

    public void instertList(List<GzFollow> gzFollowList) {
        gzFollowMapper.insertList(gzFollowList);
    }

    public void compareAndInsert(List<GzFollow> gzFollowList) {
        if (gzFollowList.isEmpty()){
            return ;
        }
        List<GzFollow> isnertRecord = compareWithPre(gzFollowList);
        




    }

    private List<GzFollow> compareWithPre(List<GzFollow> gzFollowList) {
        getLastRecord(gzFollowList.get(0).getDate());
        return null;
    }

    public List<GzFollow> getLastRecord(Date date){
        return null;
    }
}
