package com.etycx.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.etycx.system.mapper.ExaminationUserMapper;
import com.etycx.system.domain.ExaminationUser;
import com.etycx.system.service.IExaminationUserService;
import com.etycx.common.core.text.Convert;

/**
 * 用户 服务层实现
 * 
 * @author ruoyi
 * @date 2019-10-10
 */
@Service
public class ExaminationUserServiceImpl implements IExaminationUserService 
{
	@Autowired
	private ExaminationUserMapper examinationUserMapper;

	/**
     * 查询用户信息
     * 
     * @param id 用户ID
     * @return 用户信息
     */
    @Override
	public ExaminationUser selectExaminationUserById(Integer id)
	{
	    return examinationUserMapper.selectExaminationUserById(id);
	}
	
	/**
     * 查询用户列表
     * 
     * @param examinationUser 用户信息
     * @return 用户集合
     */
	@Override
	public List<ExaminationUser> selectExaminationUserList(ExaminationUser examinationUser)
	{
	    return examinationUserMapper.selectExaminationUserList(examinationUser);
	}
	
    /**
     * 新增用户
     * 
     * @param examinationUser 用户信息
     * @return 结果
     */
	@Override
	public int insertExaminationUser(ExaminationUser examinationUser)
	{
	    return examinationUserMapper.insertExaminationUser(examinationUser);
	}
	
	/**
     * 修改用户
     * 
     * @param examinationUser 用户信息
     * @return 结果
     */
	@Override
	public int updateExaminationUser(ExaminationUser examinationUser)
	{
	    return examinationUserMapper.updateExaminationUser(examinationUser);
	}

	/**
     * 删除用户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteExaminationUserByIds(String ids)
	{
		return examinationUserMapper.deleteExaminationUserByIds(Convert.toStrArray(ids));
	}

	@Override
	public int cleanSore() {
		return examinationUserMapper.cleanSore();
	}

	@Override
	public List<Map<String, Object>> branchNames() {
		return examinationUserMapper.branchNames();
	}

}
