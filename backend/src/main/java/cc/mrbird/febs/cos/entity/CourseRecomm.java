package cc.mrbird.febs.cos.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 专业课程推荐
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CourseRecomm implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 对应专业
     */
    private String discipline;

    /**
     * 课程ID
     */
    private String courseIds;

    /**
     * 课程名称
     */
    private String courseNames;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private String createDate;


}
