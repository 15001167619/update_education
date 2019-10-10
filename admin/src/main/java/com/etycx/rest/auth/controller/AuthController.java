package com.etycx.rest.auth.controller;


import com.etycx.framework.cache.RedisUtil;
import com.etycx.framework.config.properties.JwtProperties;
import com.etycx.rest.auth.controller.dto.AuthResponseImpl;
import com.etycx.rest.auth.util.JwtTokenUtil;
import com.etycx.rest.auth.validator.IReqValidator;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 武海升
 */
@RestController
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private RedisUtil redis;
    @Autowired
    private IReqValidator reqValidator;

    /**
     * 获取 Token
     */
    @RequestMapping(value = "api/auth/getToken", method= RequestMethod.GET)
    public ResponseEntity<?> createAuthenticationToken(@ApiParam(required = true, name = "credenceUnique", value = "接口调用者唯一标识") @RequestParam("credenceUnique") String credenceUnique) {
        boolean validate = reqValidator.validate(credenceUnique);
        Map<String,Object> map = new HashMap<>(4);
        map.put("error",1000);
        map.put("message","获取 token 失败 ,手机号校验失败");
        if (validate) {
            final String randomKey = jwtTokenUtil.getRandomKey();
            final String token = jwtTokenUtil.generateToken(credenceUnique, randomKey);
            final String sign = jwtTokenUtil.generateSign(credenceUnique, randomKey);
            redis.set("token_sign_"+credenceUnique,sign,jwtProperties.getExpiration());
            map.put("error",0);
            map.put("message","获取 token 成功");
            map.put("data",new AuthResponseImpl(token, sign));
            return ResponseEntity.ok(map);
        } else {
            return ResponseEntity.ok(map);
        }
    }


}
