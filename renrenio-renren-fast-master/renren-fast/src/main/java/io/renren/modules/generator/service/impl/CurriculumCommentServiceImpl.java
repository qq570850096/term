package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.CurriculumCommentDao;
import io.renren.modules.generator.entity.CurriculumCommentEntity;
import io.renren.modules.generator.service.CurriculumCommentService;


@Service("curriculumCommentService")
public class CurriculumCommentServiceImpl extends ServiceImpl<CurriculumCommentDao, CurriculumCommentEntity> implements CurriculumCommentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CurriculumCommentEntity> page = this.page(
                new Query<CurriculumCommentEntity>().getPage(params),
                new QueryWrapper<CurriculumCommentEntity>()
        );

        return new PageUtils(page);
    }

}