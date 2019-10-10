package com.etycx.system.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.etycx.common.base.BaseVo;
import com.etycx.system.domain.ExaminationUser;
import com.etycx.system.mapper.ExaminationUserMapper;
import com.etycx.system.service.IApiService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 武海升
 * @since 2019-07-19
 */
@Service
@Slf4j
public class ApiServiceImpl implements IApiService {

    @Resource
    private ExaminationUserMapper userMapper;

    private final  BaseVo baseVo = new BaseVo();

    @Override
    public BaseVo login(String userName, String branchName) {
        ExaminationUser user = new ExaminationUser();
        user.setBranchName(branchName);
        user.setUserName(userName);
        List<ExaminationUser> examinationUsers = userMapper.selectExaminationUserList(user);
        if(examinationUsers!=null && examinationUsers.size()>0){
            return baseVo.ok(examinationUsers.get(0),"用户登录成功");
        }else {
            return baseVo.error(null,404,"用户不存在");
        }
    }

    @Override
    public BaseVo rank(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ExaminationUser> list = userMapper.selectExaminationUserList(null);
        PageInfo<ExaminationUser> pageInfo = new PageInfo<>(list);
        List<Map<String, Object>> resultData = list.stream()
                .map(new ApiServiceImpl() :: toRankMap)
                .collect(Collectors.toList());
        return baseVo.ok(baseVo.findDataMapPage(pageInfo, resultData),"获取用户排行榜成功");
    }

    @Override
    public BaseVo questions() {
        List<Map<String, Object>> list = userMapper.questions();
        if(CollectionUtil.isNotEmpty(list)){
            Collections.shuffle(list);
            Collections.shuffle(list);
            Collections.shuffle(list);
            return baseVo.ok(getQuestions(list),"获取试题成功");
        }
        return baseVo.ok(null,"获取试题失败");
    }

    @Override
    public BaseVo answer(Integer userId, ArrayList userAnswerList) {
        //用户答题记录情况
        List<Map<String, Object>> answerRecord = new ArrayList<>();
        //用户答题总分
        Double userScore = 0D;
        if(CollectionUtil.isNotEmpty(userAnswerList)){
            List<Map<String, Object>> answers = userMapper.questionsAnswer();
            for (Object userAnswer : userAnswerList) {
                LinkedHashMap user = (LinkedHashMap)userAnswer;
                Map<String, Object> userRecord = new HashMap<>(3);
                userRecord.put("userId",userId);
                Integer questionId = (Integer) user.get("questionId");
                userRecord.put("questionId",questionId);
                String answer = (String) user.get("answer");
                userRecord.put("userAnswer",answer);
                answerRecord.add(userRecord);
                //获取试题本试题得分情况
                userScore =  countUserScore(userScore,questionId,answer,answers);
            }
            //用户答题信息入库
            userMapper.insertUserAnswerRecord(answerRecord);
            //更新用户
            ExaminationUser user = new ExaminationUser();
            user.setScore(userScore);
            user.setId(userId);
            userMapper.updateExaminationUser(user);
        }
        return baseVo.ok(userScore,"提交答案成功");
    }

    @Override
    public BaseVo branchNames() {
        List<Map<String, Object>> list = userMapper.branchNames();
        if(CollectionUtil.isNotEmpty(list)){
            return baseVo.ok(list,"获取支部名称成功");
        }
        return baseVo.ok(null,"获取支部名称失败");
    }

    private Double countUserScore(Double userScore, Integer questionId, String answer, List<Map<String, Object>> answers) {
        for (Map<String, Object> answerMap : answers) {
            Integer examQuestionId = (Integer) answerMap.get("questionId");
            if(examQuestionId.equals(questionId)){
                String examRight = (String) answerMap.get("examinationRight");
                Integer examinationType = (Integer) answerMap.get("examinationType");
                userScore = userScore(examQuestionId,examinationType,answer,examRight,userScore);
                break;
            }

        }
        return userScore;
    }

    private Double userScore(Integer examQuestionId,Integer examinationType, String answer, String examRight,Double userScore) {
        double score = 0D;
        if (examinationType == 1){
            if(answer.equals(examRight)){
                score+=2;
            }
        }else {
            String[] answerArray = answer.split("#");
            String[] examRightArray = examRight.split("#");
            int userAnswerCount = 0;
            int userSuccessCount = 0;
            boolean sign = answerArray.length > 0 && answerArray.length == examRightArray.length - 1;
            if(sign){
                String type = examRightArray[0];
                //答案 或 关系
                final String answerType = "0";
                if(answerType.equals(type)){
                    //用户回答
                    for (String userAnswer : answerArray) {
                        userAnswerCount++;
                        boolean flag = !"0".equals(userAnswer) && Arrays.asList(examRightArray).contains(userAnswer);
                        if (flag){
                            userSuccessCount++;
                        }
                    }
                }else {
                    //答案 必须 关系
                    for (int i = 0; i < answerArray.length; i++) {
                        String userAnswer = answerArray[i];
                        String examAnswer = examRightArray[i +1];
                        userAnswerCount++;
                        if(userAnswer.equals(examAnswer)){
                            userSuccessCount++;
                        }
                    }
                }
                //获取得分
                score = new BigDecimal(userSuccessCount)
                        .divide(new BigDecimal(userAnswerCount), 1, RoundingMode.HALF_UP).multiply(new BigDecimal(2)).doubleValue();
            }
        }
        userScore = userScore + score;
        return userScore;
    }

    private Object getQuestions(List<Map<String, Object>> list) {
        List<Map<String, Object>> questions = new ArrayList<Map<String, Object>>(list.size());
        list.forEach(map -> {
            Integer examinationType = (Integer)map.get("examinationType");
            if(examinationType == 1){
                JSONObject examinationOption = JSONObject.parseObject((String) map.get("examinationOption"));
                map.put("examinationOption",examinationOption);
            }
            questions.add(map);
        });
        return questions;
    }

    private Map<String, Object> toRankMap(ExaminationUser sysUser) {
        Map<String, Object> map = new HashMap<>(4);
        map.put("id",sysUser.getId());
        map.put("userName",sysUser.getUserName());
        map.put("branchName",sysUser.getBranchName());
        map.put("score",sysUser.getScore());
        return map;
    }
}
