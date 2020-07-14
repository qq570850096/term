package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.CurriculumIndexDao;
import io.renren.modules.generator.entity.CurriculumIndexEntity;
import io.renren.modules.generator.service.CurriculumIndexService;


@Service("curriculumIndexService")
public class CurriculumIndexServiceImpl extends ServiceImpl<CurriculumIndexDao, CurriculumIndexEntity> implements CurriculumIndexService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CurriculumIndexEntity> page = this.page(
                new Query<CurriculumIndexEntity>().getPage(params),
                new QueryWrapper<CurriculumIndexEntity>()
        );

        return new PageUtils(page);
    }

}