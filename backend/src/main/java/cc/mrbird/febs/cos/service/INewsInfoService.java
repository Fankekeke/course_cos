package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.NewsInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface INewsInfoService extends IService<NewsInfo> {

    // 分页查询动态信息
    IPage<LinkedHashMap<String, Object>> getNewsInfoByPage(Page page, NewsInfo newsInfo);
}
