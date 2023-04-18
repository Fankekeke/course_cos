package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.CourseRecomm;
import cc.mrbird.febs.cos.entity.StudentInfo;
import cc.mrbird.febs.cos.service.ICourseInfoService;
import cc.mrbird.febs.cos.service.ICourseRecommService;
import cc.mrbird.febs.cos.service.IStudentInfoService;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author FanK
 */
@RestController
@RequestMapping("/manage/course-recomm")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseRecommController {

    private final ICourseRecommService courseRecommService;

    private final IStudentInfoService studentInfoService;

    private final ICourseInfoService courseInfoService;

    /**
     * 分页获取推荐课程信息
     *
     * @param page
     * @param courseRecomm
     * @return
     */
    @GetMapping("/page")
    public R page(Page<CourseRecomm> page, CourseRecomm courseRecomm) {
        return R.ok(courseRecommService.selectCourseInfoPage(page, courseRecomm));
    }

    /**
     * 根据专业获取推荐课程
     *
     * @param userId 用户ID
     * @return 结果
     */
    @GetMapping("/recomm/discipline/{userId}")
    public R selectCourseRecommByDiscipline(@PathVariable("userId") String userId) {
        if (StrUtil.isEmpty(userId)) {
            return R.ok();
        }
        StudentInfo studentInfo = studentInfoService.getOne(Wrappers.<StudentInfo>lambdaQuery().eq(StudentInfo::getAdminId, userId));
        if (studentInfo == null || StrUtil.isEmpty(studentInfo.getDiscipline())) {
            return R.ok();
        }
        CourseRecomm courseRecomm = courseRecommService.getOne(Wrappers.<CourseRecomm>lambdaQuery().eq(CourseRecomm::getDiscipline, studentInfo.getDiscipline()));
        if (courseRecomm == null || StrUtil.isEmpty(courseRecomm.getCourseIds())) {
            return R.ok();
        }
        List<String> courseIds = StrUtil.splitTrim(courseRecomm.getCourseIds(), ",");
        return R.ok(courseInfoService.listByIds(courseIds));
    }

    /**
     * 新增推荐课程信息
     *
     * @param courseRecomm
     * @return
     */
    @PostMapping
    public R save(CourseRecomm courseRecomm) throws FebsException {
        int count = courseRecommService.count(Wrappers.<CourseRecomm>lambdaQuery().eq(CourseRecomm::getDiscipline, courseRecomm.getDiscipline()));
        if (count > 0) {
            throw new FebsException("此专业已经绑定推荐课程！");
        }
        courseRecomm.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(courseRecommService.save(courseRecomm));
    }

    /**
     * 修改推荐课程信息
     *
     * @param courseRecomm
     * @return
     */
    @PutMapping
    public R edit(CourseRecomm courseRecomm) {
        return R.ok(courseRecommService.updateById(courseRecomm));
    }

    /**
     * 删除推荐课程信息
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(courseRecommService.removeByIds(ids));
    }

}
