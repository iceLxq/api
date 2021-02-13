package com.api.service;

import com.api.domain.snowResult.SymbolLHResponse;
import com.api.domain.snowResult.SymbolLHResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 李显琪 on 2020/7/5.
 */
@Component
public class ProxyService {
    @Autowired
    private RestTemplate restTemplate;


    public void getSymbolLH(String symbol) {
        String url = String.format("https://stock.xueqiu.com/v5/stock/capital/longhu.json?symbol=%s&page=1&size=1", symbol);
        Map<String, Object> param = new HashMap<>();
        SymbolLHResponse result = restTemplate.getForObject(url, SymbolLHResponse.class, param);
        List<SymbolLHResult> data =  result.getBranches();
        System.out.println(data);
    }
}
