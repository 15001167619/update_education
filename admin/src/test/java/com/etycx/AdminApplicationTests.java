package com.etycx;


import com.etycx.framework.cache.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class AdminApplicationTests {


    @Autowired
    private RedisUtil redisUtil;




    @Test
    public void index(){
        boolean whs = redisUtil.set("whs", "111");
        System.out.println(whs);
    }


}
