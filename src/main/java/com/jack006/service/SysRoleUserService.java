package com.jack006.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.jack006.beans.LogType;
import com.jack006.common.RequestHolder;
import com.jack006.dao.SysLogMapper;
import com.jack006.dao.SysRoleUserMapper;
import com.jack006.dao.SysUserMapper;
import com.jack006.model.SysLogWithBLOBs;
import com.jack006.model.SysRoleUser;
import com.jack006.model.SysUser;
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
 * 角色与用户Service
 *
 * @Author jack
 * @Since 1.0 2020/1/31 16:10
 */
@Service
public class SysRoleUserService {

    @Resource
    private SysRoleUserMapper sysRoleUserMapper;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysLogMapper sysLogMapper;


    /**
     * 获取当前选中角色的用户列表
     * @param roleId
     * @return
     */
    public List<SysUser> getUserListByRoleId(int roleId) {
        List<Integer> userIdList = sysRoleUserMapper.getUserIdListByRoleId(roleId);
        if (CollectionUtils.isEmpty(userIdList)) {
            return Lists.newArrayList();
        }
        return sysUserMapper.getByIdList(userIdList);
    }

    public void changeRoleUsers(int roleId, List<Integer> userIdList) {
        List<Integer> originUserIdList = sysRoleUserMapper.getUserIdListByRoleId(roleId);
        // 判断是否一致
        if (originUserIdList.size() == userIdList.size()) {
            Set<Integer> originUserIdSet = Sets.newHashSet(originUserIdList);
            Set<Integer> userIdSet = Sets.newHashSet(userIdList);
            originUserIdSet.removeAll(userIdSet);
            if (CollectionUtils.isEmpty(originUserIdSet)) {
                return;
            }
        }
        // 更新之前先删除
        updateRoleUsers(roleId, userIdList);
        saveRoleUserLog(roleId,originUserIdList,userIdList);
    }

    @Transactional
    public void updateRoleUsers(int roleId, List<Integer> userIdList) {
        sysRoleUserMapper.deleteByRoleId(roleId);
        if (CollectionUtils.isEmpty(userIdList)) {
            return;
        }

        List<SysRoleUser> roleUserList = Lists.newArrayList();
        for (Integer userId : userIdList) {
            SysRoleUser roleUser = SysRoleUser.builder().userId(userId).roleId(roleId).
                    operator(RequestHolder.getCurrentUser().getUsername())
                    .operatorIp(IpUtil.getUserIP(RequestHolder.getCurrentRequest()))
                    .operatorTime(new Date()).build();
            roleUserList.add(roleUser);
        }
        sysRoleUserMapper.batchInsert(roleUserList);
    }

    private void saveRoleUserLog(int roleId, List<Integer> before, List<Integer> after) {
        SysLogWithBLOBs sysLog = new SysLogWithBLOBs();
        sysLog.setType(LogType.TYPE_ROLE_USER);
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
