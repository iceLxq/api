package com.api.util;

import com.api.domain.ResultResponse;
import com.api.domain.constant.Constant;

/**
 * Created by 李显琪 on 2021/2/13.
 */
public class ResponseUtil {

    public static ResultResponse getSuccessWebResponse() {
        return new ResultResponse("00", Constant.SUCCESS, null);
    }

    public static ResultResponse getErrorWebResponse(String errorMsg) {
        return new ResultResponse("01", errorMsg, null);
    }
}
