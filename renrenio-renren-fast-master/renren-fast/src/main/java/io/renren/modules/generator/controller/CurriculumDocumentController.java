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

import io.renren.modules.generator.entity.CurriculumDocumentEntity;
import io.renren.modules.generator.service.CurriculumDocumentService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 课程文档表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-08 13:16:47
 */
@RestController
@RequestMapping("app/curriculumdocument")
public class CurriculumDocumentController {
    @Autowired
    private CurriculumDocumentService curriculumDocumentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:curriculumdocument:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = curriculumDocumentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:curriculumdocument:info")
    public R info(@PathVariable("id") Integer id){
		CurriculumDocumentEntity curriculumDocument = curriculumDocumentService.getById(id);

        return R.ok().put("curriculumDocument", curriculumDocument);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:curriculumdocument:save")
    public R save(@RequestBody CurriculumDocumentEntity curriculumDocument){
		curriculumDocumentService.save(curriculumDocument);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:curriculumdocument:update")
    public R update(@RequestBody CurriculumDocumentEntity curriculumDocument){
		curriculumDocumentService.updateById(curriculumDocument);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:curriculumdocument:delete")
    public R delete(@RequestBody Integer[] ids){
		curriculumDocumentService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
