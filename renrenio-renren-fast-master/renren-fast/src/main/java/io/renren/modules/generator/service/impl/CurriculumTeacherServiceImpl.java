package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.CurriculumTeacherDao;
import io.renren.modules.generator.entity.CurriculumTeacherEntity;
import io.renren.modules.generator.service.CurriculumTeacherService;


@Service("curriculumTeacherService")
public class CurriculumTeacherServiceImpl extends ServiceImpl<CurriculumTeacherDao, CurriculumTeacherEntity> implements CurriculumTeacherService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CurriculumTeacherEntity> page = this.page(
                new Query<CurriculumTeacherEntity>().getPage(params),
                new QueryWrapper<CurriculumTeacherEntity>()
        );

        return new PageUtils(page);
    }

}