package com.api.service.daylyService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.api.domain.DingMsg.DingBody;
import com.api.domain.bean.Dayfollow;
import com.api.domain.share.Share;
import com.api.proxy.XueqiuProxyServe;
import com.api.service.DayfollowService;
import com.api.service.msg.DingDingService;
import com.api.service.shareServcie.ShareService;
import com.api.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    @Autowired
    private DingDingService dingDingService;


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
     * 3: 成交量大于5亿
     */
    public void cacheDayFollow() {
        List<Share> daylyShareInfoList = xueqiuProxyServe.getShareInfoList();
        Share yesterdayShare = getYesterdayShare(daylyShareInfoList);
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
                        && daylyShare.getAmount() > 5 * 10000 * 10000L
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
                logger.info("cacheDayFollowException" + daylyShare.getName());
            }
        });
        if (dayfollowList.isEmpty()) {
            logger.info("cacheDayFollow 未找到异动的股票");
            return;
        }
        List<Dayfollow> dayRecord = dayfollowService.getDayRecord(DateUtil.getDay());

        List<String> symbolList = dayRecord.stream().map(Dayfollow::getSymbol).collect(Collectors.toList());
        List<Dayfollow> insertDayFollowList = new ArrayList<>();
        List<Dayfollow> updateDayFollowList = new ArrayList<>();
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
        List<Share> shareList = shareService.getShareLimitByDate(daylyShareInfoList.get(10).getSymbol(), 2);
        return shareList.get(1);
    }

    /**
     * 缓存5日回撤25%-30%
     */
    public void cache60Breach() {
        System.out.println("cache60Breach");

    }


    /**
     * 交易员
     * 条件
     * 1：前一个成交额大于10亿
     * 2：最近10个交易日幅度大于30%
     * 3: 近20个日成交额日均大于12亿
     * 4：今日涨幅小于11%
     * 5：现价小于90（更多人参与）
     * 6：非科创， 非st
     */

    public void jyyMethod() {
        List<Share> shareLimitByDate = shareService.getShareLimitByDate("002714", 21);
        List<Share> shareByDate1 = shareService.getShareByDate(shareLimitByDate.get(0).getDate());
        List<Share> shareByDate10 = shareService.getShareByDate(shareLimitByDate.get(10).getDate());
        List<Share> shareByDate20 = shareService.getShareByDate(shareLimitByDate.get(20).getDate());
        Map<String, Share> shareDate1FilterMap = new HashMap<>();
        shareByDate1.stream().forEach(share -> {
            if (!share.getSymbol().startsWith("688")
                    && !share.getName().startsWith("*ST")
                    && share.getPercent() != null
                    && share.getPercent() < 11
                    && share.getAmount() != null
                    && share.getAmount() > 1000000000
                    && share.getCurrent() < 90) {
                shareDate1FilterMap.put(share.getSymbol(), share);
            }
        });
        Map<String, Share> shareDate20Map = new HashMap<>();
        shareByDate20.stream().forEach(share -> {
            if (shareDate1FilterMap.containsKey(share.getSymbol())) {
                shareDate20Map.put(share.getSymbol(), share);
            }
        });
        Map<String, Share> shareDate10Map = new HashMap<>();
        shareByDate10.stream().forEach(share -> {
            if (shareDate1FilterMap.containsKey(share.getSymbol())) {
                shareDate10Map.put(share.getSymbol(), share);
            }
        });
        Set<String> keySet = shareDate1FilterMap.keySet();
        List<Share> filterOut20RuleList = new ArrayList<>();
        List<Share> filterList = new ArrayList<>();
        for (String key : keySet) {
            Share share = shareDate1FilterMap.get(key);
            Share share10 = shareDate10Map.get(key);
            if (null == share10) {
                continue;
            }
            //2：最近10个交易日幅度大于30%
            if ((share.getCurrent() - share10.getCurrent()) / share10.getCurrent() > 0.3) {
                Share share20 = shareDate20Map.get(key);
                if (null == share20) {
                    continue;
                }
                Long avgAmount = shareService.calculateAvgAmount(20, share20.getSymbol());
                share.setAmount(avgAmount);
                filterOut20RuleList.add(share);
                // 3: 近20个日成交额日均大于12亿
                if (avgAmount > 1200000000) {
                    filterList.add(share);
                }
            }
        }

        System.out.println("-----------without 20 rule -----------");
        filterOut20RuleList.forEach(share -> System.out.println(
                share.getName() + " " + share.getSymbol() + " avgPrice = " + share.getAmount()
        ));

        System.out.println("----------------------");
        filterList.forEach(share -> System.out.println(
                share.getName() + " " + share.getSymbol() + " avgPrice = " + share.getAmount()
        ));
    }

    /**
     * 改为：
     * 1：前一个成交额大于10亿
     * 2：最近10个交易日最低（lowDay）的那天到今天幅度大于30%
     * 3: lowDay到今天成交额日均大于12亿
     * 4：今日涨幅小于11%
     * 5：现价小于90（更多人参与）
     * 6：非科创， 非st
     */

    public void jyyMethodUpdate() {
        List<Share> shareLimitByDate = shareService.getShareLimitByDate("002714", 21);
        List<Share> shareByDate1 = shareService.getShareByDate(shareLimitByDate.get(0).getDate());
        Map<String, Share> shareDate1FilterMap = new HashMap<>();
        shareByDate1.stream().forEach(share -> {
            if (!share.getSymbol().startsWith("688")
                    && !share.getName().startsWith("*ST")
                    && share.getPercent() != null
                    && share.getPercent() < 11
                    && share.getAmount() != null
                    && share.getAmount() > 1000000000
                    && share.getCurrent() < 90) {
                shareDate1FilterMap.put(share.getSymbol(), share);
            }
        });
        Set<String> keySet = shareDate1FilterMap.keySet();
        List<Share> filterOut20RuleList = new ArrayList<>();
        List<Share> filterList = new ArrayList<>();
        for (String key : keySet) {
            Share share = shareDate1FilterMap.get(key);
            //2：最近10个交易日最低点幅度大于30%
            Share lowCurrentShare10 = shareService.getLowCurrentLimitDay(10, share.getSymbol());
            if ((share.getCurrent() - lowCurrentShare10.getCurrent()) / lowCurrentShare10.getCurrent() > 0.3) {
                Long avgAmount = shareService.calculateAvgAmount(10, share.getSymbol());
                share.setAmount(avgAmount);
                filterOut20RuleList.add(share);
                // 3: 近20个日成交额日均大于12亿
                if (avgAmount > 1200000000) {
                    filterList.add(share);
                }
            }
        }

        System.out.println("-----------without 20 rule -----------");
        filterOut20RuleList.forEach(share -> System.out.println(
                share.getName() + " " + share.getSymbol() + " avgPrice = " + share.getAmount()
        ));

        System.out.println("----------------------");
        filterList.forEach(share -> {
                    dingDingService.sendDingMsg(buildDingMsg(share));
                    System.out.println(
                            share.getName() + " " + share.getSymbol() + " avgAmount = " + share.getAmount());

                }
        );
    }

    private JSONObject buildDingMsg(Share share) {
        DingBody msg = new DingBody();
        String content = "警告：" + share.getName() + " " + share.getSymbol() + " avgAmount = " + share.getAmount();
        msg.setMsgtype("text");
        msg.getText().setContent(content);
        String jsonString = JSON.toJSONString(msg);
        return JSONObject.parseObject(jsonString);
    }

}
