package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.SelectionInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface SelectionInfoMapper extends BaseMapper<SelectionInfo> {

    // 分页查询选课信息
    IPage<LinkedHashMap<String, Object>> selectionByPage(Page page, @Param("selectionInfo") SelectionInfo selectionInfo);
}
