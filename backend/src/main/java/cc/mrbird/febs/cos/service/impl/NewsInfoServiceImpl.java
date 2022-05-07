package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.NewsInfo;
import cc.mrbird.febs.cos.dao.NewsInfoMapper;
import cc.mrbird.febs.cos.service.INewsInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
@Service
public class NewsInfoServiceImpl extends ServiceImpl<NewsInfoMapper, NewsInfo> implements INewsInfoService {

    @Override
    public IPage<LinkedHashMap<String, Object>> getNewsInfoByPage(Page page, NewsInfo newsInfo) {
        return baseMapper.getNewsInfoByPage(page, newsInfo);
    }
}
