package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.NewsInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface NewsInfoMapper extends BaseMapper<NewsInfo> {

    // 分页查询动态信息
    IPage<LinkedHashMap<String, Object>> getNewsInfoByPage(Page page, @Param("newsInfo") NewsInfo newsInfo);
}
