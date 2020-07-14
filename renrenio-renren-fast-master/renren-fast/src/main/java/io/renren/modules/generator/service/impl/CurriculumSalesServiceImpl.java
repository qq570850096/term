package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.CurriculumSalesDao;
import io.renren.modules.generator.entity.CurriculumSalesEntity;
import io.renren.modules.generator.service.CurriculumSalesService;


@Service("curriculumSalesService")
public class CurriculumSalesServiceImpl extends ServiceImpl<CurriculumSalesDao, CurriculumSalesEntity> implements CurriculumSalesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CurriculumSalesEntity> page = this.page(
                new Query<CurriculumSalesEntity>().getPage(params),
                new QueryWrapper<CurriculumSalesEntity>()
        );

        return new PageUtils(page);
    }

}