package com.api.job;

import com.api.domain.share.Share;
import com.api.domain.snowResult.SnowResult;
import com.api.service.shareServcie.ShareService;
import com.api.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 李显琪 on 2019/3/23.
 */

@Component
public class quartzJob {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ShareService shareService;

    /**
     * 定时获取同步证券代码
     * */
    @Scheduled(cron = "30 4 16 * * ?")
    public void syncSecuritiesCode(){
        String url = "https://xueqiu.com/service/v5/stock/screener/quote/list?size=5000&order=desc&orderby=percent&order_by=percent&market=CN&type=sh_sz&_=1564279775146&page=1";
        Map<String,Object> param =  new HashMap<>();
        HttpHeaders header = new HttpHeaders();
        SnowResult snowResult = restTemplate.getForObject(url, SnowResult.class, param);
        List<Share> list = snowResult.getData().getList();
        if(!checkExist(list.get(0)) && !checkExist(list.get(1))){ //休市
            return ;
        }

        Date time = DateUtil.getDay();
        list.forEach(share -> share.setDate(time));
        shareService.batchInsert(list);
    }

    private Boolean checkExist(Share share) {
        List<Share> records = shareService.getShareByRecord(share);
        return records.isEmpty();
    }


}
