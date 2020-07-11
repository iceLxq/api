package com.api.domain.snowResult;

import com.api.domain.share.Share;

import java.util.List;

/**
 * Created by 李显琪 on 2019/7/28.
 */
public class ShareResponse {
    private int count;

    private List<Share> list;


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Share> getList() {
        return list;
    }

    public void setList(List<Share> list) {
        this.list = list;
    }

}
