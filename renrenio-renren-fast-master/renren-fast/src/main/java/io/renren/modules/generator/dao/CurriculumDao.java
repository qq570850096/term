package io.renren.modules.generator.dao;

import io.renren.modules.generator.entity.CurriculumEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * 课程概要表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-08 13:16:47
 */
@Mapper
public interface CurriculumDao extends BaseMapper<CurriculumEntity> {
	public List<HashMap> searchTop();//使用myBaits动态代理
	public HashMap searchHot();
	public List<HashMap> searchByDifficulty(String difficulty);
}
