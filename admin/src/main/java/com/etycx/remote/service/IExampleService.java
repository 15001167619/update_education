package com.etycx.remote.service;

import com.etycx.common.base.BaseVo;

import java.util.Map;
/**
 * @author 武海升
 */
public interface IExampleService {

    /**
     *  样例测试
     * @param dataParams
     * @return
     */
    BaseVo exampleAdd(Map<String, String> dataParams);

}
