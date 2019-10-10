package com.etycx.rest.auth.controller.dto;

public class AuthResponseImpl {

    /**
     * jwt token
     */
    private final String token;

    /**
     * 签名
     */
    private final String sign;

    public AuthResponseImpl(String token, String sign) {
        this.token = token;
        this.sign = sign;
    }

    public String getToken() {
        return this.token;
    }

    public String getSign() {
        return sign;
    }

}
