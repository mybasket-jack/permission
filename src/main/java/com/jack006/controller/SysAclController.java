package com.jack006.controller;

import com.google.common.collect.Maps;
import com.jack006.beans.PageQuery;
import com.jack006.beans.PageResult;
import com.jack006.common.JsonData;
import com.jack006.model.SysAcl;
import com.jack006.model.SysRole;
import com.jack006.param.AclParam;
import com.jack006.service.SysAclService;
import com.jack006.service.SysRoleService;
import com.jack006.service.SysRoleUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private SysRoleUserService sysRoleUserService;

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

    @RequestMapping("acls.json")
    @ResponseBody
    public JsonData acls(@RequestParam("aclId") int aclId) {
        Map<String, Object> map = Maps.newHashMap();
        List<SysRole> roleList = sysRoleService.getRoleListByAclId(aclId);
        map.put("users",sysRoleService.getUserListByRoleList(roleList));
        map.put("roles", roleList);
        return JsonData.success(map);
    }
}
