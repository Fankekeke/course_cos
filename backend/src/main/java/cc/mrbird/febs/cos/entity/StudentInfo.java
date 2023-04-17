package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 学生管理
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StudentInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 系统用户ID
     */
    private Long adminId;

    /**
     * 学生编号
     */
    private String code;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 学号
     */
    private String studentId;

    /**
     * 性别(1.男 2.女)
     */
    private Integer sex;

    /**
     * 专业
     */
    private String discipline;

    /**
     * 行政班
     */
    private String adminClass;

    /**
     * 创建时间
     */
    private String createTime;


}
