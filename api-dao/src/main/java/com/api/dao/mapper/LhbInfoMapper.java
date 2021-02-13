package com.api.dao.mapper;


import com.api.domain.LhbInfo;

public interface LhbInfoMapper {
    int insert(LhbInfo record);

    int insertSelective(LhbInfo record);
}