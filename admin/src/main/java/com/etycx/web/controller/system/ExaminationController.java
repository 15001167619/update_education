package com.etycx.web.controller.system;

import java.util.List;
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
import com.etycx.system.domain.Examination;
import com.etycx.system.service.IExaminationService;
import com.etycx.common.core.controller.BaseController;
import com.etycx.common.core.page.TableDataInfo;
import com.etycx.common.core.domain.AjaxResult;
import com.etycx.common.utils.poi.ExcelUtil;

/**
 * 试题 信息操作处理
 * 
 * @author ruoyi
 * @date 2019-10-10
 */
@Controller
@RequestMapping("/system/examination")
public class ExaminationController extends BaseController
{
    private String prefix = "system/examination";
	
	@Autowired
	private IExaminationService examinationService;
	
	@RequiresPermissions("system:examination:view")
	@GetMapping()
	public String examination()
	{
	    return prefix + "/examination";
	}
	
	/**
	 * 查询试题列表
	 */
	@RequiresPermissions("system:examination:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Examination examination)
	{
		startPage();
        List<Examination> list = examinationService.selectExaminationList(examination);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出试题列表
	 */
	@RequiresPermissions("system:examination:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Examination examination)
    {
    	List<Examination> list = examinationService.selectExaminationList(examination);
        ExcelUtil<Examination> util = new ExcelUtil<Examination>(Examination.class);
        return util.exportExcel(list, "examination");
    }
	
	/**
	 * 新增试题
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存试题
	 */
	@RequiresPermissions("system:examination:add")
	@Log(title = "试题", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Examination examination)
	{		
		return toAjax(examinationService.insertExamination(examination));
	}

	/**
	 * 修改试题
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		Examination examination = examinationService.selectExaminationById(id);
		mmap.put("examination", examination);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存试题
	 */
	@RequiresPermissions("system:examination:edit")
	@Log(title = "试题", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Examination examination)
	{		
		return toAjax(examinationService.updateExamination(examination));
	}
	
	/**
	 * 删除试题
	 */
	@RequiresPermissions("system:examination:remove")
	@Log(title = "试题", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(examinationService.deleteExaminationByIds(ids));
	}
	
}
