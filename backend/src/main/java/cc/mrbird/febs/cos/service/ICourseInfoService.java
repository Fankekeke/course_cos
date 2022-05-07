package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.CourseInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
public interface ICourseInfoService extends IService<CourseInfo> {

    // 分页查询课程信息
    IPage<LinkedHashMap<String, Object>> getCourseInfoByPage(Page page, CourseInfo courseInfo);

    // 根据课程获取学生信息
    List<LinkedHashMap<String, Object>> getStudentListByCourse(Integer courseId);

    // 根据教员获取课程分页信息
    IPage<LinkedHashMap<String, Object>> getCoursePageByTeacher(Page page, @Param("courseInfo") CourseInfo courseInfo);
}
