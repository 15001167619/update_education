package com.etycx.rest.auth.validator;

public interface IReqValidator {
    /**
     * 获取请求参数验证
     */
    boolean validate(String credenceUnique);

}
