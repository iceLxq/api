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

    List<Share> getShareLimitByDate(@Param("symbol") String symbol, @Param("limitDay") int limitDay);

    Double getMaCurrentTotal(@Param("symbol") String symbol, @Param("ma") int ma);

    Share getDateBeginRecord(@Param("limitDay") int limitDay, @Param("symbol") String symbol);

    List<Share> getShareListByDate(@Param("date") Date date);

    List<Share> getSharePercentDayIncr(@Param("limitDay") int limitDay, @Param("date") Date date);

    Double calculateAvgPrice(@Param("limitDay") int limitDay, @Param("symbol") String symbol);

    List<Share> getLastShareList();

    Long calculateAvgAmount(@Param("limitDay") int limitDay, @Param("symbol") String symbol);
}