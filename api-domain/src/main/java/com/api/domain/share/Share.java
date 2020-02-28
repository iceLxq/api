package com.api.domain.share;

import java.util.Date;

public class Share {
    private Integer id;

    private String name;

    private String symbol;

    private Double current;

    private Double startPrice;

    private Double percent;

    private Long volume;

    private Long amount;

    private Double turnoverRate;

    private Double marketCapital;

    private Date date;

    private Double turnover_rate;

    private Double market_capital;

    public Double getTurnover_rate() {
        return turnover_rate;
    }

    public void setTurnover_rate(Double turnover_rate) {
        this.turnover_rate = turnover_rate;
        this.turnoverRate =turnover_rate;
    }

    public Double getMarket_capital() {
        return market_capital;
    }

    public void setMarket_capital(Double market_capital) {
        this.market_capital = market_capital;
        this.marketCapital = market_capital;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSymbol() {
        if (symbol.startsWith("SZ") || symbol.startsWith("SH")){
            symbol = symbol.substring(2);
        }
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol == null ? null : symbol.trim();
    }

    public Double getCurrent() {
        return current;
    }

    public void setCurrent(Double current) {
        this.current = current;
    }

    public Double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Double startPrice) {
        this.startPrice = startPrice;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Double getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(Double turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public Double getMarketCapital() {
        return marketCapital;
    }

    public void setMarketCapital(Double marketCapital) {
        this.marketCapital = marketCapital;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}