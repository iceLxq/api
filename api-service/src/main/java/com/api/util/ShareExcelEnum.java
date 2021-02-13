package com.api.util;

/**
 * Created by 李显琪 on 2021/2/12.
 */
public enum ShareExcelEnum {
    SYMBOL("代码", "symbol"),
    NAME("名称", "name"),
    INDUSTRY("细分行业", "industry");

    private String cellName;
    private String code;

    ShareExcelEnum(String cellName, String code) {
        this.cellName = cellName;
        this.code = code;
    }

    public static String getCode(String cellName) {
        ShareExcelEnum[] shareExcelEnum = ShareExcelEnum.values();
        for (ShareExcelEnum share : shareExcelEnum) {
            if (share.cellName.equals(cellName)) {
                return share.code;
            }
        }
        return null;
    }


}
