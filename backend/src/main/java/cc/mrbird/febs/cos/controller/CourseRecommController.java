package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.CourseRecomm;
import cc.mrbird.febs.cos.service.ICourseRecommService;
import cn.hutool.core.date.DateUtil;
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
@RequestMapping("/manage/course-recomm")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseRecommController {

    private final ICourseRecommService courseRecommService;

    /**
     * 分页获取推荐课程信息
     * @param page
     * @param courseRecomm
     * @return
     */
    @GetMapping("/page")
    public R page(Page<CourseRecomm> page, CourseRecomm courseRecomm) {
        return R.ok(courseRecommService.selectCourseInfoPage(page, courseRecomm));
    }

    /**
     * 新增推荐课程信息
     * @param courseRecomm
     * @return
     */
    @PostMapping
    public R save(CourseRecomm courseRecomm) {
        courseRecomm.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(courseRecommService.save(courseRecomm));
    }

    /**
     * 修改推荐课程信息
     * @param courseRecomm
     * @return
     */
    @PutMapping
    public R edit(CourseRecomm courseRecomm) {
        return R.ok(courseRecommService.updateById(courseRecomm));
    }

    /**
     * 删除推荐课程信息
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(courseRecommService.removeByIds(ids));
    }

}
