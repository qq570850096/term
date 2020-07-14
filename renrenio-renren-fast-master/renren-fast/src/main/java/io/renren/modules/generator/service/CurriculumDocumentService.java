package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.CurriculumDocumentEntity;

import java.util.Map;

/**
 * 课程文档表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-08 13:16:47
 */
public interface CurriculumDocumentService extends IService<CurriculumDocumentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

