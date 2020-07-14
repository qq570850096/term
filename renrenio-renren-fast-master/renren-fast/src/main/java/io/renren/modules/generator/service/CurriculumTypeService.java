package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.CurriculumTypeEntity;

import java.util.Map;

/**
 * 课程类型表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-08 13:16:47
 */
public interface CurriculumTypeService extends IService<CurriculumTypeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

