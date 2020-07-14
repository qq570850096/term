package io.renren.modules.generator.dao;

import io.renren.modules.generator.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-08 13:16:47
 */
@Mapper//注意重构改名
public interface appUserDao extends BaseMapper<UserEntity> {
	
}
