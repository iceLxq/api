package com.api.dao.mapper.securityCode;

import com.api.domain.securityCode.SecurityCode;

public interface SecurityCodeMapper {

    int deleteByPrimaryKey(String uuid);

    int insert(SecurityCode record);

    int insertSelective(SecurityCode record);

    SecurityCode selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(SecurityCode record);

    int updateByPrimaryKey(SecurityCode record);
}