package com.etycx.rest.auth.security.impl;

import com.etycx.rest.auth.security.DataSecurityAction;
import org.springframework.util.Base64Utils;

public class Base64SecurityActionImpl implements DataSecurityAction {

    @Override
    public String doAction(String beProtected) {
        return Base64Utils.encodeToString(beProtected.getBytes());
    }

    @Override
    public String unlock(String securityCode) {
        byte[] bytes = Base64Utils.decodeFromString(securityCode);
        return new String(bytes);
    }

}
