package com.api.service;

import com.api.dao.mapper.ShareFollowMapper;
import com.api.domain.ShareFollow;
import com.api.service.shareServcie.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 李显琪 on 2021/10/7.
 * 关注股票的均线
 */

@Component
public class ShareFollowService {

    @Autowired
    private ShareFollowMapper shareFollowMapper;
    @Autowired
    private ShareService shareService;


    public void updateRecord() {
        List<ShareFollow> shareFollowList = shareFollowMapper.getAllRecord();
        updateAvgPrice(shareFollowList);
        shareFollowList.forEach(shareFollow -> shareFollowMapper.updateByPrimaryKey(shareFollow));
    }

    // update 均线值
    private List<ShareFollow> updateAvgPrice(List<ShareFollow> list) {
        list.forEach(shareFollow -> {
            String symbol = shareFollow.getSymbol();
            Double price3 = calculateAvgPrice(3, symbol);
            Double price5 = calculateAvgPrice(5, symbol);
            Double price10 = calculateAvgPrice(10, symbol);
            Double price13 = calculateAvgPrice(13, symbol);
            Double price20 = calculateAvgPrice(20, symbol);
            shareFollow.setThree(price3);
            shareFollow.setFive(price5);
            shareFollow.setTen(price10);
            shareFollow.setThirteen(price13);
            shareFollow.setTwenty(price20);

        });
        return list;

    }

    public Double calculateAvgPrice(int limitDay, String symbol) {
        return shareService.calculateAvgPrice(limitDay, symbol);
    }

    public void getAlarmList() {


    }

    public List<ShareFollow> getAll() {
        return shareFollowMapper.getAllRecord();
    }
}
