package com.api.dao.mapper;


import com.api.domain.ShareCategoryRank;

public interface ShareCategoryRankMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShareCategoryRank record);

    int insertSelective(ShareCategoryRank record);

    ShareCategoryRank selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShareCategoryRank record);

    int updateByPrimaryKey(ShareCategoryRank record);
}