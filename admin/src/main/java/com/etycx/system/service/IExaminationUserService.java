package com.etycx.system.service;

import com.etycx.system.domain.ExaminationUser;
import java.util.List;
import java.util.Map;

/**
 * 用户 服务层
 * 
 * @author ruoyi
 * @date 2019-10-10
 */
public interface IExaminationUserService 
{
	/**
     * 查询用户信息
     * 
     * @param id 用户ID
     * @return 用户信息
     */
	public ExaminationUser selectExaminationUserById(Integer id);
	
	/**
     * 查询用户列表
     * 
     * @param examinationUser 用户信息
     * @return 用户集合
     */
	public List<ExaminationUser> selectExaminationUserList(ExaminationUser examinationUser);
	
	/**
     * 新增用户
     * 
     * @param examinationUser 用户信息
     * @return 结果
     */
	public int insertExaminationUser(ExaminationUser examinationUser);
	
	/**
     * 修改用户
     * 
     * @param examinationUser 用户信息
     * @return 结果
     */
	public int updateExaminationUser(ExaminationUser examinationUser);
		
	/**
     * 删除用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteExaminationUserByIds(String ids);

	int cleanSore();

	List<Map<String, Object>> branchNames();
}
