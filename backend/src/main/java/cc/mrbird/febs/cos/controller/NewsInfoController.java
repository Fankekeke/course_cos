package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.NewsInfo;
import cc.mrbird.febs.cos.service.INewsInfoService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author FanK
 */
@RestController
@RequestMapping("/cos/news-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NewsInfoController {

    private final INewsInfoService newsInfoService;

    /**
     * 分页查询动态信息
     * @param page
     * @param newsInfo
     * @return
     */
    @GetMapping("/page")
    public R page(Page page, NewsInfo newsInfo) {
        return R.ok(newsInfoService.getNewsInfoByPage(page, newsInfo));
    }

    /**
     * 添加动态信息
     * @param newsInfo
     * @return
     */
    @PostMapping
    public R save(NewsInfo newsInfo) {
        newsInfo.setDate(DateUtil.formatDateTime(new Date()));
        return R.ok(newsInfoService.save(newsInfo));
    }

    /**
     * 修改动态信息
     * @param newsInfo
     * @return
     */
    @PutMapping
    public R edit(NewsInfo newsInfo) {
        newsInfo.setDate(DateUtil.formatDateTime(new Date()));
        return R.ok(newsInfoService.updateById(newsInfo));
    }

    /**
     * 删除动态信息
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(newsInfoService.removeByIds(ids));
    }

}
