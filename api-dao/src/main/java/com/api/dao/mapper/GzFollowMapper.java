package com.api.dao.mapper;

import com.api.domain.bean.GzFollow;

import java.util.List;

public interface GzFollowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GzFollow record);

    int insertSelective(GzFollow record);

    GzFollow selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GzFollow record);

    int updateByPrimaryKey(GzFollow record);

    int insertList(List<GzFollow> gzFollowList);
}