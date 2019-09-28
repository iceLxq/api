package com.api.service.shareServcie;

import com.api.dao.mapper.share.ShareMapper;
import com.api.domain.share.Share;
import com.api.domain.snowResult.SnowResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by 李显琪 on 2019/7/28.
 */
@Component
public class ShareService {
    @Autowired
    private ShareMapper shareMapper;

    @Autowired
    private RestTemplate restTemplate;

    public void batchInsert(List<Share> list) {
        shareMapper.batchInsert(list);
    }

    public List<Share> getShareByRecord(Share record) {
       return  shareMapper.getShareByRecord(record);
    }

    public Map daySelect(){
        String url = "https://xueqiu.com/service/v5/stock/screener/quote/list?size=5000&order=desc&orderby=percent&order_by=percent&market=CN&type=sh_sz&_=1564279775146&page=1";
        Map<String,Object> param =  new HashMap<>();
        SnowResult snowResult = restTemplate.getForObject(url, SnowResult.class, param);
        List<Share> list = snowResult.getData().getList();
        List<Share> collect = list.stream().filter(share -> share.getPercent() > 0.1).collect(Collectors.toList());
        return null;
    }

}
