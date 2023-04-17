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
 * 选课管理
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SelectionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 课程ID
     */
    private Integer courseId;

    /**
     * 学生ID
     */
    private Integer studentId;

    /**
     * 选课时间
     */
    private String createTime;

    @TableField(exist = false)
    private String studentName;

    @TableField(exist = false)
    private String studentNum;

    @TableField(exist = false)
    private String courseNature;

    @TableField(exist = false)
    private String courseName;

    @TableField(exist = false)
    private Integer userId;

}
