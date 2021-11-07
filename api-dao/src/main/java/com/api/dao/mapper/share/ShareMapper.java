package com.api.dao.mapper.share;


import com.api.domain.share.Share;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface ShareMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Share record);

    int insertSelective(Share record);

    Share selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Share record);

    int updateByPrimaryKey(Share record);

    void batchInsert(List<Share> list);

    List<Share> getShareByRecord(Share record);

    Share getMaxId();

    List<Share> getShareByDate(Date date);

    /**
     * 获取limitday 中symbol 的List集合
     * @param symbol 股票代码
     * @param limitDay 限制时间
     */
    List<Share> getShareLimitByDate(@Param("symbol") String symbol, @Param("limitDay") int limitDay);

    Double getMaCurrentTotal(@Param("symbol") String symbol, @Param("ma") int ma);

    /**
     * 获取limit 的开始时间
     * @param limitDay 限制时间
     * @param symbol 股票代码
     */
    Share getDateBeginRecord(@Param("limitDay") int limitDay, @Param("symbol") String symbol);

    List<Share> getShareListByDate(@Param("date") Date date);

    /**
     * 获取limit时间内平均 日增长
     * 排除st, 688， 前100
     * @param limitDay 限制时间
     * @param date 开始时间
     */
    List<Share> getSharePercentDayIncr(@Param("limitDay") int limitDay, @Param("date") Date date);


    /**
     * 计算symbol limitday的平均价格
     * @param limitDay 限制时间
     * @param symbol 股票代码
     */
    Double calculateAvgPrice(@Param("limitDay") int limitDay, @Param("symbol") String symbol);

    /**
     * 获取lastday的shareList
     */
    List<Share> getLastShareList();

    /**
     * 计算limitday的平均成交量
     * @param limitDay 限制时间
     * @param symbol 股票代码
     */
    Long calculateAvgAmount(@Param("limitDay") int limitDay, @Param("symbol") String symbol);

    /**
     * 计算limitday的最低价格
     * @param limitDay 限制时间
     * @param symbol 股票代码
     */
    Share getLowCurrentLimitDay(@Param("limitDay") int limitDay, @Param("symbol") String symbol);


    /**
     * 计算limitday的最高价格
     * @param limitDay 限制时间
     * @param symbol 股票代码
     */
    Share getHighCurrentLimitDay(@Param("limitDay") int limitDay, @Param("symbol") String symbol);

    /**
     * 根据symbol dateList 获取shareList
     * @param symbol 股票代码
     * @param dateList 时间String集合
     */
    List<Share> getShareListByDateList(@Param("symbol") String symbol, @Param("list") List<String> dateList);

    /**
     * 根据symbol dateList 获取shareList
     * @param percent 增长点
     * @param dateBegin 开始时间
     */
    List<Share> getShareByPercent(@Param("percent") Double percent, @Param("dateBegin")Date dateBegin);


    /**
     * 计算时间段内的最低价格
     * @param dateBegin 开始时间
     * @param dateEnd 结束时间
     * @param symbol 股票代码
     */
    Share getLowCurrentByDate(@Param("dateBegin")Date dateBegin, @Param("dateEnd")Date dateEnd, @Param("symbol")String symbol);
}