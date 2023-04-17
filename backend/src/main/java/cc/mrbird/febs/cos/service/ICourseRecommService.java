package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.CourseRecomm;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface ICourseRecommService extends IService<CourseRecomm> {

    /**
     * 分页获取推荐课程信息
     * @param page
     * @param courseRecomm
     * @return
     */
    IPage<LinkedHashMap<String, Object>> selectCourseInfoPage(Page<CourseRecomm> page, CourseRecomm courseRecomm);
}
