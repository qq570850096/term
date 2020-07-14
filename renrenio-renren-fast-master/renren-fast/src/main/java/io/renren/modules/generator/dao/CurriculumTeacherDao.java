package io.renren.modules.generator.dao;

import io.renren.modules.generator.entity.CurriculumTeacherEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 课程&教师关联表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-08 13:16:47
 */
@Mapper
public interface CurriculumTeacherDao extends BaseMapper<CurriculumTeacherEntity> {
	
}
