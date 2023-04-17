package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 教师管理
 *
 * @author FanK
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TeacherInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 系统用户ID
     */
    private Long adminId;

    /**
     * 教师编号
     */
    private String code;

    /**
     * 教师姓名
     */
    private String name;

    /**
     * 工号
     */
    private String jobNumber;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 创建时间
     */
    private String createTime;


}
