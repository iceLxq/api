package com.api.job;

import com.api.domain.Lhb;
import com.api.domain.share.Share;
import com.api.domain.snowResult.LhbResponse;
import com.api.domain.snowResult.LhbResult;
import com.api.domain.snowResult.SnowResult;
import com.api.service.LhbService;
import com.api.service.daylyService.DaylyService;
import com.api.service.shareServcie.ShareService;
import com.api.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Created by 李显琪 on 2019/3/23.
 */

@Component
public class QuartzJob {

    private static Logger logger = LoggerFactory.getLogger(QuartzJob.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ShareService shareService;
    @Autowired
    private DaylyService daylyService;
    @Autowired
    private LhbService lhbService;

    private static int retry = 0;

    /**
     * 定时获取同步证券代码
     */
    @Scheduled(cron = "30 4 16 * * ?")
    public void syncSecuritiesCode() {
        String url = "https://xueqiu.com/service/v5/stock/screener/quote/list?size=5000&order=desc&orderby=percent&order_by=percent&market=CN&type=sh_sz&_=1564279775146&page=1";
        Map<String, Object> param = new HashMap<>();
        SnowResult snowResult = restTemplate.getForObject(url, SnowResult.class, param);
        List<Share> list = snowResult.getData().getList();
        try {
            if (!checkExist(list.get(0)) && !checkExist(list.get(1))) { //休市
                return;
            }

            Date time = DateUtil.getDay();
            list.forEach(share -> {
                share.setDate(time);
                share.setSymbol(share.getSymbol());
            });
            shareService.batchInsert(list);
            retry = 0;
        } catch (Exception e) {
            logger.info(e.getMessage());
            logger.info("------------------");
            logger.info(e.toString());
            if (retry == 3) {
                return;
            }
            retry++;
            try {
                Thread.sleep(600000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            syncSecuritiesCode();
        }
    }

    private Boolean checkExist(Share share) {
        List<Share> records = shareService.getShareByRecord(share);
        return records.isEmpty();
    }


    /**
     * 成交量放大个股
     */
    @Scheduled(cron = "* 0/5 9,15 * * ?")
    public void cacheDayFollow() {
        daylyService.cacheDayFollow();
    }


    /**
     * 定时同步龙虎榜数据
     */
    @Scheduled(cron = "30 4 16 * * ?")
    public void syncLHBData() {
        Date createDate = DateUtil.getDay();
        Long date = createDate.getTime();
        String url = String.format("https://xueqiu.com/service/v5/stock/hq/longhu?date=%s", date);
        Map<String, Object> param = new HashMap<>();
        LhbResult result = restTemplate.getForObject(url, LhbResult.class, param);
        LhbResponse data = result.getData();
        try {
            if (data.getItems().isEmpty()) { //休市
                logger.info(String.format("cant not get lhb data,date is ", DateUtil.getDay()));
                return;
            }
            Long finalDate = date;
            List<Lhb> lhbList = new ArrayList<>();
            data.getItems().forEach(lhb -> {
                        Lhb record = new Lhb();
                        BeanUtils.copyProperties(lhb, record);
                        record.setDescription(String.format("https://xueqiu.com/snowman/S/%s/detail#/LHB?date=%s", lhb.getSymbol(), finalDate.toString()));
                        record.setDate(createDate);
                        lhbList.add(record);

                    }
            );
            lhbService.batchInsert(lhbList);
            retry = 0;
        } catch (Exception e) {
//            logger.info(e.getMessage());
//            logger.info("------------------");
//            logger.info(e.toString());
//            if (retry == 3) {
//                return;
//            }
//            retry++;
//            try {
//                Thread.sleep(6000);
//            } catch (InterruptedException e1) {
//                e1.printStackTrace();
//            }
//            syncSecuritiesCode();
        }
    }


    @Scheduled(cron = "* 30 * * * ?")
    public void test() {
        logger.info("定时任务执行中");
    }


    public static void main(String[] args) {
    }

}
