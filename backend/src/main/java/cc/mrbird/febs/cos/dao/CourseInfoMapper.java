package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.CourseInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
public interface CourseInfoMapper extends BaseMapper<CourseInfo> {

    // 分页查询课程信息
    IPage<LinkedHashMap<String, Object>> getCourseInfoByPage(Page page, @Param("courseInfo") CourseInfo courseInfo);

    // 根据课程获取学生信息
    List<LinkedHashMap<String, Object>> getStudentListByCourse(@Param("courseId") Integer courseId);

    // 根据教员获取课程分页信息
    IPage<LinkedHashMap<String, Object>> getCoursePageByTeacher(Page page, @Param("courseInfo") CourseInfo courseInfo);
}
