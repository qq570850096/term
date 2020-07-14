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

import io.renren.modules.generator.entity.CurriculumSalesEntity;
import io.renren.modules.generator.service.CurriculumSalesService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 课程销售表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-08 13:16:47
 */
@RestController
@RequestMapping("app/curriculumsales")
public class CurriculumSalesController {
    @Autowired
    private CurriculumSalesService curriculumSalesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:curriculumsales:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = curriculumSalesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:curriculumsales:info")
    public R info(@PathVariable("id") Integer id){
		CurriculumSalesEntity curriculumSales = curriculumSalesService.getById(id);

        return R.ok().put("curriculumSales", curriculumSales);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:curriculumsales:save")
    public R save(@RequestBody CurriculumSalesEntity curriculumSales){
		curriculumSalesService.save(curriculumSales);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:curriculumsales:update")
    public R update(@RequestBody CurriculumSalesEntity curriculumSales){
		curriculumSalesService.updateById(curriculumSales);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:curriculumsales:delete")
    public R delete(@RequestBody Integer[] ids){
		curriculumSalesService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
