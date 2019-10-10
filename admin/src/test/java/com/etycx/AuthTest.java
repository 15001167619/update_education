package com.etycx;

import com.etycx.framework.shiro.service.SysPasswordService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 武海升
 * @description 接口测试
 * @date 2019/6/12 11:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class AuthTest {

    @Autowired
    private SysPasswordService passwordService;
    @Test
    public void index(){

        String s = passwordService.encryptPassword("account", "pwd666", "salt");
        System.out.println(s);


    }

}
