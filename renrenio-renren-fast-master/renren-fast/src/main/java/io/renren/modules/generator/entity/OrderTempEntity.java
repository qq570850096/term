package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 订单临时表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-08 13:16:47
 */
@Data
@TableName("tb_order_temp")
public class OrderTempEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 订单流水号
	 */
	private String orderNum;
	/**
	 * 课程ID
	 */
	private Integer curriculumId;
	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * 购买价格
	 */
	private BigDecimal price;
	/**
	 * 购买时间
	 */
	private Date createTime;

}
