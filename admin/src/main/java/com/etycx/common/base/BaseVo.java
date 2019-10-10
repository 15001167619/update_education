package com.etycx.common.base;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 武海升
 * @description BaseVo
 * @date 2019/5/14 18:46
 */
@Data
public class BaseVo {

    private Object data = false;
    private Integer error = 5000;
    private String message = "服务器异常";

    public BaseVo ok(Object data, String message) {
        this.data = data;
        this.error = 0;
        this.message = message;
        return this;
    }
    public BaseVo ok(String message) {
        this.data = true;
        this.error = 0;
        this.message = message;
        return this;
    }
    public BaseVo error(String message) {
        this.data = false;
        this.error = 5000;
        this.message = message;
        return this;
    }
    public BaseVo error(Integer error, String message) {
        this.data = false;
        this.error = error;
        this.message = message;
        return this;
    }

    public BaseVo error(Object data , Integer error, String message) {
        this.data = data;
        this.error = error;
        this.message = message;
        return this;
    }
    public BaseVo isNullError() {
        this.data = false;
        this.error = -1;
        this.message = "缺少参数";
        return this;
    }

    public BaseVo isErrorSignData() {
        this.data = false;
        this.error = -2;
        this.message = "参数签名错误";
        return this;
    }



    public  <T> Map<String, Object> findDataMapPage(PageInfo<T> page, List<Map<String, Object>> resultMap) {
        Map<String, Object> map = new LinkedHashMap<>(5);
        //总记录数
        map.put("total", page.getTotal());
        //当前页
        map.put("currentPage", page.getPrePage()+1);
        //总页码数
        map.put("pages", page.getPages());
        //每页记录数
        map.put("size", page.getSize());
        //返回记录内容
        map.put("list", resultMap);
        return map;
    }



}
