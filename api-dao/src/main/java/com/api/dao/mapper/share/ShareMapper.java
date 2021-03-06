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

    List<Share> getShareLimit2ByDate(String symbol);

    Double getMaCurrentTotal(@Param("symbol")String symbol, @Param("ma") int ma);
}