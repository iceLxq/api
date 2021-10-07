package com.api.job;

import com.api.domain.ShareFollow;
import com.api.domain.share.Share;
import com.api.proxy.XueqiuProxyServe;
import com.api.service.ShareFollowService;
import com.api.service.msg.DingDingService;
import com.api.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 李显琪 on 2021/10/7.
 */

@Component
public class SharePriceAlarmSchedule {
    @Autowired
    private ShareFollowService shareFollowService;
    @Autowired
    private XueqiuProxyServe xueqiuProxyServe;
    @Autowired
    private DingDingService dingDingService;

    //缓存shareFollow 的数据
    private Map<String, List<ShareFollow>> shareFollowMap = new HashMap<>();

    @Scheduled(cron = "0 0/1 * * * ?")
    public void priceAlarm() {
        String key = DateUtil.getDayStr();
        checkShareFollowMap(key);
        Map<String, ShareFollow> focusSymbolMap = getFocusSymbolMap(key);
        List<Share> shareInfoList = xueqiuProxyServe.getShareInfoList();
        shareInfoList.forEach(share -> {
            if (focusSymbolMap.containsKey(share.getSymbol())) {
                compareAvgPrice(share, focusSymbolMap.get(share.getSymbol()));
            }
        });
    }

    //当前数据与各日线对比
    private void compareAvgPrice(Share share, ShareFollow shareFollow) {
        String focusOn = shareFollow.getFocusOn();
        if (!StringUtils.isEmpty(focusOn)) { //关注 3， 5日线
            List<String> focusOnList = Arrays.asList(focusOn.split(","));
            focusOnList.forEach(dayStr ->
                    comparePriceSwitch( share, shareFollow, dayStr)
            );
        }
        comparePrice(share.getCurrent(), shareFollow.getFive(), true, share.getName(),5);

    }

    private void comparePriceSwitch(Share share, ShareFollow shareFollow, String dayStr) {
        Boolean gt = true; //突破， 现价需要大于均价
        if (dayStr.startsWith("-")){
            gt = false; //回落， 现价需要小于均价
            dayStr = dayStr.substring(1);
        }
        switch (dayStr){
            case "3":
                comparePrice(share.getCurrent(), shareFollow.getThree(), gt, share.getName(),3);
                break;
            case "5":
                comparePrice(share.getCurrent(), shareFollow.getFive(), gt, share.getName(),5);
                break;
            case "10":
                comparePrice(share.getCurrent(), shareFollow.getTen(), gt, share.getName(),10);
                break;
            case "13":
                comparePrice(share.getCurrent(), shareFollow.getThirteen(), gt, share.getName(),13);
                break;
            case "20":
                comparePrice(share.getCurrent(), shareFollow.getTwenty(), gt, share.getName(),20);
                break;
            default:
                comparePrice(share.getCurrent(), shareFollow.getFive(), gt, share.getName(),5);
        }

    }

    private void comparePrice(Double current, Double avgPrice, Boolean gt, String name, int day){
        if (gt) {
            if (current > avgPrice){
                dingDingService.sendDingMsg(day, name);
            }
        } else {
            if (current < avgPrice){
                dingDingService.sendDingMsg(day, name);
            }
        }
    }


    private Map<String, ShareFollow> getFocusSymbolMap(String key) {
        List<ShareFollow> shareFollowList = shareFollowMap.get(key);
        Map<String, ShareFollow> symbolMap = new HashMap<>();
        shareFollowList.forEach(shareFollow -> symbolMap.put(shareFollow.getSymbol(), shareFollow));
        return symbolMap;
    }

    private void checkShareFollowMap(String key) {
        if (shareFollowMap.get(key) == null) {
            List<ShareFollow> shareFollowList = shareFollowService.getAll();
            shareFollowMap.put(key, shareFollowList);
        }
    }
}
