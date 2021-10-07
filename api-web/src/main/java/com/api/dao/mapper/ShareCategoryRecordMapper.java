package com.api.dao.mapper;

import com.api.dao.mapper.ShareCategoryRecord;

public interface ShareCategoryRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShareCategoryRecord record);

    int insertSelective(ShareCategoryRecord record);

    ShareCategoryRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShareCategoryRecord record);

    int updateByPrimaryKey(ShareCategoryRecord record);
}