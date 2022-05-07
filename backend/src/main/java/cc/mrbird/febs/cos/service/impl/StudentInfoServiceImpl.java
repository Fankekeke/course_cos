package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.CourseInfo;
import cc.mrbird.febs.cos.entity.StudentInfo;
import cc.mrbird.febs.cos.dao.StudentInfoMapper;
import cc.mrbird.febs.cos.entity.TeacherInfo;
import cc.mrbird.febs.cos.service.*;
import cc.mrbird.febs.system.domain.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentInfoServiceImpl extends ServiceImpl<StudentInfoMapper, StudentInfo> implements IStudentInfoService {

    private final IBulletinInfoService bulletinInfoService;

    private final ITeacherInfoService teacherInfoService;

    private final ICourseInfoService courseInfoService;

    private final INewsInfoService newsInfoService;

    @Override
    public IPage<LinkedHashMap<String, Object>> getStudentInfoByPage(Page page, StudentInfo studentInfo) {
        return baseMapper.getStudentInfoByPage(page, studentInfo);
    }

    @Override
    public List<LinkedHashMap> selectionByStudentId(Integer studentId) {
        return baseMapper.selectionByStudentId(studentId);
    }

    @Override
    public IPage<LinkedHashMap<String, Object>> systemUserPage(Page page, User user) {
        return baseMapper.systemUserPage(page, user);
    }

    @Override
    public LinkedHashMap<String, Object> home(Integer userId, Integer userType) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        // 公告信息
        result.put("bulletin", bulletinInfoService.list());
        // 动态信息
        result.put("news", newsInfoService.list());
        // 学生专业分布统计
        result.put("studentDisciplineRate", baseMapper.studentDisciplineRate());
        // 课程预约情况
        result.put("courseRate", baseMapper.courseRate());
        // 管理员
        result.put("teacherNum", teacherInfoService.count());
        result.put("studentNum", this.count());
        result.put("courseNum", courseInfoService.count());
        result.put("courseNotTeacherNum", courseInfoService.count(Wrappers.<CourseInfo>lambdaQuery().isNull(CourseInfo::getTeacherId)));
        // 教员
        if (userType == 75) {
            Integer teacherId = teacherInfoService.getOne(Wrappers.<TeacherInfo>lambdaQuery().eq(TeacherInfo::getAdminId, userId)).getId();
            result.put("course", teacherInfoService.selectionByTeacher(teacherId));
        }
        // 学生
        if (userType == 76) {
            Integer studentId = this.getOne(Wrappers.<StudentInfo>lambdaQuery().eq(StudentInfo::getAdminId, userId)).getId();
            result.put("course", baseMapper.selectionByStudentId(studentId));
        }
        return result;
    }
}
