package com.jack006.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.jack006.common.RequestHolder;
import com.jack006.dao.SysRoleAclMapper;
import com.jack006.model.SysRoleAcl;
import com.jack006.util.IpUtil;
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
}
