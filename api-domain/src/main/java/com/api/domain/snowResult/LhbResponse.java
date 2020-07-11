package com.api.domain.snowResult;

import com.api.domain.Lhb;

import java.util.List;

/**
 * Created by 李显琪 on 2020/7/5.
 */
public class LhbResponse {
    private int items_size;

    private List<LhbVo> items;

    public int getItems_size() {
        return items_size;
    }

    public void setItems_size(int items_size) {
        this.items_size = items_size;
    }

    public List<LhbVo> getItems() {
        return items;
    }

    public void setItems(List<LhbVo> items) {
        this.items = items;
    }
}
