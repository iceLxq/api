package com.api.service.shareServcie;

import com.api.dao.mapper.share.ShareMapper;
import com.api.domain.share.Share;
import com.api.domain.snowResult.SnowResult;
import com.api.service.daylyService.DaylyService;
import com.api.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by 李显琪 on 2019/7/28.
 */
@Component
public class ShareService {

    private static Logger logger = LoggerFactory.getLogger(ShareService.class);

    @Autowired
    private ShareMapper shareMapper;

    @Autowired
    private RestTemplate restTemplate;

    public void batchInsert(List<Share> list) {
        shareMapper.batchInsert(list);
    }

    public List<Share> getShareByRecord(Share record) {
        return shareMapper.getShareByRecord(record);
    }

    public Map daySelect() {
        String url = "https://xueqiu.com/service/v5/stock/screener/quote/list?size=5000&order=desc&orderby=percent&order_by=percent&market=CN&type=sh_sz&_=1564279775146&page=1";
        Map<String, Object> param = new HashMap<>();
        SnowResult snowResult = restTemplate.getForObject(url, SnowResult.class, param);
        List<Share> list = snowResult.getData().getList();
        List<Share> collect = list.stream().filter(share -> share.getPercent() > 0.1).collect(Collectors.toList());
        return null;
    }

    public Share getMaxId() {
        return shareMapper.getMaxId();
    }

    public List<Share> getShareByDate(Date date) {
        return shareMapper.getShareByDate(date);
    }

    public List<Share> getShareLimitByDate(String symbol, int limitDay) {
        return shareMapper.getShareLimitByDate(symbol, limitDay);
    }

    public Double getMA5(String symbol, Double ma) {
        return getHigtPriceDay(symbol, ma, 5);
    }

    /**
     * 获取当天能达到的最高价格
     * (根据不同科创 st等不同属性计算 --待完成)
     */
    public double getHigtPriceDay(String symbol, Double ma, int maDay) {
        Double totalPrice = shareMapper.getMaCurrentTotal(symbol, maDay - 1);
        return (totalPrice + ma) / 5;
    }

    /**
     * 获取上一天的数据
     */
    public Share getLastdayShare(String symbol) {
        List<Share> shareList = shareMapper.getShareLimitByDate(symbol, 2);
        if (!shareList.isEmpty() && shareList.size() == 2) {
            return shareList.get(1);
        }
        logger.info("can not get shareList by symbol = {}", symbol);
        return null;
    }


    public Date getDateBegin(int limitDay, String symbol) {
        if (StringUtils.isEmpty(symbol)) {
            symbol = "002714";
        }
        Share beginRecord = shareMapper.getDateBeginRecord(limitDay, symbol);
        return beginRecord.getDate();
    }


    /**
     * symbol < 6880000
     */
    public List<Share> getShareListByDate(Date date) {
        return shareMapper.getShareListByDate(date);
    }




    public List<Share> getSharePercentDayIncr(int limitDay, String symbol) {
        if (null == symbol) {
            symbol = "002741";
        }
        List<Share> shareLimitByDate = shareMapper.getShareLimitByDate(symbol, limitDay + 1);
        if (shareLimitByDate.isEmpty()){
            return null ;
        }
        Date beginDay = shareLimitByDate.get(limitDay).getDate();
        return shareMapper.getSharePercentDayIncr(limitDay,beginDay);
    }
}
