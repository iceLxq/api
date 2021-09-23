package com.api.dao.mapper;

public class ShareFollow {
    private Integer id;

    private String symbol;

    private String name;

    private Double three;

    private Double five;

    private Double ten;

    private Double thirteen;

    private Double twenty;

    private Boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol == null ? null : symbol.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Double getThree() {
        return three;
    }

    public void setThree(Double three) {
        this.three = three;
    }

    public Double getFive() {
        return five;
    }

    public void setFive(Double five) {
        this.five = five;
    }

    public Double getTen() {
        return ten;
    }

    public void setTen(Double ten) {
        this.ten = ten;
    }

    public Double getThirteen() {
        return thirteen;
    }

    public void setThirteen(Double thirteen) {
        this.thirteen = thirteen;
    }

    public Double getTwenty() {
        return twenty;
    }

    public void setTwenty(Double twenty) {
        this.twenty = twenty;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}