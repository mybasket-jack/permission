package com.jack006.service;

import com.google.common.base.Preconditions;
import com.jack006.beans.PageQuery;
import com.jack006.beans.PageResult;
import com.jack006.common.RequestHolder;
import com.jack006.dao.SysAclMapper;
import com.jack006.exception.ParamException;
import com.jack006.model.SysAcl;
import com.jack006.param.AclParam;
import com.jack006.util.BeanValidator;
import com.jack006.util.IpUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 权限点svice 实现类
 *
 * @Author jack
 * @Since 1.0 2020/1/30 15:10
 */
@Service
public class SysAclService {

    @Resource
    private SysAclMapper sysAclMapper;

    public void save(AclParam param) {
        BeanValidator.check(param);
        if (checkExist(param.getAclModuleId(), param.getName(), param.getId())) {
            throw  new ParamException("当前权限模块下面存在相同名称的权限点");
        }
        SysAcl acl = SysAcl.builder().name(param.getName()).aclModuleId(param.getAclModuleId()).url(param.getUrl())
                .type(param.getType()).status(param.getStatus()).seq(param.getSeq()).remark(param.getRemark()).build();
        acl.setCode(generateCode());
        acl.setOperator(RequestHolder.getCurrentUser().getUsername());
        acl.setOperatorIp(IpUtil.getUserIP(RequestHolder.getCurrentRequest()));
        acl.setOperatorTime(new Date());
        sysAclMapper.insertSelective(acl);
    }

    public void update(AclParam param) {
        BeanValidator.check(param);
        if (checkExist(param.getAclModuleId(), param.getName(), param.getId())) {
            throw  new ParamException("当前权限模块下面存在相同名称的权限点");
        }

        SysAcl before = sysAclMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before,"待更新的权限点不存在");

        SysAcl after = SysAcl.builder().id(param.getId()).name(param.getName()).aclModuleId(param.getAclModuleId()).url(param.getUrl())
                .type(param.getType()).status(param.getStatus()).seq(param.getSeq()).remark(param.getRemark()).build();
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperatorIp(IpUtil.getUserIP(RequestHolder.getCurrentRequest()));
        after.setOperatorTime(new Date());
        sysAclMapper.updateByPrimaryKeySelective(after);

    }

    /**
     * 校验权限点名称是否重复
     * @param aclModuleId
     * @param name
     * @param id
     * @return
     */
    public boolean checkExist(int aclModuleId, String name, Integer id) {
        return sysAclMapper.countByNameAndAclModuleId(aclModuleId,name,id) > 0;
    }

    /**
     * 生成权限点的权限码
     * @return
     */
    public String generateCode() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpleDateFormat.format(new Date()) + "_" + (int)(Math.random() * 100);
    }

    public PageResult<SysAcl> getPageByAclModuleId(int aclModuleId, PageQuery page) {
        BeanValidator.check(page);
        int count = sysAclMapper.countByAclModuleId(aclModuleId);
        if (count > 0) {
            List<SysAcl> aclList = sysAclMapper.getPageByAclModuleId(aclModuleId, page);
            return PageResult.<SysAcl>builder().data(aclList).total(count).build();
        }
        return PageResult.<SysAcl>builder().build();

    }
}
