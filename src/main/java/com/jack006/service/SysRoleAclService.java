package com.jack006.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.jack006.beans.LogType;
import com.jack006.common.RequestHolder;
import com.jack006.dao.SysLogMapper;
import com.jack006.dao.SysRoleAclMapper;
import com.jack006.model.SysLogWithBLOBs;
import com.jack006.model.SysRoleAcl;
import com.jack006.util.IpUtil;
import com.jack006.util.JsonMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * TODO
 *
 * @Author jack
 * @Since 1.0 2020/1/31 16:10
 */
@Service
public class SysRoleAclService {

    @Resource
    private SysRoleAclMapper sysRoleAclMapper;
    @Resource
    private SysLogMapper sysLogMapper;

    public void changeRoleAcls(Integer roleId, List<Integer> aclIdList) {
        List<Integer> originAclIdList = sysRoleAclMapper.getAclIdListByRoleIdList(Lists.newArrayList(roleId));
        // 判断权限是否更新
        if (originAclIdList.size() == aclIdList.size()) {
            Set<Integer> originAclIdSet = Sets.newHashSet(originAclIdList);
            Set<Integer> aclIdSet = Sets.newHashSet(aclIdList);
            originAclIdSet.removeAll(aclIdSet);
            if (CollectionUtils.isEmpty(originAclIdList)) {
                return;
            }
        }
        updateRoleAcls(roleId, aclIdList);
        saveRoleAclLog(roleId,originAclIdList, aclIdList);
    }

    @Transactional
    public void updateRoleAcls(int roleId, List<Integer> aclIdList) {
        sysRoleAclMapper.deleteByRoleId(roleId);

        if (CollectionUtils.isEmpty(aclIdList)) {
            return;
        }

        List<SysRoleAcl> roleAclList = Lists.newArrayList();
        for (Integer aclId : aclIdList) {
            SysRoleAcl roleAcl = SysRoleAcl.builder().aclId(aclId).roleId(roleId).
                    operator(RequestHolder.getCurrentUser().getUsername())
                    .operatorIp(IpUtil.getUserIP(RequestHolder.getCurrentRequest()))
                    .operatorTime(new Date()).build();
            roleAclList.add(roleAcl);
        }
        sysRoleAclMapper.batchInsert(roleAclList);
    }

    private void saveRoleAclLog(int roleId, List<Integer> before, List<Integer> after) {
        SysLogWithBLOBs sysLog = new SysLogWithBLOBs();
        sysLog.setType(LogType.TYPE_ROLE_ACL);
        sysLog.setTargetId(roleId);
        sysLog.setOldValue(before == null ? "" : JsonMapper.obj2String(before));
        sysLog.setNewValue(after == null ? "" : JsonMapper.obj2String(after));
        sysLog.setOperator(RequestHolder.getCurrentUser().getUsername());
        sysLog.setOperatorTime(new Date());
        sysLog.setOperatorIp(IpUtil.getUserIP(RequestHolder.getCurrentRequest()));
        sysLog.setStatus(1);
        sysLogMapper.insertSelective(sysLog);
    }
}
