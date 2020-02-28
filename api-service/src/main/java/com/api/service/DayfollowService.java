package com.api.service;

import com.api.dao.mapper.DayfollowMapper;
import com.api.domain.bean.Dayfollow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by 李显琪 on 2020/2/6.
 */
@Component
public class DayfollowService {

    @Autowired
    private DayfollowMapper dayfollowMapper;

    /**
     * 根据日期获取数据
     */
    public List<Dayfollow> getDayRecord(Date date) {
        return dayfollowMapper.getDayRecord(date);
    }

    /**
     * 批量插入
     */
    public void insert(List<Dayfollow> dayfollowList) {
        if (dayfollowList.isEmpty()){
            return ;
        }
        dayfollowList.stream().forEach(dayfollow -> dayfollowMapper.insert(dayfollow));
    }

    /**
     * 根据代码和是建批量更新数据
     */
    public void updateBySymbolAndDate(List<Dayfollow> dayfollowList) {
        if (dayfollowList.isEmpty()){
            return ;
        }
        dayfollowList.stream().forEach(dayfollow -> this.updateBySymbolAndDate(dayfollow));
    }

    private void updateBySymbolAndDate(Dayfollow dayfollow) {
        dayfollowMapper.updateBySymbolAndDate(dayfollow);
    }

    public List<Dayfollow> getDaylyInfo(Date day) {
        return dayfollowMapper.getDaylyInfo(day);
    }
}
