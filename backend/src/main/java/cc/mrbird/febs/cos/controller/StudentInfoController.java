package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.StudentInfo;
import cc.mrbird.febs.cos.service.IStudentInfoService;
import cc.mrbird.febs.system.domain.User;
import cc.mrbird.febs.system.service.UserService;
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
@RequestMapping("/cos/student-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentInfoController {

    private final IStudentInfoService studentInfoService;

    private final UserService userService;

    /**
     * 进入主页
     * @param userId
     * @return
     */
    @GetMapping("/home")
    public R home(@RequestParam Integer userId, @RequestParam Integer userType) {
        return R.ok(studentInfoService.home(userId, userType));
    }

    /**
     * 学生个人信息
     * @param userId
     * @return
     */
    @GetMapping("/user/info")
    public R userHome(@RequestParam Integer userId) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        StudentInfo studentInfo = studentInfoService.getOne(Wrappers.<StudentInfo>lambdaQuery().eq(StudentInfo::getAdminId, userId));
        result.put("studentInfo", studentInfo);
        result.put("courseInfo", studentInfoService.selectionByStudentId(studentInfo.getId()));
        return R.ok(result);
    }

    /**
     * 学生信息列表
     * @return
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(studentInfoService.list());
    }

    /**
     * 判断学号是否重复
     * @param studentId
     * @return
     */
    @GetMapping("/repeat")
    public R studentIdRepeat(@RequestParam String studentId, @RequestParam Integer tid) {
        return R.ok(studentInfoService.count(Wrappers.<StudentInfo>lambdaQuery().eq(StudentInfo::getStudentId, studentId).ne(StudentInfo::getId, tid)));
    }

    /**
     * 根据学生获取选择的课程
     * @param id
     * @return
     */
    @GetMapping("/selection")
    public R selectionByStudentId(@RequestParam Integer id) {
        return R.ok(studentInfoService.selectionByStudentId(id));
    }

    /**
     * 更改系统用户账号状态
     * @param status
     * @param userId
     * @return
     */
    @PutMapping("/user/audit")
    public R auditUserStatus(@RequestParam Integer status, @RequestParam Integer userId) {
        return R.ok(userService.update(Wrappers.<User>lambdaUpdate().set(User::getStatus, status).eq(User::getUserId, userId)));
    }

    /**
     * 分页获取系统用户数据
     * @param page
     * @param user
     * @return
     */
    @GetMapping("/user/page")
    public R userPage(Page page, User user) {
        return R.ok(studentInfoService.systemUserPage(page, user));
    }

    /**
     * 分页查询学生信息
     * @param page
     * @param studentInfo
     * @return
     */
    @GetMapping("/page")
    public R page(Page page, StudentInfo studentInfo) {
        return R.ok(studentInfoService.getStudentInfoByPage(page, studentInfo));
    }

    /**
     * 添加新的学生
     * @param studentInfo
     * @return
     */
    @PostMapping
    public R save(StudentInfo studentInfo) {
        studentInfo.setCreateTime(DateUtil.formatTime(new Date()));
        return R.ok(studentInfoService.save(studentInfo));
    }

    /**
     * 修改学生信息
     * @param studentInfo
     * @return
     */
    @PutMapping
    public R edit(StudentInfo studentInfo) {
        return R.ok(studentInfoService.updateById(studentInfo));
    }

    /**
     * 删除学生信息
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(studentInfoService.removeByIds(ids));
    }

}
