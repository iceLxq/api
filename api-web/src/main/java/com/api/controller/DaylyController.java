package com.api.controller;

import com.api.domain.bean.Dayfollow;
import com.api.service.DayfollowService;
import com.api.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 李显琪 on 2020/2/3.
 */
@RestController
@RequestMapping("/dayfollow")
public class DaylyController {

    @Autowired
    private DayfollowService dayfollowService;

    @GetMapping("/daylyfollow")
    public List<Dayfollow> getDayfollowInfo(){
        return dayfollowService.getDayRecord(DateUtil.getDay());
    }

}
