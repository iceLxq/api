package com.api.controller;

import com.api.service.shareServcie.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 李显琪 on 2020/12/20.
 */

@RestController
@RequestMapping("/share")
public class ShareController {

    @Autowired
    private ShareService shareService;

    /**
     * 以当日最低点为5日线 反算收盘价
     */
    @GetMapping("/ma5")
    public double get5DayLine(String symbol, Double ma5) {
        return shareService.getMA5(symbol, ma5);
    }
}
