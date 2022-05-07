package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.TeacherInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
public interface ITeacherInfoService extends IService<TeacherInfo> {

    // 分页查询教师信息
    IPage<LinkedHashMap<String, Object>> teacherInfoByPage(Page page, TeacherInfo teacherInfo);

    // 根据教员获取选择的课程
    List<LinkedHashMap<String, Object>> selectionByTeacher(Integer teacherId);
}
