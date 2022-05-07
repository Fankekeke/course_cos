package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.TeacherInfo;
import cc.mrbird.febs.cos.dao.TeacherInfoMapper;
import cc.mrbird.febs.cos.service.ITeacherInfoService;
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
public class TeacherInfoServiceImpl extends ServiceImpl<TeacherInfoMapper, TeacherInfo> implements ITeacherInfoService {

    @Override
    public IPage<LinkedHashMap<String, Object>> teacherInfoByPage(Page page, TeacherInfo teacherInfo) {
        return baseMapper.teacherInfoByPage(page, teacherInfo);
    }

    @Override
    public List<LinkedHashMap<String, Object>> selectionByTeacher(Integer teacherId) {
        return baseMapper.selectionByTeacher(teacherId);
    }
}
