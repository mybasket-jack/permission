package com.jack006.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.jack006.common.RequestHolder;
import com.jack006.dao.SysRoleUserMapper;
import com.jack006.dao.SysUserMapper;
import com.jack006.model.SysRoleAcl;
import com.jack006.model.SysRoleUser;
import com.jack006.model.SysUser;
import com.jack006.util.IpUtil;
import com.jack006.util.StringUtil;
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
        return sysUserMapper.getUserListByIdList(userIdList);
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
}
