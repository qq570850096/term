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

import io.renren.modules.generator.entity.LearningEntity;
import io.renren.modules.generator.service.LearningService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 学习表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-08 13:16:47
 */
@RestController
@RequestMapping("app/learning")
public class LearningController {
    @Autowired
    private LearningService learningService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:learning:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = learningService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:learning:info")
    public R info(@PathVariable("id") Integer id){
		LearningEntity learning = learningService.getById(id);

        return R.ok().put("learning", learning);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:learning:save")
    public R save(@RequestBody LearningEntity learning){
		learningService.save(learning);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:learning:update")
    public R update(@RequestBody LearningEntity learning){
		learningService.updateById(learning);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:learning:delete")
    public R delete(@RequestBody Integer[] ids){
		learningService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
