package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 课程评价表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-08 13:16:47
 */
@Data
@TableName("tb_curriculum_comment")
public class CurriculumCommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 课程ID
	 */
	private Integer curriculumId;
	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * 评分
	 */
	private Integer rating;
	/**
	 * 评语
	 */
	private String comment;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
