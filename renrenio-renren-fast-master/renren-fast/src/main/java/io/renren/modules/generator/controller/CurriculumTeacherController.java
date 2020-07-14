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

import io.renren.modules.generator.entity.CurriculumTeacherEntity;
import io.renren.modules.generator.service.CurriculumTeacherService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 课程&教师关联表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-08 13:16:47
 */
@RestController
@RequestMapping("app/curriculumteacher")
public class CurriculumTeacherController {
    @Autowired
    private CurriculumTeacherService curriculumTeacherService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:curriculumteacher:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = curriculumTeacherService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:curriculumteacher:info")
    public R info(@PathVariable("id") Integer id){
		CurriculumTeacherEntity curriculumTeacher = curriculumTeacherService.getById(id);

        return R.ok().put("curriculumTeacher", curriculumTeacher);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:curriculumteacher:save")
    public R save(@RequestBody CurriculumTeacherEntity curriculumTeacher){
		curriculumTeacherService.save(curriculumTeacher);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:curriculumteacher:update")
    public R update(@RequestBody CurriculumTeacherEntity curriculumTeacher){
		curriculumTeacherService.updateById(curriculumTeacher);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:curriculumteacher:delete")
    public R delete(@RequestBody Integer[] ids){
		curriculumTeacherService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
