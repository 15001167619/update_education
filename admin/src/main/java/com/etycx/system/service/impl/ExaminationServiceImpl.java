package com.etycx.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.etycx.system.mapper.ExaminationMapper;
import com.etycx.system.domain.Examination;
import com.etycx.system.service.IExaminationService;
import com.etycx.common.core.text.Convert;

/**
 * 试题 服务层实现
 * 
 * @author ruoyi
 * @date 2019-10-10
 */
@Service
public class ExaminationServiceImpl implements IExaminationService 
{
	@Autowired
	private ExaminationMapper examinationMapper;

	/**
     * 查询试题信息
     * 
     * @param id 试题ID
     * @return 试题信息
     */
    @Override
	public Examination selectExaminationById(Integer id)
	{
	    return examinationMapper.selectExaminationById(id);
	}
	
	/**
     * 查询试题列表
     * 
     * @param examination 试题信息
     * @return 试题集合
     */
	@Override
	public List<Examination> selectExaminationList(Examination examination)
	{
	    return examinationMapper.selectExaminationList(examination);
	}
	
    /**
     * 新增试题
     * 
     * @param examination 试题信息
     * @return 结果
     */
	@Override
	public int insertExamination(Examination examination)
	{
	    return examinationMapper.insertExamination(examination);
	}
	
	/**
     * 修改试题
     * 
     * @param examination 试题信息
     * @return 结果
     */
	@Override
	public int updateExamination(Examination examination)
	{
	    return examinationMapper.updateExamination(examination);
	}

	/**
     * 删除试题对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteExaminationByIds(String ids)
	{
		return examinationMapper.deleteExaminationByIds(Convert.toStrArray(ids));
	}
	
}
