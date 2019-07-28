package com.api.domain.snowResult;

import com.api.domain.share.Share;

import java.util.List;

/**
 * Created by 李显琪 on 2019/7/28.
 */
public class SnowResult {

    private DataResult data;

    private String error_code;

    private String error_description;

    public DataResult getData() {
        return data;
    }

    public void setData(DataResult data) {
        this.data = data;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_description() {
        return error_description;
    }

    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

}


