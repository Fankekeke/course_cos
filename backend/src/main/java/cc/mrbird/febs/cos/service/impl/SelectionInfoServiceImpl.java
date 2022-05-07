package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.SelectionInfo;
import cc.mrbird.febs.cos.dao.SelectionInfoMapper;
import cc.mrbird.febs.cos.service.ISelectionInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
@Service
public class SelectionInfoServiceImpl extends ServiceImpl<SelectionInfoMapper, SelectionInfo> implements ISelectionInfoService {

    @Override
    public IPage<LinkedHashMap<String, Object>> selectionByPage(Page page, SelectionInfo selectionInfo) {
        return baseMapper.selectionByPage(page, selectionInfo);
    }
}
