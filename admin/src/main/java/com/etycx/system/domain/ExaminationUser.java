package com.etycx.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.etycx.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 用户表 examination_user
 * 
 * @author ruoyi
 * @date 2019-10-10
 */
public class ExaminationUser extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/** 用户姓名 */
	private String userName;
	/** 支部名称 */
	private String branchName;
	/** 成绩 */
	private Double score;
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
	public void setUserName(String userName) 
	{
		this.userName = userName;
	}

	public String getUserName() 
	{
		return userName;
	}
	public void setBranchName(String branchName) 
	{
		this.branchName = branchName;
	}

	public String getBranchName() 
	{
		return branchName;
	}
	public void setScore(Double score) 
	{
		this.score = score;
	}

	public Double getScore() 
	{
		return score;
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
            .append("userName", getUserName())
            .append("branchName", getBranchName())
            .append("score", getScore())
            .append("createTime", getCreateTime())
            .toString();
    }
}
