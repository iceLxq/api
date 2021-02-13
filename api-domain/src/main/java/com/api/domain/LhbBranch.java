package com.api.domain;

/**
 * Created by 李显琪 on 2020/7/25.
 */
public class LhbBranch {
    private Integer seq;
    private String branchId;
    private String branchName;
    private Double buyAmt;  //买入额度
    private Double sellAmt; //卖出额度
    private Double ratio;   //净额
    private Double netAmt;  //占比
    private String branchTag; //标记

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
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

    public String getBranchTag() {
        return branchTag;
    }

    public void setBranchTag(String branchTag) {
        this.branchTag = branchTag;
    }
}
