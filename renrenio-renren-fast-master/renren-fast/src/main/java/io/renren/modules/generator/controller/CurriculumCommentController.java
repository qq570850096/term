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

import io.renren.modules.generator.entity.CurriculumCommentEntity;
import io.renren.modules.generator.service.CurriculumCommentService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 课程评价表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-08 13:16:47
 */
@RestController
@RequestMapping("app/curriculumcomment")
public class CurriculumCommentController {
    @Autowired
    private CurriculumCommentService curriculumCommentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:curriculumcomment:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = curriculumCommentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:curriculumcomment:info")
    public R info(@PathVariable("id") Integer id){
		CurriculumCommentEntity curriculumComment = curriculumCommentService.getById(id);

        return R.ok().put("curriculumComment", curriculumComment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:curriculumcomment:save")
    public R save(@RequestBody CurriculumCommentEntity curriculumComment){
		curriculumCommentService.save(curriculumComment);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:curriculumcomment:update")
    public R update(@RequestBody CurriculumCommentEntity curriculumComment){
		curriculumCommentService.updateById(curriculumComment);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:curriculumcomment:delete")
    public R delete(@RequestBody Integer[] ids){
		curriculumCommentService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
