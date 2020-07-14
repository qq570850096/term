package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 课程概要表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-08 13:16:47
 */
@Data
@TableName("tb_curriculum")
public class CurriculumEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 课程标题
	 */
	private String title;
	/**
	 * 售价
	 */
	private BigDecimal price;
	/**
	 * 当前售价
	 */
	private BigDecimal currentPrice;
	/**
	 * 条幅地址
	 */
	private String banner;
	/**
	 * 学习周期
	 */
	private String learningCycle;
	/**
	 * 类型ID
	 */
	private Integer typeId;
	/**
	 * 标签
	 */
	private String tag;
	/**
	 * 推荐介绍
	 */
	private String suggestion;
	/**
	 * 难度等级
	 */
	private String difficulty;
	/**
	 * 状态
	 */
	private String state;
	/**
	 * 是否置顶
	 */
	private Integer top;
	/**
	 * 视频数量
	 */
	private Integer videoNum;
	/**
	 * 文档数量
	 */
	private Integer documentNum;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
