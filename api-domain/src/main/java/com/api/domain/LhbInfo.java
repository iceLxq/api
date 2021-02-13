package com.api.domain;


/**
 * Created by 李显琪 on 2020/7/25.
 */
public class LhbInfo {
    private Integer seq;
    private Long tdDate;
    private Integer rankType; //1:买入榜 2：卖出榜
    private Double transAmt; //总成交额
    private String infoTypeName; //上榜理由
    private Double buyTotal; //总计买入  单位W
    private Double sellTotal; //总计卖出  单位W
    private Double chg; //振幅
    private Double netBuy; //龙虎榜净额
    private Double ratioTotal; //买入 /卖出占比
    private Long totalIn; //总计买入
    private Long totalOut; //总计卖出
    private String codeTaglet;

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Long getTdDate() {
        return tdDate;
    }

    public void setTdDate(Long tdDate) {
        this.tdDate = tdDate;
    }

    public Integer getRankType() {
        return rankType;
    }

    public void setRankType(Integer rankType) {
        this.rankType = rankType;
    }

    public Double getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(Double transAmt) {
        this.transAmt = transAmt;
    }

    public String getInfoTypeName() {
        return infoTypeName;
    }

    public void setInfoTypeName(String infoTypeName) {
        this.infoTypeName = infoTypeName;
    }

    public Double getBuyTotal() {
        return buyTotal;
    }

    public void setBuyTotal(Double buyTotal) {
        this.buyTotal = buyTotal;
    }

    public Double getSellTotal() {
        return sellTotal;
    }

    public void setSellTotal(Double sellTotal) {
        this.sellTotal = sellTotal;
    }

    public Double getChg() {
        return chg;
    }

    public void setChg(Double chg) {
        this.chg = chg;
    }

    public Double getNetBuy() {
        return netBuy;
    }

    public void setNetBuy(Double netBuy) {
        this.netBuy = netBuy;
    }

    public Double getRatioTotal() {
        return ratioTotal;
    }

    public void setRatioTotal(Double ratioTotal) {
        this.ratioTotal = ratioTotal;
    }

    public Long getTotalIn() {
        return totalIn;
    }

    public void setTotalIn(Long totalIn) {
        this.totalIn = totalIn;
    }

    public Long getTotalOut() {
        return totalOut;
    }

    public void setTotalOut(Long totalOut) {
        this.totalOut = totalOut;
    }

    public String getCodeTaglet() {
        return codeTaglet;
    }

    public void setCodeTaglet(String codeTaglet) {
        this.codeTaglet = codeTaglet;
    }
}
