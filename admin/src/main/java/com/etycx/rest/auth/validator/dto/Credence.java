package com.etycx.rest.auth.validator.dto;

public interface  Credence {

    /**
     * 唯一凭证
     */
    String getCredenceUniqueName();

    /**
     * 密码或者是其他的验证码之类的
     */
    String getCredenceCode();

}
