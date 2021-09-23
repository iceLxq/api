package com.api.service;

import com.api.domain.share.Share;
import com.api.model.ShareCompare;
import com.api.service.shareServcie.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by 李显琪 on 2021/8/22.
 */

@Component
public class RuleService {

    @Autowired
    private ShareService shareService;

    public void comparePrice(int limitDay) {
        Date dateBegin = shareService.getDateBegin(limitDay, null);
        List<Share> shareListByDate = shareService.getShareListByDate(dateBegin);
        Map<String, List<Share>> shareMap = new HashMap<>();
        shareListByDate.forEach(share -> {
            if (shareMap.containsKey(share.getSymbol())) {
                List<Share> shareList = shareMap.get(share.getSymbol());
                shareList.add(share);
            } else {
                List<Share> shareList = new ArrayList<>();
                shareList.add(share);
                shareMap.put(share.getSymbol(), shareList);
            }
        });
        System.out.println(shareMap);
        for (String symbol : shareMap.keySet()) {
            List<Share> shareList = shareMap.get(symbol);
            if (shareList.size() != limitDay) {
                continue;
            }
            ShareCompare shareCompare = new ShareCompare();
            for (int i = 1; i < limitDay; i++) {
                Share currentShare = shareList.get(i);
                Share lastShare = shareList.get(i-1);
            }

        }


    }
}
