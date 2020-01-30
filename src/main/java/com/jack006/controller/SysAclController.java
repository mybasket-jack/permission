package com.jack006.controller;

import com.jack006.beans.PageQuery;
import com.jack006.beans.PageResult;
import com.jack006.common.JsonData;
import com.jack006.model.SysAcl;
import com.jack006.param.AclParam;
import com.jack006.service.SysAclService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 权限点控制类
 *
 * @Author jack
 * @Since 1.0 2020/1/29 21:28
 */
@Controller
@RequestMapping("/sys/acl")
public class SysAclController {

    @Resource
    private SysAclService sysAclService;

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData saveAcl(AclParam param) {
        sysAclService.save(param);
        return JsonData.success();
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData updateAcl(AclParam param){
        sysAclService.update(param);
        return JsonData.success();
    }

    @RequestMapping("/page.json")
    @ResponseBody
    public JsonData page(@RequestParam("aclModuleId") Integer aclModuleId, PageQuery pageQuery){
        PageResult<SysAcl> aclList = sysAclService.getPageByAclModuleId(aclModuleId, pageQuery);
        return JsonData.success(aclList);
    }
}
