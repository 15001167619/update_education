package com.etycx.rest.auth.validator.impl;



import com.etycx.rest.auth.validator.IReqValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ReqValidatorImpl implements IReqValidator {



    @Value("${specialValidator.enable}")
    private boolean enableValidator;

    @Override
    public boolean validate(String credenceUnique) {
        if(StringUtils.isBlank(credenceUnique))
            return false;
        //是否开启特殊验证
        if (enableValidator && ("666".equals(credenceUnique))) {
            return true;
        } else {
           /* //HealthooUser userInfo = userMapper.findUserInfo(new HealthooUser(credenceUnique));
            if(userInfo == null){
                return false;
            }*/
            return true;
        }
    }

}
