package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.SelectionInfo;
import cc.mrbird.febs.cos.entity.StudentInfo;
import cc.mrbird.febs.cos.service.ISelectionInfoService;
import cc.mrbird.febs.cos.service.IStudentInfoService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author FanK
 */
@RestController
@RequestMapping("/cos/selection-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SelectionInfoController {

    private final ISelectionInfoService selectionInfoService;

    private final IStudentInfoService studentInfoService;

    /**
     * 查询课程是否重复预约
     * @param courseId
     * @param userId
     * @return
     */
    @GetMapping("/isCheck")
    public R courseIsCheck(@RequestParam Integer courseId,  @RequestParam Integer userId) {
        StudentInfo studentInfo = studentInfoService.getOne(Wrappers.<StudentInfo>lambdaQuery().eq(StudentInfo::getAdminId, userId));
        return R.ok(selectionInfoService.count(Wrappers.<SelectionInfo>lambdaQuery().eq(SelectionInfo::getCourseId, courseId)
                .eq(SelectionInfo::getStudentId, studentInfo.getId())));
    }

    /**
     * 预约课程
     * @param courseId
     * @param userId
     * @return
     */
    @PostMapping("/check")
    public R check(@RequestParam Integer courseId,  @RequestParam Integer userId) {
        StudentInfo studentInfo = studentInfoService.getOne(Wrappers.<StudentInfo>lambdaQuery().eq(StudentInfo::getAdminId, userId));
        SelectionInfo selectionInfo = new SelectionInfo();
        selectionInfo.setCourseId(courseId);
        selectionInfo.setStudentId(studentInfo.getId());
        selectionInfo.setCreateTime(DateUtil.formatDateTime(new Date()));
        return R.ok(selectionInfoService.save(selectionInfo));
    }

    /**
     * 分页查询选课信息
     * @param page
     * @param selectionInfo
     * @return
     */
    @GetMapping("/page")
    public R page(Page page, SelectionInfo selectionInfo) {
        if (selectionInfo.getUserId() != null) {
            StudentInfo studentInfo = studentInfoService.getOne(Wrappers.<StudentInfo>lambdaQuery().eq(StudentInfo::getAdminId, selectionInfo.getUserId()));
            selectionInfo.setStudentId(studentInfo.getId());
        }
        return R.ok(selectionInfoService.selectionByPage(page, selectionInfo));
    }

    /**
     * 添加新的选课信息
     * @param selectionInfo
     * @return
     */
    @PostMapping
    public R save(SelectionInfo selectionInfo) {
        return R.ok(selectionInfoService.save(selectionInfo));
    }

    /**
     * 修改选课信息
     * @param selectionInfo
     * @return
     */
    @PutMapping
    public R edit(SelectionInfo selectionInfo) {
        return R.ok(selectionInfoService.updateById(selectionInfo));
    }

    /**
     * 删除选课信息
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(selectionInfoService.removeByIds(ids));
    }

}
