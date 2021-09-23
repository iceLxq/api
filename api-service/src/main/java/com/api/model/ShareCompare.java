package com.api.model;

/**
 * Created by 李显琪 on 2021/8/22.
 * same symbol compare with lastDay
 *
 */
public class ShareCompare {

    private String symbol;

    private Integer limitDay;

    private Double amount;

    private Integer gtLastDay;


    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getLimitDay() {
        return limitDay;
    }

    public void setLimitDay(Integer limitDay) {
        this.limitDay = limitDay;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
