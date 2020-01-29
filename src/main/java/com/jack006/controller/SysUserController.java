package com.jack006.controller;

import com.jack006.beans.PageQuery;
import com.jack006.beans.PageResult;
import com.jack006.common.JsonData;
import com.jack006.model.SysUser;
import com.jack006.param.UserParam;
import com.jack006.service.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @Author jack
 * @Since 1.0 2020/1/28 20:51
 */
@Controller
@RequestMapping("/sys/user")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData saveUser(UserParam param) {
        sysUserService.save(param);
        return JsonData.success();
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData updateUser(UserParam param) {
        sysUserService.update(param);
        return JsonData.success();
    }

    @RequestMapping("page.json")
    @ResponseBody
    public JsonData page(@RequestParam("deptId") Integer deptId, PageQuery page) {
        PageResult<SysUser> result = sysUserService.getPageByDeptId(deptId, page);
        return JsonData.success(result);
    }
}
