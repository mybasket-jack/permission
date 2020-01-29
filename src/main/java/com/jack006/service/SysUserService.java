package com.jack006.service;

import com.google.common.base.Preconditions;
import com.jack006.beans.PageQuery;
import com.jack006.beans.PageResult;
import com.jack006.dao.SysUserMapper;
import com.jack006.exception.ParamException;
import com.jack006.model.SysUser;
import com.jack006.param.UserParam;
import com.jack006.util.BeanValidator;
import com.jack006.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 用户接口类
 *
 * @Author jack
 * @Since 1.0 2020/1/28 21:07
 */
@Service
public class SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    // 保存用户
    public void save(UserParam param){
        BeanValidator.check(param);
        if (checkTelephoneExist(param.getTelephone(), param.getId())) {
            throw new ParamException("电话已被占用");
        }
        if (checkEmailExist(param.getMail(), param.getId())) {
            throw new ParamException("邮箱已被占用");
        }
        // 系统生成用户密码,然后发送密码给用户邮箱中
        //String password = PasswordUtil.randomPassword();
        // 为了方便登录先暂定123456
        String password = "123456";
        String encrytedPassword = MD5Util.encrypt(password);
        SysUser sysUser = SysUser.builder().username(param.getUsername()).telephone(param.getTelephone())
                .mail(param.getMail()).password(encrytedPassword).status(param.getStatus())
                .deptId(param.getDeptId()).remark(param.getRemark()).build();
        sysUser.setOperator("system");
        sysUser.setOperatorIp("127.0.0.1");
        sysUser.setOperatorTime(new Date());
        // TODO： sendEmail 通过邮件通知用户
        sysUserMapper.insertSelective(sysUser);
    }

    // 更新用户
    public void update(UserParam param){
        BeanValidator.check(param);
        if (checkTelephoneExist(param.getTelephone(), param.getId())) {
            throw new ParamException("电话已被占用");
        }
        if (checkEmailExist(param.getMail(), param.getId())) {
            throw new ParamException("邮箱已被占用");
        }
        // 更新前的用户
        SysUser before = sysUserMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before,"带更新的用户不存在");
        SysUser after = SysUser.builder().id(param.getId()).username(param.getUsername()).telephone(param.getTelephone())
                .mail(param.getMail()).password(before.getPassword()).status(param.getStatus())
                .deptId(param.getDeptId()).remark(param.getRemark()).build();
        sysUserMapper.updateByPrimaryKeySelective(after);
    }

    public boolean checkEmailExist(String mail, Integer userId) {
        return sysUserMapper.countByMail(mail,userId) > 0;
    }

    public boolean checkTelephoneExist(String telephone, Integer userId ) {
            return sysUserMapper.countByTelephone(telephone,userId) > 0;
    }

    public SysUser findUserByKeyWord(String keyword) {
        return sysUserMapper.findUserByKeyWord(keyword);
    }

    public PageResult<SysUser> getPageByDeptId(int deptId, PageQuery page) {
        // 校验page分页对象是否符合
        BeanValidator.check(page);
        int count = sysUserMapper.countByDeptId(deptId);
        if (count > 0) {
            List<SysUser> list = sysUserMapper.getPageByDeptId(deptId,page);
            return PageResult.<SysUser>builder().total(count).data(list).build();
        }
        return PageResult.<SysUser>builder().build();
    }
}
