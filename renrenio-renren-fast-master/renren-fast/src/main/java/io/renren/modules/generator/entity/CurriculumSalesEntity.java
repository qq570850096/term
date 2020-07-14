package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 课程销售表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-08 13:16:47
 */
@Data
@TableName("tb_curriculum_sales")
public class CurriculumSalesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private Integer curriculumId;
	/**
	 * 购买者数量
	 */
	private Integer buyers;
	/**
	 * 总销售额
	 */
	private BigDecimal totalAmount;
	/**
	 * 平均评分
	 */
	private String rating;
	/**
	 * 评语总数
	 */
	private Integer comment;

}
