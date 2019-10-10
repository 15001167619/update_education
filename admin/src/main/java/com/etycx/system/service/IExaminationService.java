package com.etycx.system.service;

import com.etycx.system.domain.Examination;
import java.util.List;

/**
 * 试题 服务层
 * 
 * @author ruoyi
 * @date 2019-10-10
 */
public interface IExaminationService 
{
	/**
     * 查询试题信息
     * 
     * @param id 试题ID
     * @return 试题信息
     */
	public Examination selectExaminationById(Integer id);
	
	/**
     * 查询试题列表
     * 
     * @param examination 试题信息
     * @return 试题集合
     */
	public List<Examination> selectExaminationList(Examination examination);
	
	/**
     * 新增试题
     * 
     * @param examination 试题信息
     * @return 结果
     */
	public int insertExamination(Examination examination);
	
	/**
     * 修改试题
     * 
     * @param examination 试题信息
     * @return 结果
     */
	public int updateExamination(Examination examination);
		
	/**
     * 删除试题信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteExaminationByIds(String ids);
	
}
