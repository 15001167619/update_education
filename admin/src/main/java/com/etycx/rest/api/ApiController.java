package com.etycx.rest.api;

import com.etycx.system.service.IApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping(value = "api/user")
public class ApiController {

    @Autowired
    private IApiService apiService;

    /**
     * @description  用户登录
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Object login(@RequestParam("userName") String userName,
                        @RequestParam("branchName") String branchName) {
        return apiService.login(userName,branchName);
    }

    /**
     * @description  用户排名
     */
    @RequestMapping(value={"rank"}, method= RequestMethod.GET)
    public Object rank(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                       @RequestParam(value = "pageSize",defaultValue = "8") Integer pageSize) {
        return apiService.rank(pageNum,pageSize);
    }

    /**
     * @description  获取支部名称
     */
    @RequestMapping(value={"branchNames"}, method= RequestMethod.GET)
    public Object branchNames() {
        return apiService.branchNames();
    }

    /**
     * @description  获取试题
     */
    @RequestMapping(value={"questions"}, method= RequestMethod.GET)
    public Object questions() {
        return apiService.questions();
    }

    /**
     * @description  用户答题
     */
    @RequestMapping(value = "answer", method = RequestMethod.POST)
    public Object answer(@RequestBody Map<String,Object> params) {
        Integer userId = ( Integer) params.get("userId");
        ArrayList userAnswerList =  (ArrayList) params.get("userAnswer");
        return apiService.answer(userId,userAnswerList);
    }


}
