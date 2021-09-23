package com.api.snow.constant;

import com.api.util.ShareExcelEnum;

/**
 * Created by 李显琪 on 2021/9/11.
 */
public enum LoginEnum {

    SNOW("snowLogin","https://xueqiu.com/snowman/account/login");

    private String name;

    private String url;

    LoginEnum(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public static LoginEnum getLoginEnum(String name) {
        LoginEnum[] loginEnum = LoginEnum.values();
        for (LoginEnum login : loginEnum) {
            if (login.name.equals(name)) {
                return login;
            }
        }
        return null;
    }

}
