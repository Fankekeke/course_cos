package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.SelectionInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface ISelectionInfoService extends IService<SelectionInfo> {

    // 分页查询选课信息
    IPage<LinkedHashMap<String, Object>> selectionByPage(Page page, SelectionInfo selectionInfo);
}
