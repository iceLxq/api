package com.api.util;

/**
 * Created by 李显琪 on 2021/1/9.
 */
public enum SharePreEnum {
    SH("60", "SH"),
    SZ("00", "SZ"),
    CYB("300", "SZ"),
    KCB("68", "SH"),;
    private String preNum;
    private String preCode;

    SharePreEnum(String preNum, String preCode) {
        this.preNum = preNum;
        this.preCode = preCode;
    }

    public static String getPreCode(String preNum) {
        SharePreEnum[] sharePreEnums = SharePreEnum.values();
        for (SharePreEnum share : sharePreEnums) {
            if (share.preNum.equals(preNum)) {
                return share.preCode;
            }
        }
        return null;
    }


    public static String getSymbol(String symbol) {
        String preCode = symbol.substring(0, 2);
        return getPreCode(preCode) + symbol;
    }
}
