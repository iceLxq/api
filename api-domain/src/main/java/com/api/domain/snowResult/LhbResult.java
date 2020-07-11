package com.api.domain.snowResult;

/**
 * Created by 李显琪 on 2020/7/5.
 */
public class LhbResult {
    private LhbResponse data;

    private String error_code;

    private String error_description;

    public LhbResponse getData() {
        return data;
    }

    public void setData(LhbResponse data) {
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
