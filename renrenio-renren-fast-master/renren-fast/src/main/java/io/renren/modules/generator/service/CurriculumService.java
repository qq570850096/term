package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.CurriculumEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 课程概要表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-08 13:16:47
 */
public interface CurriculumService extends IService<CurriculumEntity> {

    PageUtils queryPage(Map<String, Object> params);
    public List<HashMap> searchTop();//使用myBaits动态代理
    public HashMap searchHot();
    public List<HashMap> searchByDifficulty(String difficulty);
}

