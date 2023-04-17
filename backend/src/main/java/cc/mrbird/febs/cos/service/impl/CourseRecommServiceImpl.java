package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.dao.CourseRecommMapper;
import cc.mrbird.febs.cos.entity.CourseInfo;
import cc.mrbird.febs.cos.entity.CourseRecomm;
import cc.mrbird.febs.cos.service.ICourseInfoService;
import cc.mrbird.febs.cos.service.ICourseRecommService;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author FanK
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseRecommServiceImpl extends ServiceImpl<CourseRecommMapper, CourseRecomm> implements ICourseRecommService {

    private ICourseInfoService courseInfoService;

    /**
     * 分页获取推荐课程信息
     * @param page
     * @param courseRecomm
     * @return
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectCourseInfoPage(Page<CourseRecomm> page, CourseRecomm courseRecomm) {
        // 返回数据
        return baseMapper.selectCourseInfoPage(page, courseRecomm);
    }
}
