package com.jack006.controller;

import com.jack006.beans.PageQuery;
import com.jack006.beans.PageResult;
import com.jack006.common.JsonData;
import com.jack006.model.SysAcl;
import com.jack006.model.SysRole;
import com.jack006.param.AclParam;
import com.jack006.param.RoleParam;
import com.jack006.service.SysRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.management.relation.Role;
import java.util.List;

/**
 * 角色控制类
 *
 * @Author jack
 * @Since 1.0 2020/1/30 20:24
 */
@Controller
@RequestMapping("/sys/role")
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;

    @RequestMapping("/role.page")
    public ModelAndView page() {
        return new ModelAndView("role");
    }

    @RequestMapping("/save.json")
    @ResponseBody
    public JsonData saveRole(RoleParam param) {
        sysRoleService.save(param);
        return JsonData.success();
    }

    @RequestMapping("/update.json")
    @ResponseBody
    public JsonData updateRole(RoleParam param){
        sysRoleService.update(param);
        return JsonData.success();
    }

    @RequestMapping("/list.json")
    @ResponseBody
    public JsonData list(){
        return JsonData.success(sysRoleService.getAll());
    }

}
