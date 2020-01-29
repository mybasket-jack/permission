package com.jack006.dto;

import com.google.common.collect.Lists;
import com.jack006.model.SysAclModule;
import com.jack006.model.SysDept;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 权限模块树型结构层级对象
 *
 * @Author jack
 * @Since 1.0 2020/1/29 22:21
 */
@Setter
@Getter
@ToString
public class AclModuleLevelDto extends SysAclModule {

    private List<AclModuleLevelDto> aclModuleList = Lists.newArrayList();

    public static AclModuleLevelDto adapt (SysAclModule sysAclModule) {
        AclModuleLevelDto dto = new AclModuleLevelDto();
        BeanUtils.copyProperties(sysAclModule, dto);
        return dto;
    }
}
