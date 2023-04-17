package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.CourseRecomm;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface CourseRecommMapper extends BaseMapper<CourseRecomm> {

    /**
     * 分页获取推荐课程信息
     * @param page
     * @param courseRecomm
     * @return
     */
    IPage<LinkedHashMap<String, Object>> selectCourseInfoPage(Page<CourseRecomm> page, @Param("courseRecomm") CourseRecomm courseRecomm);
}
