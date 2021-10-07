package com.api.proxy;

import com.api.domain.share.Share;
import com.api.domain.snowResult.SnowResult;
import com.api.service.shareServcie.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 李显琪 on 2020/2/4.
 */

@Component
public class XueqiuProxyServe {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ShareService shareService;

    public List<Share> getShareInfo(){
        String url = "https://xueqiu.com/S/SH601728";
        Map<String, Object> param = new HashMap<>();
        SnowResult snowResult = restTemplate.getForObject(url, SnowResult.class, param);
        return null;

    }
    /**
     * 获取当前的share信息
     */
    public List<Share> getShareInfoList(){
        String url = "https://xueqiu.com/service/v5/stock/screener/quote/list?size=5000&order=desc&orderby=percent&order_by=percent&market=CN&type=sh_sz&_=1564279775146&page=1";
        Map<String, Object> param = new HashMap<>();
        SnowResult snowResult = restTemplate.getForObject(url, SnowResult.class, param);
        List<Share> list = snowResult.getData().getList();
        return list;
    }


    /**
     * 检查今日是否休市
     */
    public Boolean checkOpen(List<Share> list){
        if (!checkExist(list.get(0)) && !checkExist(list.get(1))) { //休市
            return false;
        }
        return true;
    }

    private Boolean checkExist(Share share) {
        List<Share> records = shareService.getShareByRecord(share);
        return records.isEmpty();
    }
}
