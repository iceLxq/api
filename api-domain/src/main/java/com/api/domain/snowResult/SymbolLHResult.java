package com.api.domain.snowResult;


/**
 * Created by 李显琪 on 2020/7/11.
 */
public class SymbolLHResult {
    private String branchId;
    private String branceName;
    private Double buyAmt;
    private Double sellAmt;
    private Double ratio;  //买入占比
    private Double netAmt; //净额

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranceName() {
        return branceName;
    }

    public void setBranceName(String branceName) {
        this.branceName = branceName;
    }

    public Double getBuyAmt() {
        return buyAmt;
    }

    public void setBuyAmt(Double buyAmt) {
        this.buyAmt = buyAmt;
    }

    public Double getSellAmt() {
        return sellAmt;
    }

    public void setSellAmt(Double sellAmt) {
        this.sellAmt = sellAmt;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public Double getNetAmt() {
        return netAmt;
    }

    public void setNetAmt(Double netAmt) {
        this.netAmt = netAmt;
    }
}
