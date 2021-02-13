package com.api.util;

/**
 * Created by 李显琪 on 2021/1/9.
 */
public class ShareUtil {


//    public static String addShareSymbolPre(String symbol) {
//
//    }

    public static void main(String[] args) {
        String preCode = SharePreEnum.getSymbol("600123");
        System.out.println(preCode);
    }
}
