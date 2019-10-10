package com.etycx.framework.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author 武海升
 * @description JWT 安全配置
 * @date 2019/5/9 9:54
 */
@Component
@Data
@Configuration
@ConfigurationProperties(prefix = JwtProperties.JWT_PREFIX)
public class JwtProperties {

    public static final String JWT_PREFIX = "jwt";

    private String header = "Authorization";

    private String secret = "defaultSecret";

    private Long expiration = 100L;

    private String authPath = "auth";

    private String md5Key = "randomKey";

    private Integer randomCount = 6;

}
