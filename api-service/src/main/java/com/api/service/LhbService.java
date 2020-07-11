package com.api.service;

import com.api.dao.mapper.LhbMapper;
import com.api.domain.Lhb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 李显琪 on 2020/7/5.
 */
@Component
public class LhbService {

    @Autowired
    private LhbMapper lhbMapper;


    public void batchInsert(List<Lhb> list) {
        lhbMapper.batchInsert(list);
    }
}
