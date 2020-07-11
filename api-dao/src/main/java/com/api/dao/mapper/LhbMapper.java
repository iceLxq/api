package com.api.dao.mapper;


import com.api.domain.Lhb;

import java.util.List;

public interface LhbMapper {
    int insert(Lhb record);

    int insertSelective(Lhb record);

    void batchInsert(List<Lhb> list);
}