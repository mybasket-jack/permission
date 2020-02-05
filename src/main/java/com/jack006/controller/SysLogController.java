package com.jack006.controller;

import com.jack006.beans.PageQuery;
import com.jack006.common.JsonData;
import com.jack006.param.SearchLogParam;
import com.jack006.service.SysLogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @Author jack
 * @Since 1.0 2020/2/4 21:05
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController {

    @Resource
    private SysLogService sysLogService;

    @RequestMapping("/log.page")
    public ModelAndView page() {
        return new ModelAndView("log");
    }

    @RequestMapping("/page.json")
    @ResponseBody
    public JsonData page(SearchLogParam param, PageQuery page) {
        return JsonData.success(sysLogService.searchPageList(param,page));
    }

    @RequestMapping("/recover.json")
    @ResponseBody
    public JsonData recover(@RequestParam("id") int id) {
        sysLogService.recover(id);
        return JsonData.success();
    }
}
