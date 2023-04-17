package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 课程管理
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CourseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 课程性质
     */
    private String courseNature;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 绩点
     */
    private Integer gpa;

    /**
     * 学分
     */
    private Integer credit;

    /**
     * 节课数量
     */
    private Integer lessonsNum;

    /**
     * 所在教室
     */
    private String classroom;

    /**
     * 备注
     */
    private String content;

    /**
     * 教师ID
     */
    private Integer teacherId;

    /**
     * 创建时间
     */
    private String createDate;

    @TableField(exist = false)
    private String teacherName;

}
