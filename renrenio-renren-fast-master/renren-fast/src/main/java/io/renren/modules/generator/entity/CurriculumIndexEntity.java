package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 课程目录表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-08 13:16:47
 */
@Data
@TableName("tb_curriculum_index")
public class CurriculumIndexEntity implements Serializable {
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
	 * 序号
	 */
	private Integer seqNum;
	/**
	 * 章节标题
	 */
	private String chapter;
	/**
	 * 课程小节
	 */
	private String section;

}
