package com.etycx.system.service;

import com.etycx.common.base.BaseVo;
import com.etycx.system.domain.ExaminationUser;

import java.util.ArrayList;

/**
 * <p>
 *  接口服务
 * </p>
 *
 * @author 武海升
 * @since 2019-07-19
 */
public interface IApiService{

    /**
     * <p>
     *  用户登录
     * </p>
     * @param userName
     * @param branchName
     * @author 武海升
     * @since 2019-08-22
     * @return BaseVo
     */
    BaseVo login(String userName, String branchName);




    /**
     * <p>
     *  用户排名
     * </p>
     * @param pageNum
     * @param pageSize
     * @author 武海升
     * @since 2019-08-22
     * @return BaseVo
     */
    BaseVo rank(Integer pageNum, Integer pageSize);

    /**
     * <p>
     *  用户排名
     * </p>
     * @author 武海升
     * @since 2019-08-22
     * @return BaseVo
     */
    BaseVo questions();

    /**
     * <p>
     *  用户回答
     * </p>
     * @param userId
     * @param userAnswerList
     * @author 武海升
     * @since 2019-08-22
     * @return BaseVo
     */
    BaseVo answer(Integer userId, ArrayList userAnswerList);

    /**
     * <p>
     *  获取支部名称
     * </p>
     * @author 武海升
     * @since 2019-08-22
     * @return BaseVo
     */
    BaseVo branchNames();

}
