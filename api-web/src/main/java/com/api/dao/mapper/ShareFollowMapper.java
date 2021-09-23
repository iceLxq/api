package com.api.dao.mapper;

import com.api.dao.mapper.ShareFollow;

public interface ShareFollowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShareFollow record);

    int insertSelective(ShareFollow record);

    ShareFollow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShareFollow record);

    int updateByPrimaryKey(ShareFollow record);
}