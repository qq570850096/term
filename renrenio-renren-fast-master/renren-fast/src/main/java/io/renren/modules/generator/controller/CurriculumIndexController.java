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

import io.renren.modules.generator.entity.CurriculumIndexEntity;
import io.renren.modules.generator.service.CurriculumIndexService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 课程目录表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-08 13:16:47
 */
@RestController
@RequestMapping("app/curriculumindex")
public class CurriculumIndexController {
    @Autowired
    private CurriculumIndexService curriculumIndexService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:curriculumindex:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = curriculumIndexService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:curriculumindex:info")
    public R info(@PathVariable("id") Integer id){
		CurriculumIndexEntity curriculumIndex = curriculumIndexService.getById(id);

        return R.ok().put("curriculumIndex", curriculumIndex);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:curriculumindex:save")
    public R save(@RequestBody CurriculumIndexEntity curriculumIndex){
		curriculumIndexService.save(curriculumIndex);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:curriculumindex:update")
    public R update(@RequestBody CurriculumIndexEntity curriculumIndex){
		curriculumIndexService.updateById(curriculumIndex);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:curriculumindex:delete")
    public R delete(@RequestBody Integer[] ids){
		curriculumIndexService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
