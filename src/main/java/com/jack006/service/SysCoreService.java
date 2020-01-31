package com.jack006.service;

import com.google.common.collect.Lists;
import com.jack006.common.RequestHolder;
import com.jack006.dao.SysAclMapper;
import com.jack006.dao.SysRoleAclMapper;
import com.jack006.dao.SysRoleUserMapper;
import com.jack006.model.SysAcl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色权限核心Service
 *
 * @Author jack
 * @Since 1.0 2020/1/31 9:17
 */
@Service
public class SysCoreService {

    @Resource
    private SysAclMapper sysAclMapper;
    @Resource
    private SysRoleUserMapper sysRoleUserMapper;
    @Resource
    private SysRoleAclMapper sysRoleAclMapper;
    /**
     * 获取当前用户已分配的权限
     * @return
     */
    public List<SysAcl> getCurrentUserAclList() {
        int userId = RequestHolder.getCurrentUser().getId();
        return getUserAclList(userId);
    }

    /**
     * 获取该角色分配的权限
     * @param roleId
     * @return
     */
    public List<SysAcl> getCurrentRoleAclList(int roleId) {
        List<Integer> aclIdList = sysRoleAclMapper.getAclIdListByRoleIdList(Lists.<Integer>newArrayList(roleId));
        if (CollectionUtils.isEmpty(aclIdList)) {
            return Lists.newArrayList();
        }
        return sysAclMapper.getByIdList(aclIdList);
    }

    /**
     *  获取该用户分配的权限
     * @param userId
     * @return
     */
    public List<SysAcl> getUserAclList(int userId) {
        if (isSupperAdmin()) {
            return sysAclMapper.getAll();
        }
        // 用户角色ID
        List<Integer> userRoleIdList = sysRoleUserMapper.getRoleIdListByUserId(userId);
        if (CollectionUtils.isEmpty(userRoleIdList)) {
            return Lists.newArrayList();
        }
        // 用户权限Id
        List<Integer> userAclIdList= sysRoleAclMapper.getAclIdListByRoleIdList(userRoleIdList);
        if (CollectionUtils.isEmpty(userAclIdList)) {
            return Lists.newArrayList();
        }
        return sysAclMapper.getByIdList(userAclIdList);
    }

    /**
     * 是否为超级管理员
     * @return
     */
    public boolean isSupperAdmin() {
        return true;
    }
}
