package com.etycx.system.mapper;

import com.etycx.system.domain.ExaminationUser;
import java.util.List;
import java.util.Map;

/**
 * 用户 数据层
 * 
 * @author ruoyi
 * @date 2019-10-10
 */
public interface ExaminationUserMapper 
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
     * 删除用户
     * 
     * @param id 用户ID
     * @return 结果
     */
	public int deleteExaminationUserById(Integer id);
	
	/**
     * 批量删除用户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteExaminationUserByIds(String[] ids);

	/**
	 * 清空成绩
	 *
	 * @return 结果
	 */
	int cleanSore();

	List<Map<String, Object>> questions();

	List<Map<String, Object>> questionsAnswer();

	int insertUserAnswerRecord(List<Map<String, Object>> list);

	List<Map<String, Object>> branchNames();
}