package com.api.dao.mapper;


import com.api.domain.LhbBranch;

public interface LhbBranchMapper {
    int insert(LhbBranch record);

    int insertSelective(LhbBranch record);
}