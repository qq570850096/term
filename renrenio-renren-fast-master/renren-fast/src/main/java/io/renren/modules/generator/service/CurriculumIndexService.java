package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.CurriculumIndexEntity;

import java.util.Map;

/**
 * 课程目录表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-08 13:16:47
 */
public interface CurriculumIndexService extends IService<CurriculumIndexEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

