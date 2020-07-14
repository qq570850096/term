package io.renren.modules.generator.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.generator.entity.CurriculumEntity;
import io.renren.modules.generator.service.CurriculumService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 课程概要表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-08 13:16:47
 */
@RestController
@RequestMapping("app/curriculum")
@Api("网络课程类")
public class CurriculumController {
    @Autowired
    private CurriculumService curriculumService;
    @RequestMapping("/searchTop")
    @ApiOperation("查询置顶课程")
    public  R searchTop(){
        List<HashMap> list=curriculumService.searchTop();
        return  R.ok().put("result",list);
    }
    @RequestMapping("/searchHot")
    @ApiOperation("查询置顶课程")
    public  R searchHot(){
        HashMap map=curriculumService.searchHot();
        return  R.ok().put("result",map);
    }
    @RequestMapping("/searchRefined")
    @ApiOperation("查询精致课程")
    public R searchRefined(){
        List<HashMap> list_1 = curriculumService.searchByDifficulty("入门");
        List<HashMap> list_2 = curriculumService.searchByDifficulty("简单");
        List<HashMap> list_3 = curriculumService.searchByDifficulty("中等");
        return R.ok().put("list_1",list_1).put("list_2",list_2).put("list_3",list_3);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:curriculum:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = curriculumService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:curriculum:info")
    public R info(@PathVariable("id") Integer id){
		CurriculumEntity curriculum = curriculumService.getById(id);

        return R.ok().put("curriculum", curriculum);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:curriculum:save")
    public R save(@RequestBody CurriculumEntity curriculum){
		curriculumService.save(curriculum);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:curriculum:update")
    public R update(@RequestBody CurriculumEntity curriculum){
		curriculumService.updateById(curriculum);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:curriculum:delete")
    public R delete(@RequestBody Integer[] ids){
		curriculumService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
