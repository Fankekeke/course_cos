package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.StudentInfo;
import cc.mrbird.febs.system.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
public interface StudentInfoMapper extends BaseMapper<StudentInfo> {

    // 分页查询学生信息
    IPage<LinkedHashMap<String, Object>> getStudentInfoByPage(Page page, @Param("studentInfo") StudentInfo studentInfo);

    // 根据学生获取选择的课程
    List<LinkedHashMap> selectionByStudentId(@Param("studentId") Integer studentId);

    // 分页获取系统用户数据
    IPage<LinkedHashMap<String,Object>> systemUserPage(Page page, @Param("user") User user);

    // 学生专业分布统计
    List<LinkedHashMap<String, Object>> studentDisciplineRate();

    // 课程预约情况
    List<LinkedHashMap<String, Object>> courseRate();
}
