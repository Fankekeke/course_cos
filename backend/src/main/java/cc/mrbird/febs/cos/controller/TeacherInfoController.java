package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.StudentInfo;
import cc.mrbird.febs.cos.entity.TeacherInfo;
import cc.mrbird.febs.cos.service.ITeacherInfoService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
@RestController
@RequestMapping("/cos/teacher-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TeacherInfoController {

    private final ITeacherInfoService teacherInfoService;

    /**
     * 学生个人信息
     * @param userId
     * @return
     */
    @GetMapping("/user/info")
    public R userHome(@RequestParam Integer userId) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        TeacherInfo teacherInfo = teacherInfoService.getOne(Wrappers.<TeacherInfo>lambdaQuery().eq(TeacherInfo::getAdminId, userId));
        result.put("teacherInfo", teacherInfo);
        result.put("courseInfo", teacherInfoService.selectionByTeacher(teacherInfo.getId()));
        return R.ok(result);
    }

    /**
     * 获取所有教师信息
     * @return
     */
    @GetMapping("/list")
    public R getTeacherList() {
        return R.ok(teacherInfoService.list());
    }

    /**
     * 判断教员是否重复
     * @param jobNumber
     * @return
     */
    @GetMapping("/repeat")
    public R studentIdRepeat(@RequestParam String jobNumber, @RequestParam Integer tid) {
        return R.ok(teacherInfoService.count(Wrappers.<TeacherInfo>lambdaQuery().eq(TeacherInfo::getJobNumber, jobNumber).ne(TeacherInfo::getId, tid)));
    }

    /**
     * 根据教员获取选择的课程
     * @param id
     * @return
     */
    @GetMapping("/selection")
    public R selectionByTeacher(@RequestParam Integer id) {
        return R.ok(teacherInfoService.selectionByTeacher(id));
    }

    /**
     * 分页查询教师信息
     * @param page
     * @param teacherInfo
     * @return
     */
    @GetMapping("/page")
    public R page(Page page, TeacherInfo teacherInfo) {
        return R.ok(teacherInfoService.teacherInfoByPage(page, teacherInfo));
    }

    /**
     * 添加新教师
     * @param teacherInfo
     * @return
     */
    @PostMapping
    public R save(TeacherInfo teacherInfo) {
        teacherInfo.setCreateTime(DateUtil.formatTime(new Date()));
        return R.ok(teacherInfoService.save(teacherInfo));
    }

    /**
     * 修改教师
     * @param teacherInfo
     * @return
     */
    @PutMapping
    public R edit(TeacherInfo teacherInfo) {
        return R.ok(teacherInfoService.updateById(teacherInfo));
    }

    /**
     * 删除教师
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(teacherInfoService.removeByIds(ids));
    }

}
