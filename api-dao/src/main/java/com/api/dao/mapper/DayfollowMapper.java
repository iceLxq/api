package com.api.dao.mapper;


import com.api.domain.bean.Dayfollow;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface DayfollowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dayfollow record);

    int insertSelective(Dayfollow record);

    Dayfollow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dayfollow record);

    int updateByPrimaryKey(Dayfollow record);

    List<Dayfollow> getDayRecord(Date date);

    void updateBySymbolAndDate(Dayfollow dayfollow);

    List<Dayfollow> getDaylyInfo(Date day);
}