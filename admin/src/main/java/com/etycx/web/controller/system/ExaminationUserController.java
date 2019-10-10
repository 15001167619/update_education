package com.etycx.web.controller.system;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.etycx.common.annotation.Log;
import com.etycx.common.enums.BusinessType;
import com.etycx.system.domain.ExaminationUser;
import com.etycx.system.service.IExaminationUserService;
import com.etycx.common.core.controller.BaseController;
import com.etycx.common.core.page.TableDataInfo;
import com.etycx.common.core.domain.AjaxResult;
import com.etycx.common.utils.poi.ExcelUtil;

/**
 * 用户 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-10-10
 */
@Controller
@RequestMapping("/system/examinationUser")
public class ExaminationUserController extends BaseController
{
    private String prefix = "system/examinationUser";
	
	@Autowired
	private IExaminationUserService examinationUserService;
	
	@RequiresPermissions("system:examinationUser:view")
	@GetMapping()
	public String examinationUser()
	{
	    return prefix + "/examinationUser";
	}
	
	/**
	 * 查询用户列表
	 */
	@RequiresPermissions("system:examinationUser:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ExaminationUser examinationUser)
	{
		startPage();
        List<ExaminationUser> list = examinationUserService.selectExaminationUserList(examinationUser);
		return getDataTable(list);
	}


	/**
	 * 清空成绩
	 */
	@RequiresPermissions("system:examinationUser:list")
	@PostMapping("/cleanSore")
	@ResponseBody
	public boolean cleanSore()
	{
		examinationUserService.cleanSore();
		return true;
	}
	
	
	/**
	 * 导出用户列表
	 */
	@RequiresPermissions("system:examinationUser:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ExaminationUser examinationUser)
    {
    	List<ExaminationUser> list = examinationUserService.selectExaminationUserList(examinationUser);
        ExcelUtil<ExaminationUser> util = new ExcelUtil<ExaminationUser>(ExaminationUser.class);
        return util.exportExcel(list, "examinationUser");
    }
	
	/**
	 * 新增用户
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		mmap.put("branchNames", examinationUserService.branchNames());
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存用户
	 */
	@RequiresPermissions("system:examinationUser:add")
	@Log(title = "用户", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ExaminationUser examinationUser)
	{		
		return toAjax(examinationUserService.insertExaminationUser(examinationUser));
	}

	/**
	 * 修改用户
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		ExaminationUser examinationUser = examinationUserService.selectExaminationUserById(id);
		mmap.put("examinationUser", examinationUser);
		mmap.put("branchNames", examinationUserService.branchNames());
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存用户
	 */
	@RequiresPermissions("system:examinationUser:edit")
	@Log(title = "用户", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ExaminationUser examinationUser)
	{		
		return toAjax(examinationUserService.updateExaminationUser(examinationUser));
	}
	
	/**
	 * 删除用户
	 */
	@RequiresPermissions("system:examinationUser:remove")
	@Log(title = "用户", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(examinationUserService.deleteExaminationUserByIds(ids));
	}
	
}
