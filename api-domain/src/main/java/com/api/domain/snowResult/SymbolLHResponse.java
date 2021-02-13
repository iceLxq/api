package com.api.domain.snowResult;

import java.util.List;

/**
 * Created by 李显琪 on 2020/7/11.
 */
public class SymbolLHResponse {
    private int seq;
    private Long tdDate;
    private int rankType;
    private String infoTypeName;
    private Double transAmt;
    private List<SymbolLHResult> branches;
    private Double buyTotal;
    private Double sellTotal;
    private Double chg;
    private Double netBuy;
    private Double ratioTotal;
    private Double totalIn;
    private Double totalOut;
    private int codeTag;


    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public Long getTdDate() {
        return tdDate;
    }

    public void setTdDate(Long tdDate) {
        this.tdDate = tdDate;
    }

    public int getRankType() {
        return rankType;
    }

    public void setRankType(int rankType) {
        this.rankType = rankType;
    }

    public String getInfoTypeName() {
        return infoTypeName;
    }

    public void setInfoTypeName(String infoTypeName) {
        this.infoTypeName = infoTypeName;
    }

    public Double getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(Double transAmt) {
        this.transAmt = transAmt;
    }

    public List<SymbolLHResult> getBranches() {
        return branches;
    }

    public void setBranches(List<SymbolLHResult> branches) {
        this.branches = branches;
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

    public Double getTotalIn() {
        return totalIn;
    }

    public void setTotalIn(Double totalIn) {
        this.totalIn = totalIn;
    }

    public Double getTotalOut() {
        return totalOut;
    }

    public void setTotalOut(Double totalOut) {
        this.totalOut = totalOut;
    }

    public int getCodeTag() {
        return codeTag;
    }

    public void setCodeTag(int codeTag) {
        this.codeTag = codeTag;
    }
}
