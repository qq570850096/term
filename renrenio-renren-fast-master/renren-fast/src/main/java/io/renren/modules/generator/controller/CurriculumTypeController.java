package io.renren.modules.generator.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.generator.entity.CurriculumTypeEntity;
import io.renren.modules.generator.service.CurriculumTypeService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 课程类型表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-08 13:16:47
 */
@RestController
@RequestMapping("app/curriculumtype")
public class CurriculumTypeController {
    @Autowired
    private CurriculumTypeService curriculumTypeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:curriculumtype:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = curriculumTypeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:curriculumtype:info")
    public R info(@PathVariable("id") Integer id){
		CurriculumTypeEntity curriculumType = curriculumTypeService.getById(id);

        return R.ok().put("curriculumType", curriculumType);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:curriculumtype:save")
    public R save(@RequestBody CurriculumTypeEntity curriculumType){
		curriculumTypeService.save(curriculumType);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:curriculumtype:update")
    public R update(@RequestBody CurriculumTypeEntity curriculumType){
		curriculumTypeService.updateById(curriculumType);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:curriculumtype:delete")
    public R delete(@RequestBody Integer[] ids){
		curriculumTypeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
