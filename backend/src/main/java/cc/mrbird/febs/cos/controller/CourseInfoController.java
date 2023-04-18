package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.CourseInfo;
import cc.mrbird.febs.cos.entity.TeacherInfo;
import cc.mrbird.febs.cos.service.ICourseInfoService;
import cc.mrbird.febs.cos.service.ITeacherInfoService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
@RestController
@RequestMapping("/cos/course-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseInfoController {

    private final ICourseInfoService courseInfoService;

    private final ITeacherInfoService teacherInfoService;

    /**
     * 根据课程获取学生信息
     *
     * @param id
     * @return
     */
    @GetMapping("/studentList")
    public R getStudentListByCourse(@RequestParam Integer id) {
        return R.ok(courseInfoService.getStudentListByCourse(id));
    }

    /**
     * 查询所有课程信息
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        List<CourseInfo> courseInfoList = courseInfoService.list();
        // 返回数据
        List<LinkedHashMap<String, Object>> result = new ArrayList<>();
        courseInfoList.forEach(e -> {
            result.add(new LinkedHashMap<String, Object>() {
                {
                    put("label", e.getCourseName());
                    put("value", e.getId());
                }
            });
        });
        return R.ok(result);
    }

    /**
     * 取消课程绑定
     *
     * @param courseId
     * @return
     */
    @GetMapping("/removeCourse")
    public R removeCourse(@RequestParam Integer courseId) {
        return R.ok(courseInfoService.update(Wrappers.<CourseInfo>lambdaUpdate().set(CourseInfo::getTeacherId, null).eq(CourseInfo::getId, courseId)));
    }

    /**
     * 绑定课程
     *
     * @param courseId
     * @param userId
     * @return
     */
    @PutMapping("/check")
    public R check(@RequestParam Integer courseId, @RequestParam Integer userId) {
        TeacherInfo teacherInfo = teacherInfoService.getOne(Wrappers.<TeacherInfo>lambdaQuery().eq(TeacherInfo::getAdminId, userId));
        return R.ok(courseInfoService.update(Wrappers.<CourseInfo>lambdaUpdate().set(CourseInfo::getTeacherId, teacherInfo.getId()).eq(CourseInfo::getId, courseId)));
    }

    /**
     * 根据教员获取课程分页信息
     *
     * @param page
     * @param courseInfo
     * @return
     */
    @GetMapping("/page/teacher")
    public R getCoursePageByTeacher(Page page, CourseInfo courseInfo) {
        return R.ok(courseInfoService.getCoursePageByTeacher(page, courseInfo));
    }

    /**
     * 分页查询课程信息
     *
     * @param page
     * @param courseInfo
     * @return
     */
    @GetMapping("/page")
    public R page(Page page, CourseInfo courseInfo) {
        return R.ok(courseInfoService.getCourseInfoByPage(page, courseInfo));
    }

    /**
     * 添加新的课程信息
     *
     * @param courseInfo
     * @return
     */
    @PostMapping
    public R save(CourseInfo courseInfo) {
        courseInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(courseInfoService.save(courseInfo));
    }

    /**
     * 修改课程信息
     *
     * @param courseInfo
     * @return
     */
    @PutMapping
    public R edit(CourseInfo courseInfo) {
        return R.ok(courseInfoService.updateById(courseInfo));
    }

    /**
     * 删除课程信息
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(courseInfoService.removeByIds(ids));
    }

}
