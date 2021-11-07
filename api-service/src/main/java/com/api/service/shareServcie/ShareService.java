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


    /**
     * 获取limitday 中symbol 的List集合
     * @param symbol 股票代码
     * @param limitDay 限制时间
     */
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
     * 获取last 第2天的数据
     * @param symbol 股票代码
     */
    public Share getLastdayShare(String symbol) {
        List<Share> shareList = shareMapper.getShareLimitByDate(symbol, 2);
        if (!shareList.isEmpty() && shareList.size() == 2) {
            return shareList.get(1);
        }
        logger.info("can not get shareList by symbol = {}", symbol);
        return null;
    }

    /**
     * 获取limit 的开始时间
     * @param limitDay 限制时间
     * @param symbol 股票代码
     */
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



    /**
     * 获取limit时间内平均 日增长
     * 排除st, 688， 前100
     * @param limitDay 限制时间
     * @param symbol 股票代码
     */
    public List<Share> getSharePercentDayIncr(int limitDay, String symbol) {
        if (null == symbol) {
            symbol = "002741";
        }
        List<Share> shareLimitByDate = shareMapper.getShareLimitByDate(symbol, limitDay + 1);
        if (shareLimitByDate.isEmpty()) {
            return null;
        }
        Date beginDay = shareLimitByDate.get(limitDay).getDate();
        return shareMapper.getSharePercentDayIncr(limitDay, beginDay);
    }


    /**
     * 计算symbol limitday的平均价格
     * @param limitDay 限制时间
     * @param symbol 股票代码
     */
    public Double calculateAvgPrice(int limitDay, String symbol) {
        Double price = shareMapper.calculateAvgPrice(limitDay, symbol);
        return Double.parseDouble(new java.text.DecimalFormat("#.00").format(price));
    }

    /**
     * 获取lastday的shareList
     */
    public List<Share> getLastShareList() {
        return shareMapper.getLastShareList();
    }

    /**
     * 计算limitday的平均成交量
     * @param limitDay 限制时间
     * @param symbol 股票代码
     */
    public Long calculateAvgAmount(int limitDay, String symbol) {
        return shareMapper.calculateAvgAmount(limitDay, symbol);
    }

    /**
     * 计算limitday的最低价格
     * @param limitDay 限制时间
     * @param symbol 股票代码
     */
    public Share getLowCurrentLimitDay(int limitDay, String symbol) {
        return shareMapper.getLowCurrentLimitDay(limitDay, symbol);
    }

    /**
     * 计算时间段内的最低价格
     * @param dateBegin 开始时间
     * @param dateEnd 结束时间
     * @param symbol 股票代码
     */
    public Share getLowCurrentByDate(Date dateBegin, Date dateEnd, String symbol) {
        return shareMapper.getLowCurrentByDate(dateBegin, dateEnd, symbol);
    }
    /**
     * 计算limitday的最高价格
     * @param limitDay 限制时间
     * @param symbol 股票代码
     */
    public Share getHighCurrentLimitDay(int limitDay, String symbol) {
        return shareMapper.getHighCurrentLimitDay(limitDay, symbol);
    }

    /**
     * 根据symbol dateList 获取shareList
     * @param symbol 股票代码
     * @param dateList 时间String集合
     */
    public List<Share> getShareListByDateList(String symbol, List<String> dateList) {
        return shareMapper.getShareListByDateList(symbol, dateList);
    }

    /**
     * 根据symbol dateList 获取shareList
     * @param percent 增长点
     * @param dateBegin 开始时间
     */
    public List<Share> getShareByPercent(Double percent, Date dateBegin) {
        return shareMapper.getShareByPercent(percent, dateBegin);
    }


}
