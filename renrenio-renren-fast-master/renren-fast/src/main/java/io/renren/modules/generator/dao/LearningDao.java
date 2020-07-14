package io.renren.modules.generator.dao;

import io.renren.modules.generator.entity.LearningEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 学习表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-08 13:16:47
 */
@Mapper
public interface LearningDao extends BaseMapper<LearningEntity> {
	
}