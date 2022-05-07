package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.CourseInfo;
import cc.mrbird.febs.cos.dao.CourseInfoMapper;
import cc.mrbird.febs.cos.service.ICourseInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
@Service
public class CourseInfoServiceImpl extends ServiceImpl<CourseInfoMapper, CourseInfo> implements ICourseInfoService {

    @Override
    public IPage<LinkedHashMap<String, Object>> getCourseInfoByPage(Page page, CourseInfo courseInfo) {
        return baseMapper.getCourseInfoByPage(page, courseInfo);
    }

    @Override
    public List<LinkedHashMap<String, Object>> getStudentListByCourse(Integer courseId) {
        return baseMapper.getStudentListByCourse(courseId);
    }

    @Override
    public IPage<LinkedHashMap<String, Object>> getCoursePageByTeacher(Page page, CourseInfo courseInfo) {
        return baseMapper.getCoursePageByTeacher(page, courseInfo);
    }
}
