package com.etycx.rest.auth.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.etycx.common.annotation.Security;
import com.etycx.common.utils.Md5Utils;
import com.etycx.framework.cache.RedisUtil;
import com.etycx.framework.wrapper.RequestWrapper;
import com.etycx.rest.auth.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @author  武海升
 */
@Slf4j
public class UrlInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private RedisUtil redis;

    final String TOKEN_PATH = "/api/auth/getToken";
    final String AUTH_PATH = "/api/auth";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        String requestMethod = request.getRequestURI();
        if(requestMethod==null){
            return false;
        }
        if(TOKEN_PATH.equals(requestMethod)){
            return true;
        }
        JSONObject object = new JSONObject();
        PrintWriter writer;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html; charset=utf-8");
        if(requestMethod.contains(AUTH_PATH)){
            String token = request.getHeader("token");
            String sign = request.getHeader("sign");
            String credenceUnique = request.getHeader("credenceUnique");
            if(StringUtils.isBlank(token) || StringUtils.isBlank(sign) || StringUtils.isBlank(credenceUnique)){
                writer = httpServletResponse.getWriter();
                return isNullParams(writer, object);
            }else {
                boolean flag = jwtTokenUtil.isTokenExpired(token);
                if(flag){
                    writer = httpServletResponse.getWriter();
                    return isTokenOrSignExpired(writer,object);
                }
                String redisValueByKey = (String) redis.get("token_sign_" + credenceUnique);
                if(StringUtils.isBlank(redisValueByKey) || !sign.equals(redisValueByKey)){
                    writer = httpServletResponse.getWriter();
                    return isTokenOrSignExpired(writer,object);
                }
            }

            if (handler instanceof HandlerMethod) {
                Method method = ((HandlerMethod) handler).getMethod();
                if (AnnotatedElementUtils.isAnnotated(method, Security.class)) {
                    Security security = method.getAnnotation(Security.class);
                    if (security == null) {
                        return true;
                    }
                    String appKey = security.appKey();
                    String interfaceName = security.interfaceName();
                    long validTime = security.validTime();
                    System.out.println("appKey=" + appKey + "interfaceName=" + interfaceName + "validTime=" + validTime);
                    RequestWrapper requestWrapper = new RequestWrapper(request);
                    String body = requestWrapper.getBody();
                    try {
                        JSONObject requestParams = JSONObject.parseObject(body);
                        String requestApiSign = requestParams.getString("apiSign");
                        String requestAppKey = requestParams.getString("appKey");
                        String requestDataParams = requestParams.getString("dataParams");
                        String requestInterfaceName = requestParams.getString("interfaceName");
                        long requestTimestamp = requestParams.getLong("timestamp");
                        //接口有效访问时间
//                        if(System.currentTimeMillis() - requestTimestamp > validTime){
//                            writer = httpServletResponse.getWriter();
//                            return isErrorSignData(writer, object);
//                        }
                        if( StringUtils.isBlank(requestAppKey) || StringUtils.isBlank(requestDataParams)
                                || StringUtils.isBlank(requestApiSign) ){
                            writer = httpServletResponse.getWriter();
                            return isNullParams(writer, object);
                        }
                        String currentSign = Md5Utils.hash("appKey=" + appKey
                                + "#interfaceName=" + requestInterfaceName
                                + "#timestamp=" + requestTimestamp
                                + "#dataParams=" + requestDataParams);
                        if(!requestApiSign.equals(currentSign)){
                            writer = httpServletResponse.getWriter();
                            return isErrorSignData(writer, object);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return true;
    }

    /**
     * @author 武海升
     * @description 校验参数是否正确
     */
    private boolean isErrorSignData(PrintWriter writer, JSONObject object) {
        try {
            object.put("error",10002);
            object.put("message","参数签名错误");
            writer.print(object);
        } catch (Exception e) {
            log.error("response error",e);
        } finally {
            if (writer != null){
                writer.close();
            }
        }
        return false;
    }
    /**
     * @author 武海升
     * @description 校验参数是否为空
     */
    private boolean isNullParams(PrintWriter writer,JSONObject object){
        try {
            object.put("error",10000);
            object.put("message","请求头参数错误");
            writer.print(object);
        } catch (Exception e) {
            log.error("response error",e);
        } finally {
            if (writer != null){
                writer.close();
            }
        }
        return false;
    }

    /**
     * @author 武海升
     * @description 校验Token、签名是否过期
     */
    private boolean isTokenOrSignExpired(PrintWriter writer,JSONObject object){
        try {
            object.put("error",10001);
            object.put("message","token 或 sign 已过期");
            writer.print(object);
        } catch (Exception e) {
            log.error("response error",e);
        } finally {
            if (writer != null){
                writer.close();
            }
        }
        return false;

    }
}
