package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 讲师表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-07-08 13:16:47
 */
@Data
@TableName("tb_teacher")
public class TeacherEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 头像
	 */
	private String photo;
	/**
	 * 讲师介绍
	 */
	private String introduction;
	/**
	 * 职称
	 */
	private String title;
	/**
	 * 课程数量
	 */
	private Integer curriculums;
	/**
	 * 好评度
	 */
	private String rating;
	/**
	 * 学生数量
	 */
	private Integer students;

}
