package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.TeacherInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
public interface TeacherInfoMapper extends BaseMapper<TeacherInfo> {

    // 分页查询教师信息
    IPage<LinkedHashMap<String, Object>> teacherInfoByPage(Page page, @Param("teacherInfo") TeacherInfo teacherInfo);

    // 根据教员获取选择的课程
    List<LinkedHashMap<String, Object>> selectionByTeacher(@Param("teacherId") Integer teacherId);
}
