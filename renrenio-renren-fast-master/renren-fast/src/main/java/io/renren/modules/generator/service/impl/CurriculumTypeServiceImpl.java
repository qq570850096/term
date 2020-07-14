package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.CurriculumTypeDao;
import io.renren.modules.generator.entity.CurriculumTypeEntity;
import io.renren.modules.generator.service.CurriculumTypeService;


@Service("curriculumTypeService")
public class CurriculumTypeServiceImpl extends ServiceImpl<CurriculumTypeDao, CurriculumTypeEntity> implements CurriculumTypeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CurriculumTypeEntity> page = this.page(
                new Query<CurriculumTypeEntity>().getPage(params),
                new QueryWrapper<CurriculumTypeEntity>()
        );

        return new PageUtils(page);
    }

}