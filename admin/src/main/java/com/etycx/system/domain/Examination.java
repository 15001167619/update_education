package com.etycx.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.etycx.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 试题表 examination
 * 
 * @author ruoyi
 * @date 2019-10-10
 */
public class Examination extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 试题类型 0 填空 1 选择 */
	private Integer examinationType;
	/** 试题内容 */
	private String examinationContent;
	/** 试题选项 */
	private String examinationOption;
	/** 试题答案 */
	private String examinationRight;
	/** 录入时间 */
	private Date createTime;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setExaminationType(Integer examinationType) 
	{
		this.examinationType = examinationType;
	}

	public Integer getExaminationType() 
	{
		return examinationType;
	}
	public void setExaminationContent(String examinationContent) 
	{
		this.examinationContent = examinationContent;
	}

	public String getExaminationContent() 
	{
		return examinationContent;
	}
	public void setExaminationOption(String examinationOption) 
	{
		this.examinationOption = examinationOption;
	}

	public String getExaminationOption() 
	{
		return examinationOption;
	}
	public void setExaminationRight(String examinationRight) 
	{
		this.examinationRight = examinationRight;
	}

	public String getExaminationRight() 
	{
		return examinationRight;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("examinationType", getExaminationType())
            .append("examinationContent", getExaminationContent())
            .append("examinationOption", getExaminationOption())
            .append("examinationRight", getExaminationRight())
            .append("createTime", getCreateTime())
            .toString();
    }
}
