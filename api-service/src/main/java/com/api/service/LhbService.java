package com.api.service;

import com.api.dao.mapper.LhbMapper;
import com.api.domain.Lhb;
import com.api.domain.snowResult.LhbResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 李显琪 on 2020/7/5.
 */
@Component
public class LhbService {

    @Autowired
    private LhbMapper lhbMapper;
    @Autowired
    private ProxyService proxyService;


    public void batchInsert(List<Lhb> list) {
        lhbMapper.batchInsert(list);
    }

    private void count(String symbol, Date date) {
        String url = String.format("https://stock.xueqiu.com/v5/stock/capital/longhu.json?symbol=%s&page=1&size=1", date);
        proxyService.getSymbolLH(symbol);

    }
}
