package com.etycx.remote.service.impl;

import com.etycx.common.base.BaseVo;
import com.etycx.common.utils.AesUtil;
import com.etycx.remote.service.IExampleService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 武海升
 * @description 样例展示
 * @date 2019/6/14 11:35
 */
@Service
public class ExampleServiceImpl implements IExampleService {

    private final BaseVo baseVo = new BaseVo();

    @Override
    public BaseVo exampleAdd(Map<String, String> dataParams) {
        System.out.println(dataParams);

        String strData = "{\n" +
                "    \"data\": {\n" +
                "        \"total\": 3,\n" +
                "        \"currentPage\": 1,\n" +
                "        \"pages\": 1,\n" +
                "        \"size\": 3,\n" +
                "        \"list\": [\n" +
                "            {\n" +
                "                \"bodySignType\": \"血压\",\n" +
                "                \"bodySignTypeId\": 1,\n" +
                "                \"id\": 1,\n" +
                "                \"openId\": \"oJbdW4wcbdbnrk3ayQCk8lN-GrS8\",\n" +
                "                \"value\": \"120/60\",\n" +
                "                \"status\": 1,\n" +
                "                \"createTime\": \"2019-05-29 17:02:06\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"bodySignType\": \"血压\",\n" +
                "                \"bodySignTypeId\": 1,\n" +
                "                \"id\": 4,\n" +
                "                \"openId\": \"oJbdW4wcbdbnrk3ayQCk8lN-GrS8\",\n" +
                "                \"value\": \"120/70\",\n" +
                "                \"status\": 1,\n" +
                "                \"createTime\": \"2019-05-29 18:02:15\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"bodySignType\": \"血压\",\n" +
                "                \"bodySignTypeId\": 1,\n" +
                "                \"id\": 7,\n" +
                "                \"openId\": \"oJbdW4wcbdbnrk3ayQCk8lN-GrS8\",\n" +
                "                \"value\": \"130/70\",\n" +
                "                \"status\": 1,\n" +
                "                \"createTime\": \"2019-06-10 10:27:05\"\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"error\": 0,\n" +
                "    \"message\": \"获取医院列表成功\"\n" +
                "}";

        return baseVo.ok(AesUtil.aesEncrypt(strData),"获取样例展示详情成功");
    }
}
