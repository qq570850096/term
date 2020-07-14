package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.CurriculumDocumentDao;
import io.renren.modules.generator.entity.CurriculumDocumentEntity;
import io.renren.modules.generator.service.CurriculumDocumentService;


@Service("curriculumDocumentService")
public class CurriculumDocumentServiceImpl extends ServiceImpl<CurriculumDocumentDao, CurriculumDocumentEntity> implements CurriculumDocumentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CurriculumDocumentEntity> page = this.page(
                new Query<CurriculumDocumentEntity>().getPage(params),
                new QueryWrapper<CurriculumDocumentEntity>()
        );

        return new PageUtils(page);
    }

}