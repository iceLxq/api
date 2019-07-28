package com.api.service.shareServcie;

import com.api.dao.mapper.share.ShareMapper;
import com.api.domain.share.Share;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by 李显琪 on 2019/7/28.
 */
@Component
public class ShareService {
    @Autowired
    private ShareMapper shareMapper;

    public void batchInsert(List<Share> list) {
        shareMapper.batchInsert(list);
    }

    public List<Share> getShareByRecord(Share record) {
       return  shareMapper.getShareByRecord(record);
    }
}
