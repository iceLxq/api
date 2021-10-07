package com.api.dao.mapper;


import com.api.domain.ShareFollow;

import java.util.List;

public interface ShareFollowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShareFollow record);

    int insertSelective(ShareFollow record);

    ShareFollow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShareFollow record);

    int updateByPrimaryKey(ShareFollow record);

    List<ShareFollow> getAllRecord();

}