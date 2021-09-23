package com.api.service.daylyService;

import com.api.domain.bean.Dayfollow;
import com.api.domain.share.Share;
import com.api.proxy.XueqiuProxyServe;
import com.api.service.DayfollowService;
import com.api.service.shareServcie.ShareService;
import com.api.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by 李显琪 on 2020/2/3.
 */
@Component
public class DaylyService {

    private static Logger logger = LoggerFactory.getLogger(DaylyService.class);

    @Autowired
    private ShareService shareService;
    @Autowired
    private XueqiuProxyServe xueqiuProxyServe;
    @Autowired
    private DayfollowService dayfollowService;


    /**
     * 缓存5日回撤25%-30%
     */
    public void cache5Retracement() {

    }

    /**
     * 当日异动
     * 条件
     * 1：股价高于前一日，(高于5日线)
     * 2：成交量放大，1倍
     * 3: 成交量大于4亿
     */
    public void cacheDayFollow() {
        List<Share> daylyShareInfoList = xueqiuProxyServe.getShareInfoList();
        Share yesterdayShare = getYesterdayShare(daylyShareInfoList);
//        List<Share> lastDayShareList = shareService.getShareByDate(DateUtil.getDayByStr("2021-1-7"));
        if (null == yesterdayShare){
            return;
        }
        List<Share> lastDayShareList = shareService.getShareByDate(yesterdayShare.getDate());
        List<Dayfollow> dayfollowList = new ArrayList<>();
        daylyShareInfoList.stream().forEach(daylyShare -> {
            Share lastShare = lastDayShareList.stream().filter(lastDayShare -> lastDayShare.getSymbol().equals(daylyShare.getSymbol())).findAny().orElse(null);
            if (lastShare == null || lastShare.getVolume() == null) {
                return;
            }
            try {
                if (lastShare.getVolume() * 2 < daylyShare.getVolume()
                        && daylyShare.getCurrent() > lastShare.getCurrent()
                        && daylyShare.getAmount() > 4 * 1000 * 1000L
                        ) {
                    printData(daylyShare);
                    Dayfollow dayfollow = new Dayfollow();
                    dayfollow.setSymbol(daylyShare.getSymbol());
                    dayfollow.setName(daylyShare.getName());
                    dayfollow.setUpdateTime(new Date());
                    dayfollow.setCreateDay(DateUtil.getDay());
                    DecimalFormat df = new DecimalFormat("0.00");//设置保留位数
                    dayfollow.setDescription(df.format((float) daylyShare.getVolume() / lastShare.getVolume()));
                    dayfollowList.add(dayfollow);
                }
            } catch (Exception e) {
                logger.info("cacheDayFollowException"  + daylyShare.getName());
            }
        });
        if (dayfollowList.isEmpty()) {
            logger.info("cacheDayFollow 未找到异动的股票");
            return;
        }
        List<Dayfollow> dayRecord = dayfollowService.getDayRecord(DateUtil.getDay());
        if (dayRecord.isEmpty()) {
            dayfollowService.insert(dayfollowList);
            return;
        }
        List<String> symbolList = dayRecord.stream().map(Dayfollow::getSymbol).collect(Collectors.toList());
        List<Dayfollow> insertDayFollowList = new ArrayList<>();
        List<Dayfollow> updateDayFollowList = new ArrayList<>();
        dayfollowList.stream().filter(dayfollow -> !symbolList.contains(dayfollow.getSymbol())).collect(Collectors.toList());
        dayfollowList.stream().forEach(dayfollow -> {
            if (!symbolList.contains(dayfollow.getSymbol())) {
                insertDayFollowList.add(dayfollow);
            } else {
                updateDayFollowList.add(dayfollow);
            }

        });
        dayfollowService.insert(insertDayFollowList);
        dayfollowService.updateBySymbolAndDate(updateDayFollowList);
    }

    private void printData(Share daylyShare) {
        String url = "https://xueqiu.com/S/";
        System.out.println(daylyShare.getName() + "  " + daylyShare.getPercent() + " " + url + daylyShare.getSymbolCode());

    }

    private Share getYesterdayShare(List<Share> daylyShareInfoList) {
        Share maxIdShare = shareService.getMaxId();
        if (maxIdShare.getDate().getTime() == DateUtil.getDay().getTime()) {
            List<Share> shareList = shareService.getShareLimitByDate(maxIdShare.getSymbol(),2);
            return shareList.get(1);
        }
        if (maxIdShare.getDate().getTime() < DateUtil.getDay().getTime()) {
            if (!xueqiuProxyServe.checkOpen(daylyShareInfoList)) {//今日休市
                logger.info("今日休市 ：{}", DateUtil.getDay());
                return null;
            }
            return maxIdShare;
        }
        return null;
    }

    /**
     * 缓存5日回撤25%-30%
     */
    public void cache60Breach() {
        System.out.println("cache60Breach");

    }

    private BigDecimal double2BigDecimal(Double num) {
        return new BigDecimal(num);
    }


    public static void main(String[] args) {
    }


}
