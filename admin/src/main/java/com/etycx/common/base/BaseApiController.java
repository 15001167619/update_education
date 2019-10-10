package com.etycx.common.base;

import com.etycx.common.utils.AesUtil;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 武海升
 * @description 基础controller
 * @date 2019/5/14 18:45
 */

public class BaseApiController {

    protected BaseVo isNullData(){
        BaseVo baseVo = new BaseVo();
        return baseVo.isNullError();
    }

    protected BaseVo isErrorSignData(){
        BaseVo baseVo = new BaseVo();
        return baseVo.isErrorSignData();
    }

    protected Map<String, String> getDataParamsMap(String dataParams){
        dataParams = AesUtil.aesDecrypt(dataParams);
        Map<String, String> dataParamsMap = Stream.of(dataParams.split("&"))
                .map(str -> str.split("="))
                .collect(Collectors.toMap(s -> s[0], s -> s[1]));
        return dataParamsMap;
    }



}
