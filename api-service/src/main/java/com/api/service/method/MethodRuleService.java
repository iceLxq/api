package com.api.service.method;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.api.domain.DingMsg.DingBody;
import com.api.domain.share.Share;
import com.api.service.msg.DingDingService;
import com.api.service.shareServcie.ShareService;
import com.api.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by 李显琪 on 2021/8/22.
 * 规则方法类
 */
@Component
public class MethodRuleService {

    @Autowired
    private ShareService shareService;
    @Autowired
    private DingDingService dingDingService;


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

        System.out.println("-----------without 20 method -----------");
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

        System.out.println("-----------without 20 method -----------");
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

    /**
     * 计算周四涨跌概率
     */
    public void test(String symbol, int weekday) {
        Date date = new Date();
        List<String> dateList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Date addDays = DateUtil.addDays(date, 0 - i);
            if (weekday == getWeekDay(addDays)) {
                String dayStr = DateUtil.getDayStr(addDays);
                dateList.add(dayStr);
            }
        }
        List<Share> shareList = shareService.getShareListByDateList(symbol, dateList);
        List<Share> upShareList = new ArrayList<>();
        List<Share> downShareList = new ArrayList<>();
        shareList.stream().forEach(share -> {
            if (share.getPercent() > 0) {
                upShareList.add(share);
            } else {
                downShareList.add(share);
            }
        });

        System.out.println("上涨概率 ： " + upShareList.size() * 100 / shareList.size());
        double upSum = upShareList.stream().mapToDouble(Share::getPercent).sum();
        System.out.println("上涨平均值 ：" + upSum / upShareList.size());
        double downSum = downShareList.stream().mapToDouble(Share::getPercent).sum();
        System.out.println("下跌平均值 ：" + downSum / downShareList.size());

    }

    private int getWeekDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 口袋支点
     * 1. 250日内最高点 FDK250
     * 2. 120日的最高点 H120
     * 3. 120日内最高点至今的最低点 L120
     * 4. 40日内最低价不低于120日内最高价的一半 FKD61
     * or 创250日内的最高价 FKD250
     * 5. 阶段最大下跌 < 46% L120 / H120 > 54%
     */
    public void getPackageSupport() {
        Date dateBegin = shareService.getDateBegin(1, null);
        List<Share> shareList = shareService.getShareByPercent(7.0, dateBegin);
        List<Share> conditionList = new ArrayList<>();
        shareList.forEach(share -> {
            //成交额大于5亿
            if (share.getAmount() < 500000000 ){
                return ;
            }
            Share H120 = shareService.getHighCurrentLimitDay(120, share.getSymbol());
            //120 今日内最高点
            if (dateBegin.getTime() == H120.getDate().getTime()) {
                conditionList.add(share);
                return;
            }
            Share L120 = shareService.getLowCurrentByDate(H120.getDate(), dateBegin, share.getSymbol());
            //次新 临近日期
            if (L120 == null || L120.getCurrent() / H120.getPercent() < 0.54){
                return ;
            }
            Share FKD61 = shareService.getLowCurrentLimitDay(40, share.getSymbol());
            if (FKD61.getCurrent()/L120.getCurrent() < 0.5) {
                return ;
            }
            conditionList.add(share);
        });
        conditionList.forEach(share -> {
            System.out.println("---------");
            System.out.println(share);
        });

    }

}
